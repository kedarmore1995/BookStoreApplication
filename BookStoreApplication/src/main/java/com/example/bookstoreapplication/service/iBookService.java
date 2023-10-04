package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iBookService {
    public Book insert(BookDto bookDto);
    public List<Book> getAllBooks();
    public Book getById(int bookId);
    public ResponseEntity<ResponseDto> deleteById(int bookId);
    public Book searchByBookName(String bookName);
    public Book updateBookById(int bookId, BookDto bookDto);
    public ResponseEntity<ResponseDto> updateQuantity(int bookId, int quantity);
    public List<Book> sortAsc();
    public List<Book> sortDesc();

}
