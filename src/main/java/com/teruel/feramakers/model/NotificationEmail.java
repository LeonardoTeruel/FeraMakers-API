package com.teruel.feramakers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NotificationEmail {

    @Id
    @GeneratedValue
    private Long notificationEmailId;

    private String subject;

    private String recipient;

    private String body;

    public Long getNotificationEmailId() {
        return notificationEmailId;
    }

    public void setNotificationEmailId(Long notificationEmailId) {
        this.notificationEmailId = notificationEmailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
