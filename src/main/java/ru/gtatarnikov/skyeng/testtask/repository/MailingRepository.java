package ru.gtatarnikov.skyeng.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.skyeng.testtask.model.entity.Mailing;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
