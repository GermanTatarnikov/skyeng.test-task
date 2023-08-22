package ru.gtatarnikov.skyeng.testtask.model.dto;

import ru.gtatarnikov.skyeng.testtask.model.enumeration.MailingType;

public class MailingDto {
    private Long id;

    private MailingType type;

    private String recipientIndex;

    private String recipientName;

    private String recipientAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailingType getType() {
        return type;
    }

    public void setType(MailingType type) {
        this.type = type;
    }

    public String getRecipientIndex() {
        return recipientIndex;
    }

    public void setRecipientIndex(String recipientIndex) {
        this.recipientIndex = recipientIndex;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
}
