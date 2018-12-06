package com.dhp.codesetloadservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Collection;

@Component
@Log4j2
public class CodesetLoadServiceRunner implements ApplicationRunner {
    private final EntityManager em;
    private final TransactionTemplate transactionTemplate;
    private final OptumCodesetFileRepository optumCodesetFileRepository;

    @Autowired
    private DataLoadServiceFactory dataLoadServiceFactory;


    CodesetLoadServiceRunner(EntityManager em, TransactionTemplate transactionTemplate,
                             OptumCodesetFileRepository optumCodesetFileRepository) {
        this.em = em;
        this.transactionTemplate = transactionTemplate;
        this.optumCodesetFileRepository = optumCodesetFileRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Running Loading of files.");
        Collection<OptumCodesetFile> unprocessedFiles = optumCodesetFileRepository.findByProcessStatusAndConfigNotNullAndConfig_IsInScopeOrderByOptumCodesetFileIdentity_LastModifiedTsAsc("N", true);
        log.info("Total number of unprocessed file: " + unprocessedFiles.size());
        for (OptumCodesetFile unprocessedFile : unprocessedFiles) {
            try {
                loadFile(unprocessedFile);
            } catch (Exception e) {
                log.error("error while loading data", e);
                e.printStackTrace();
            }
        }
    }

    private void loadFile(OptumCodesetFile unprocessedFile) throws Exception {
        log.info("Processing file: " + unprocessedFile);
        DataLoadService dataLoadService = DataLoadServiceFactory.getService(unprocessedFile.getConfig().getTargetTable());
        log.info("Got the service as: " + dataLoadService.getClass());
        dataLoadService.execute(unprocessedFile);
    }

}
