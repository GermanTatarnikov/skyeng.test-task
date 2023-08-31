package ru.gtatarnikov.skyeng.testtask.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "postal_office")
public class PostalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "index", nullable = false)
    private String index;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "recipient_address")
    private String recipientAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
}
