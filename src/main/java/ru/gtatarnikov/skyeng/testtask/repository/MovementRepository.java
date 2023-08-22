package ru.gtatarnikov.skyeng.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.skyeng.testtask.model.entity.Movement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    Optional<List<Movement>> findAllByMailingId(Long mailingId);

    @Query(value = "select m.postalOffice.id from Movement m where m.mailing.id = ?1 " +
            "and m.action = ?2 " +
            "AND m.movementDateTime = (select min(m.movementDateTime) from Movement m)")
    Long getLatestPostalOfficeIdByMailingIdAndAction(Long mailingId, String action);
}
