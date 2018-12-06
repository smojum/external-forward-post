package com.dhp.codesetloadservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataLoadServiceFactory {

    @Autowired
    private OptumCodesetConfigRepository optumCodesetConfigRepository;
    @Autowired
    private List<DataLoadService> services;

    private static final Map<String, DataLoadService> myServiceCache = new HashMap<>();

    @PostConstruct
    public void initMyServiceCache() {
        List<OptumCodesetConfig> configs = optumCodesetConfigRepository.findAll();
        for (DataLoadService service : services) {
            myServiceCache.put(service.getType(), service);
        }
    }

    public static DataLoadService getService(String type) {
        DataLoadService service = myServiceCache.get(type);
        if (service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }
}
