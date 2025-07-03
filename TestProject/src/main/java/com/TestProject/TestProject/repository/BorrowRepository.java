package com.TestProject.TestProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TestProject.TestProject.model.Borrower;

@Repository
public interface BorrowRepository extends JpaRepository<Borrower, Long> {

}
