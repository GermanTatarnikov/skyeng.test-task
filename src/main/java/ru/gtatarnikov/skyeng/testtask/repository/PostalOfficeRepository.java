package ru.gtatarnikov.skyeng.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.skyeng.testtask.model.entity.PostalOffice;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, Long> {
}
