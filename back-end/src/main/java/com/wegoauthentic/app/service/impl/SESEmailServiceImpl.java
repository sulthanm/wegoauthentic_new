package com.wegoauthentic.app.service.impl;

import com.wegoauthentic.app.service.EmailService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Service
public class SESEmailServiceImpl implements EmailService {

    private final SesClient sesClient = SesClient.builder()
            .region(Region.AP_SOUTH_1)
            .build();

    @Override
    public void sendOtpToMail(String toEmail, String otp) {
        String subject = "Your OTP Code";
        String body = "Your OTP is: " + otp + ". It expires in 5 minutes.";

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder()
                        .toAddresses(toEmail)
                        .build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).build())
                        .body(Body.builder()
                                .text(Content.builder().data(body).build())
                                .build())
                        .build())
                .source("wegoauthentic@gmail.com")
                .build();

        sesClient.sendEmail(request);
    }
}
