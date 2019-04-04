package com.dhp.providerloadbatch;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.poi.ss.usermodel.*;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

@Service
@Log4j2
public class ProviderLoadService {
    static RestClient client;
    static RestHighLevelClient highLevelClient;
    static {
        client = RestClient.builder(
                new HttpHost("dhpxdevopsd1.dhp.ad.deanhealth.com", 9200, "http")).build();
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("dhpxdevopsd1.dhp.ad.deanhealth.com", 9200, "http")));
    }

    public void load(File inputFile) throws IOException {
        Workbook workbook = WorkbookFactory.create(inputFile);
        Sheet sheet = workbook.getSheetAt(0);
        Integer columns = getColumnCount(sheet.getRow(0));
        Integer items = getItemCount(sheet, 1);
        log.info("Deleting Index ...");
        deleteIndex();
        createIndexMapping();
        readRows(sheet, columns, items);
    }

    private void createIndexMapping() throws IOException {
        log.info("creating mapping.");
        Request request = new Request("PUT", "/provider_raw");
        request.setJsonEntity(getIndexMapping());
        Response response = client.performRequest(request);
        log.info(response);
    }

    private String getIndexMapping() throws IOException {
        File file = ResourceUtils.getFile("classpath:provider_raw_mapping.json");
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded, Charset.defaultCharset());
    }

    private void deleteIndex() {
        try{
            Request request = new Request("DELETE", "/provider_raw");
            Response response = client.performRequest(request);
            log.info("Delete Response: " + response);
        } catch (Exception ex) {
            log.warn("Delete Not successful", ex);
        }

    }

    private int getColumnCount(Row row) {
        Iterator<Cell> iterator = row.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            DataFormatter dataFormatter = new DataFormatter();
            if (dataFormatter.formatCellValue(cell) != null) {
                count++;
            } else {
                break;
            }
        }
        log.info("Number of columns: " + count);
        return count;
    }

    private int getItemCount(Sheet sheet, int offset) {
        int count = 0;
        Row row = sheet.getRow(offset);

        while (row != null) {
            count++;
            row = sheet.getRow(offset + count);
        }
        log.info("Number of rows: " + count);
        return count;
    }

    private void readRows(Sheet sheet, int columnsCount, int items) throws IOException {
        Integer columnRow = 0;
        String[] columns = getColumns(sheet.getRow(columnRow), columnsCount);

        for (int i = 0; i < items; i++) {
            String json = getRow(columns, sheet.getRow(i + columnRow + 1), columnsCount);
            Request request = new Request("PUT", "/provider_raw/_doc/" + "RW" + String.format("%010d", i)); //TODO - generate id dynanmically
            request.setJsonEntity(json);
            log.info(request);
            Response response = client.performRequest(request);
            log.info(response);
        }
    }


    private String[] getColumns(Row row, Integer columns) {
        String[] titles = new String[columns];
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.getCell(i);
            String title = dataFormatter.formatCellValue(cell);
            titles[i] = getNewColumn(titles, title);
        }
        return titles;
    }

    public String getNewColumn(String[] arr, String columnName) {
        if(Arrays.asList(arr).contains(columnName))
            return columnName + UUID.randomUUID();
        else
            return columnName;
    }


    private String getRow(String[] titles, Row row, Integer columns) {

        String json = "{";
        DataFormatter dataFormatter = new DataFormatter();
        for (int index = 0; index < columns; index++) {
            if(row == null) {
                log.warn("Should not be here:" + index);
                continue;
            }
            Cell cell = row.getCell(index);
            if(cell != null) {
                json = json + "\"" + titles[index] + "\":\"" + dataFormatter.formatCellValue(cell) + "\",";
            } else {
                json = json + "\"" + titles[index] + "\":\"\",";
            }
        }
        json = json.replaceAll(",$", "");
        json = json + "}";
        return json;
    }


}
