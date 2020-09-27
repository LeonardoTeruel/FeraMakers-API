package com.teruel.feramakers.service;

import com.teruel.feramakers.exceptions.FeraMakersException;
import com.teruel.feramakers.model.NotificationEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;


public class MailService {

    @Autowired
    private  JavaMailSender mailSender;

    @Autowired
    private  MailActivationBuilderService mailActivationBuilderService;

    Logger log = LoggerFactory.getLogger(MailService.class);

    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new FeraMakersException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }
}
