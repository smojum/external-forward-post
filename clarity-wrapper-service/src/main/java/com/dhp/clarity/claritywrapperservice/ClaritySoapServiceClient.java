package com.dhp.eob;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.clarity.webservice.AttributeValuePair;
import com.clarity.webservice.ClarityService;
import com.clarity.webservice.ClarityWebServiceSEI;
import com.clarity.webservice.GetProductAsPDFRequest;
import com.clarity.webservice.GetProductAsPDFResponse;

@Component("eobSecureClarityDocumentClient")
@Configuration
@Import(EobSecureClarityDocumentConfiguration.class)
public class EobSecureClarityDocumentClient {

	private static final String WSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private static final QName SERVICE_NAME = new QName("http://webservice.clarity.com/", "ClarityService");
	
	@Value("${clarity-wsdl}")
	private String WSDL;
	
	@Value("${clarity-endpoint}")
	private String ENDPOINT;

	public byte[] getEobDocumentFromClarity(String memberId, String claimNumber, String processingDate) {
		byte[] bFile = null;
		SpringBusFactory bf = new SpringBusFactory();
		URL busFile = EobSecureClarityDocumentClient.class.getResource("/clarity-cxf.xml");
		Bus bus = bf.createBus(busFile.toString());
		BusFactory.setDefaultBus(bus);
		/*
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, "Signature Timestamp");
		outProps.put(WSHandlerConstants.SIGNATURE_USER, "clarity-dvlx.deanhealthplan.com");
		outProps.put("passwordCallbackClass", "demo.wssec.client.UTPasswordCallback");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "etc/dean-sign.properties");
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
				"{Content}{" + WSU_NS + "}Timestamp;" + "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body;");

		outProps.put(WSHandlerConstants.SIG_ALGO, WSConstants.RSA);
		outProps.put(WSHandlerConstants.SIG_DIGEST_ALGO, WSConstants.SHA1);
		*/
		URL wsdlURL = null;
		try {
			//TODO put in properties file
//			WSDL = "https://api276.clarityssi.com/DeanHealthEOBWebService/wsdl/e_service.wsdl";
//			WSDL = "https://api276uat.clarityssi.net:446/DeanHealthEOBWebService/services/ClarityServicePort?wsdl";
			wsdlURL = new URL(WSDL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		ClarityService ss = new ClarityService(wsdlURL, SERVICE_NAME);
		ClarityWebServiceSEI port = ss.getClarityServicePort();
		((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		client.getRequestContext().put("security.signature.properties", "props/dean-sign.properties");
		client.getRequestContext().put("security.encryption.properties", "props/clarity-sign.properties");
		client.getRequestContext().put("security.encryption.username", "clarity-prod");
		client.getRequestContext().put("security.callback-handler", "com.dhp.eob.UTPasswordCallback");

		System.out.println("Invoking getProductAsPDF...");

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
		batchDate.setValue(EobDocumentUtil.formatDate(processingDate, "yyyyMMdd", "MM/dd/yyyy"));

		searchCriteria.add(batchDate);

		GetProductAsPDFResponse response = port.getProductAsPDF(request);
		try {
			if(response.getResults().size() == 0) {
				return null;
			}
			bFile = EobDocumentUtil.toBytes(response.getResults().get(0).getProductPDF());
			if (port instanceof Closeable) {
				((Closeable) port).close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bus.shutdown(true);
		List<Integer> pageNumbers = new ArrayList<Integer>();
		pageNumbers.add(2);
		byte[] removedPage;
		try {
			removedPage = EobDocumentUtil.removePage(bFile, pageNumbers);
		} catch (IOException e) {
			removedPage = bFile;
		}
		return removedPage;
	}

}
