package ru.financialliteracy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.financialliteracy.entities.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> { }