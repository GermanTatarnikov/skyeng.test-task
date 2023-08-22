package ru.gtatarnikov.skyeng.testtask.model.dto;

import ru.gtatarnikov.skyeng.testtask.model.enumeration.Status;

import java.time.LocalDateTime;

public class MovementDto {
    private Long id;

    private Status status;

    private MailingDto mailing;

    private PostalOfficeDto postalOffice;

    private LocalDateTime movementDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
