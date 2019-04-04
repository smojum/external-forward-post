package com.dhp.providerloadbatch;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Log4j2
public class ProviderLoadController {

    @Value("${excel.file-path}")
    private String filePath;
    @Autowired
    private ProviderLoadService providerLoadService;

    @PostMapping("/load")
    private String loadRawData(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("File uplo1aded size: " + file.getBytes().length);
        File inputFile = new File(filePath);
        providerLoadService.load(inputFile);
        return "Success : file size uploaded: " + file.getBytes().length;
    }

    @GetMapping("/loaddata")
    private String loadRawData() throws IOException {
        File inputFile = new File(filePath);
        providerLoadService.load(inputFile);
        return "Success : file uploaded: ";
    }
}
