package com.dhp.bppa.accum.accummonitorservice;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/")
public class AccumMonitorServiceController {

    @Autowired
    private AccumClaimReconRepository accumClaimReconRepository;

    @Autowired
    private AccumClaimReconAllRepository accumClaimReconAllRepository;

    @Value("${email.recipient}")
    private String recipient;

    @Value("${email.sender}")
    private String sender;

    @Value("${email.host}")
    private String host;

    @Value("${recon.url}")
    private String url;

    @Autowired
    private Environment environment;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = "byclaim/{fileDate}/{claimnum}")
    public Collection<AccumClaimRecon> getByClaimnum(@PathVariable("fileDate") Integer fileDate, @PathVariable("claimnum") String claimnum) {
        log.info("getting the information for claim: " + claimnum);
        return accumClaimReconRepository.findByFileDtAndClmAuthNum(fileDate, claimnum);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = {"byreason/{fileDate}/{notLoadedReason}", "byreason/{fileDate}"})
    @GetMapping
    public Collection<AccumClaimRecon> getByNotLoadedReason(@PathVariable("fileDate") Integer fileDt, @PathVariable("notLoadedReason") Optional<Long> notLoadedReasonCd) {
        if (notLoadedReasonCd.isPresent()) {
            if(0==notLoadedReasonCd.get()) {
                return accumClaimReconAllRepository.findByFileDt(fileDt)
                        .stream()
                        .map(obj -> AccumClaimReconMapper.INSTANCE.convert(obj))
                        .collect(Collectors.toList());

            } else {
                return accumClaimReconRepository.findByFileDtAndAccumClaimReconCode_RxClaimMtvReconCdSk(fileDt, notLoadedReasonCd.get());
            }
        } else {
            return accumClaimReconRepository.findByFileDt(fileDt);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = "byreason/summary/{fileDate}")
    public Collection<AccumClaimReconSummary> getNotLoadedReasonSummary(@PathVariable("fileDate") Integer fileDate) {
        log.info("getting the information for Date: " + fileDate);
        Collection<AccumClaimReconSummary> response = accumClaimReconRepository.dailySummary(fileDate);
        AccumClaimReconSummary all = accumClaimReconRepository.dailySummaryTotal(fileDate);
        AccumClaimReconSummary allClaims = accumClaimReconAllRepository.dailyAllTotal(fileDate);
        if(all != null) {
            response.add(all);
        }
        if (allClaims != null) {
            response.add(allClaims);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = "byreason/summaryemail/{fileDate}")
    public Collection<AccumClaimReconSummary> getNotLoadedReasonSummaryEmail(@PathVariable("fileDate") Integer fileDate) throws MessagingException {
        log.info("getting the information for Date: " + fileDate);
        Collection<AccumClaimReconSummary> response = getNotLoadedReasonSummary(fileDate);
        sendEmail(response);
        return response;
    }

    protected void sendEmail(Collection<AccumClaimReconSummary> summary) throws MessagingException {

        log.info("Summary: " + summary);
        String emailBody = convertToFormattedBody(summary);
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        // MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From Field: adding senders email to from field.
        message.setFrom(new InternetAddress(sender));

        // Set To Field: adding recipient's email to from field.
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        // Set Subject: subject of the email
        message.setSubject("Rx Claim Reconciliation Report: " + environment.getActiveProfiles()[0]);

        // set body of the email.
        message.setText(emailBody);

        // Send email.
        Transport.send(message);
        log.info("Mail successfully sent");
    }

    private String convertToFormattedBody(Collection<AccumClaimReconSummary> summary) {
        StringBuilder body = new StringBuilder();
        body.append("Summary of Rx Claim Reconciliation report.\n\n");
        log.info("Summary Data: " + summary);
        log.info("Summary Data: " + summary.size());
        if(summary == null || summary.size() == 0) {
            body.append("\n No summary data available.");
            return body.toString();
        }
        summary.forEach(data -> body.append("\n").append(data.getFiledate() + "-").append(data.getReason()).append(": ").append(data.getCount()).append("\n"));
        body.append("\n\nFollow the link below for more details.\n");
        body.append(url);
        return body.toString();
    }

}
