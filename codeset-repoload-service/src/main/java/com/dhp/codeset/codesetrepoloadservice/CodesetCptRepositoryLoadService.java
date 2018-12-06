//Do not directly modify this file. File Generated on 2018-10-10T11:36:38.653.
package com.dhp.codesetloadservice;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@Log4j2
public class OptumCptAgeRangeDataLoadService extends DataLoadService {
    @Autowired
    private OptumCodesetFileRepository optumCodesetFileRepository;
    @Autowired
    private OptumCptAgeRangeRepository repository;

    @Value("${unzip.folder}")
    private String unzipFolder;

    @Override
    public Object execute(OptumCodesetFile codesetFile) throws Exception {

        log.info("Check if we need to delete before reloading. mostly will be used for re-running the job.");
        Collection<OptumCptAgeRange> toCleanup = repository.findByFile_OptumCodesetFileSk(codesetFile.getOptumCodesetFileSk());
        repository.deleteAll(toCleanup);
        log.info("processing file record: " + codesetFile);
        ZipFile zipFile = new ZipFile(new File(unzipFolder, codesetFile.getOptumCodesetFileIdentity().getZipFileName()));
        ZipEntry entry = zipFile.getEntry(codesetFile.getOptumCodesetFileIdentity().getFileName());
        InputStream in = zipFile.getInputStream(entry);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String firstLine = reader.readLine();
        String delimiter = codesetFile.getConfig().getDelimiter();
        String[] columns = firstLine.split(delimiter);
        List<OptumCptAgeRange> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            OptumCptAgeRange loadData = new OptumCptAgeRange();
            int i = 0;
            for (String fieldName : columns) {
                String[] lineData = line.split(delimiter, -1);
                PropertyUtils.setSimpleProperty(loadData, toJavaFieldName(fieldName), lineData[i]);
                i++;
            }
            loadData.setFile(codesetFile);
            list.add(loadData);
        }
        log.info("Total record to be saved: " + list.size());
        //TODO before saving delete the data if it is present
        repository.saveAll(list);
        log.info("Saved all data successfully.");
        codesetFile.setProcessStatus("Y");
        optumCodesetFileRepository.save(codesetFile);
        return null;
    }

    @Override
    public String getType() {
        return "OPTUM_CPT_AGE_RANGE";
    }
}

