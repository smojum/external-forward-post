package com.dhp.clarity.claritywrapperservice;

import com.clarity.webservice.*;
import javassist.NotFoundException;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class ClaritySoapServiceClient {

    private static final QName SERVICE_NAME = new QName("http://webservice.clarity.com/", "ClarityService");

    @Value("${clarity-wsdl}")
    private String WSDL;

    @Value("${clarity-endpoint}")
    private String ENDPOINT;

    private Bus bus;

    private ClarityWebServiceSEI port;

    private void open() {
        SpringBusFactory bf = new SpringBusFactory();
        URL busFile = ClaritySoapServiceClient.class.getResource("/clarity-cxf.xml");
        bus = bf.createBus(busFile.toString());
        BusFactory.setDefaultBus(bus);
        URL wsdlURL = null;
        try {
            wsdlURL = new URL(WSDL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ClarityService ss = new ClarityService(wsdlURL, SERVICE_NAME);
        port = ss.getClarityServicePort();
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);
        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
        client.getRequestContext().put("security.signature.properties", "dean-sign.properties");
        client.getRequestContext().put("security.encryption.properties", "clarity-sign.properties");
        client.getRequestContext().put("security.callback-handler", "com.dhp.clarity.claritywrapperservice.UTPasswordCallback");
    }

    private void close() throws IOException {
        if (port instanceof Closeable) {
            ((Closeable) port).close();
        }
        bus.shutdown(true);
    }

    public byte[] getEobDocumentFromClarity(String memberId, String claimNumber, String processingDate) throws IOException {
        open();
        byte[] bFile = null;

        GetProductAsPDFRequest request = new GetProductAsPDFRequest();
        List<AttributeValuePair> searchCriteria = request.getSearchCriteria();
        AttributeValuePair memberNum = new AttributeValuePair();
        memberNum.setAttribute("member_num");
        memberNum.setValue(memberId);

        searchCriteria.add(memberNum);

        AttributeValuePair claimNum = new AttributeValuePair();
        claimNum.setAttribute("clm_nbr_string");
        claimNum.setValue(claimNumber);

        searchCriteria.add(claimNum);

        AttributeValuePair batchDate = new AttributeValuePair();
        batchDate.setAttribute("batch_dt");
        batchDate.setValue(formatDate(processingDate, "yyyyMMdd", "MM/dd/yyyy"));

        searchCriteria.add(batchDate);

        GetProductAsPDFResponse response = port.getProductAsPDF(request);
        if (response.getResults().size() == 0) {
            return null;
        }
        bFile = toBytes(response.getResults().get(0).getProductPDF());
        close();
        return bFile;
    }

    public byte[] getProductAsPDF(GetProductAsPDFRequest request) throws IOException, NotFoundException {
        open();
        GetProductAsPDFResponse response = port.getProductAsPDF(request);
        close();
        if (response.getResults().size() == 0) {
            throw new NotFoundException("No data found for request: " + request);
        } else if (response.getResults().size() > 1) {
            String output = "";
            response.getResults().stream().forEach(element -> System.out.println(element));
            throw new RuntimeException("Multiple document found. Please try with correct criteria.");
        } else {
            return toBytes(response.getResults().get(0).getProductPDF());
        }
    }

    public GetAvailableProductsResponse getAvailableProductsRequest(String invoiceRecipientId){
        open();
        GetAvailableProductsRequest request = new GetAvailableProductsRequest();

        List<AttributeValuePair> searchCriteria = request.getRequest();
        AttributeValuePair invoiceRecipientIdAttr = new AttributeValuePair();
        invoiceRecipientIdAttr.setAttribute("Invoice_Recipient_ID");
        invoiceRecipientIdAttr.setValue(invoiceRecipientId);
        searchCriteria.add(invoiceRecipientIdAttr);
        GetAvailableProductsResponse response = port.getAvailableProducts(request);
        return response;
    }

    private static byte[] toBytes(DataHandler handler) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        handler.writeTo(output);
        return output.toByteArray();
    }

    private static String formatDate(String inputDate, String inputFormat, String outputFormat) {
        DateFormat originalFormat = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat(outputFormat);
        Date date = null;
        try {
            date = originalFormat.parse(inputDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }
}
