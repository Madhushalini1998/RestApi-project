package com.TestProject.TestProject.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TestProject.TestProject.model.Book;
import com.TestProject.TestProject.model.BorrowRecord;



@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    // Custom method to find active borrowed book
    Optional<BorrowRecord> findByBookAndReturnDateIsNull(Book book);
}
