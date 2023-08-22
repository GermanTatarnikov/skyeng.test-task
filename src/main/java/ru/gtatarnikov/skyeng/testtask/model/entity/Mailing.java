package ru.gtatarnikov.skyeng.testtask.model.entity;

import jakarta.persistence.*;
import ru.gtatarnikov.skyeng.testtask.model.enumeration.MailingType;
import ru.gtatarnikov.skyeng.testtask.model.enumeration.Status;

@Entity
@Table(name = "mailing")
public class Mailing {
    @Id
    @SequenceGenerator(name = "mailing_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mailing_id_generator")
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MailingType type;

    @Column(name = "recipient_index", nullable = false)
    private String recipientIndex;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
