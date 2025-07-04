package com.TestProject.TestProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TestProject.TestProject.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
