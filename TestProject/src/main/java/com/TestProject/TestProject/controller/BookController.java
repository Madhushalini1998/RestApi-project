package com.TestProject.TestProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TestProject.TestProject.model.Book;
import com.TestProject.TestProject.model.Borrower;
import com.TestProject.TestProject.service.LibraryService;

import jakarta.validation.Valid;

@RestController
public class BookController {

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/borrowers")
	public ResponseEntity<Borrower> registerBorrower(@RequestBody @Valid Borrower borrower) {
		return ResponseEntity.ok(libraryService.registerBorrower(borrower));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> registerBook(@RequestBody @Valid Book book) {
		return ResponseEntity.ok(libraryService.registerBook(book));
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> listBooks() {
		return ResponseEntity.ok(libraryService.getAllBooks());
	}

	@PostMapping("/borrow/{bookId}")
	public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
		libraryService.borrowBook(bookId, borrowerId);
		return ResponseEntity.ok("Book borrowed successfully");
	}

	@PostMapping("/return/{bookId}")
	public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
		libraryService.returnBook(bookId);
		return ResponseEntity.ok("Book returned successfully");
	}
}
