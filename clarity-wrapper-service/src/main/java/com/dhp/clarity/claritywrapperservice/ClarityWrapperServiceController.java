package com.dhp.clarity.claritywrapperservice;

import com.clarity.webservice.GetAvailableProductsResponse;
import com.clarity.webservice.GetProductAsPDFRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class ClarityWrapperServiceController {

    @Autowired
    private ClaritySoapServiceClient client;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, value = "eob/{memberId}/{claimNumber}/{processDate}")
    public @ResponseBody
    byte[] getEobDocument(@PathVariable("memberId") String memberId, @PathVariable("claimNumber") String claimNumber, @PathVariable("processDate") String processDate) throws IOException {
        return client.getEobDocumentFromClarity(memberId, claimNumber, processDate);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, value = "product/pdf")
    public @ResponseBody
    byte[] getProductAsPDF(@RequestBody GetProductAsPDFRequest request) throws IOException, NotFoundException {
        return client.getProductAsPDF(request);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "invoice")
    public @ResponseBody
    GetAvailableProductsResponse getAvailableProductsRequest(@RequestParam(name = "invoice_recipient_id") String invoiceRecipientId,
                                                             @RequestParam(name = "recipient_id", required = false) String recipientId,
                                                             @RequestParam(name = "invoice_number", required = false) String invoiceNumber,
                                                             @RequestParam(name = "invoice_date", required = false) String invoiceDate) throws IOException {
        return client.getAvailableProductsRequest(invoiceRecipientId, recipientId, invoiceNumber, invoiceDate);
    }

}
