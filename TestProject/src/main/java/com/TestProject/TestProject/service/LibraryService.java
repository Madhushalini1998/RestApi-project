package com.TestProject.TestProject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TestProject.TestProject.model.Book;
import com.TestProject.TestProject.model.BorrowRecord;
import com.TestProject.TestProject.model.Borrower;
import com.TestProject.TestProject.repository.BookRepository;
import com.TestProject.TestProject.repository.BorrowRecordRepository;
import com.TestProject.TestProject.repository.BorrowRepository;

@Service
public class LibraryService {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	private BorrowRepository borrowRepository;

	@Autowired
	private BorrowRecordRepository borrowRecordRepository;

	// Register a new book
	public Book registerBook(Book book) {
		return bookRepository.save(book);
	}

	// Register a new borrower
	public Borrower registerBorrower(Borrower borrower) {
		return borrowRepository.save(borrower);
	}

	// Get a list of all books
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// Borrow a book
	// Borrow a book
	public String borrowBook(Long bookId, Long borrowerId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Optional<Borrower> optionalBorrower = borrowRepository.findById(borrowerId);

		if (optionalBook.isEmpty()) {
			return "Book not found.";
		}

		if (optionalBorrower.isEmpty()) {
			return "Borrower not found.";
		}

		Book book = optionalBook.get();

		if (book.isBorrowed()) {
			return "Book is already borrowed.";
		}

		// ✅ Mark book as borrowed
		book.setBorrowed(true);
		bookRepository.save(book);

		// ✅ Save borrow record
		BorrowRecord record = new BorrowRecord();
		record.setBook(book);
		record.setBorrower(optionalBorrower.get());
		record.setBorrowDate(LocalDateTime.now());

		borrowRecordRepository.save(record);

		return "Book borrowed successfully.";
	}

	// Return a book
	public String returnBook(Long bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);

		if (optionalBook.isEmpty()) {
			return "Book not found.";
		}

		Book book = optionalBook.get();

		Optional<BorrowRecord> optionalRecord = borrowRecordRepository.findByBookAndReturnDateIsNull(book);

		if (optionalRecord.isEmpty()) {
			return "This book is not currently borrowed.";
		}

		BorrowRecord record = optionalRecord.get();
		record.setReturnDate(LocalDateTime.now());

		book.setBorrowed(false);

		borrowRecordRepository.save(record);
		bookRepository.save(book);

		return "Book returned successfully.";
	}

}
