package ru.gtatarnikov.skyeng.testtask.model.entity;

import jakarta.persistence.*;
import ru.gtatarnikov.skyeng.testtask.model.enumeration.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @SequenceGenerator(name = "movement_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movement_id_generator")
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "mailing_id", nullable = false)
    private Mailing mailing;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "postal_office_id", nullable = false)
    private PostalOffice postalOffice;

    @Column(name = "movement_date_time", nullable = false)
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

    public Mailing getMailing() {
        return mailing;
    }

    public void setMailing(Mailing mailing) {
        this.mailing = mailing;
    }

    public PostalOffice getPostalOffice() {
        return postalOffice;
    }

    public void setPostalOffice(PostalOffice postalOffice) {
        this.postalOffice = postalOffice;
    }

    public LocalDateTime getMovementDateTime() {
        return movementDateTime;
    }

    public void setMovementDateTime(LocalDateTime movementDateTime) {
        this.movementDateTime = movementDateTime;
    }
}
