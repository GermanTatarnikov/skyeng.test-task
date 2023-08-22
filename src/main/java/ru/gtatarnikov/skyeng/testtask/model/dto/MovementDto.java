package ru.gtatarnikov.skyeng.testtask.model.dto;

import java.time.LocalDateTime;

public class MovementDto {
    private Long id;

    private String action;

    private MailingDto mailing;

    private PostalOfficeDto postalOffice;

    private LocalDateTime movementDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MailingDto getMailing() {
        return mailing;
    }

    public void setMailing(MailingDto mailing) {
        this.mailing = mailing;
    }

    public PostalOfficeDto getPostalOffice() {
        return postalOffice;
    }

    public void setPostalOffice(PostalOfficeDto postalOffice) {
        this.postalOffice = postalOffice;
    }

    public LocalDateTime getMovementDateTime() {
        return movementDateTime;
    }

    public void setMovementDateTime(LocalDateTime movementDateTime) {
        this.movementDateTime = movementDateTime;
    }
}
