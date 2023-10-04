package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/insert")
    public ResponseEntity<Book> insert(@Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.insert(bookDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getById/{bookId}")
    public ResponseEntity<ResponseDto> getById( @PathVariable int bookId){
        ResponseDto responseDto = new ResponseDto("Book by ID: "+bookId, bookService.getById(bookId));
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int bookId){
        return bookService.deleteById(bookId);
    }

    @GetMapping("/searchByBookName")
    public Book searchByBookName(@RequestParam String bookName){
        return bookService.searchByBookName(bookName);
    }

    @PutMapping("/updateBook/{bookId}")
    public Book updateBookById(@PathVariable int bookId,@Valid @RequestBody BookDto bookDto){
        return bookService.updateBookById(bookId,bookDto);
    }

    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int bookId,@RequestParam int quantity){
        return bookService.updateQuantity(bookId, quantity);
    }

    @GetMapping("/sortAsc")
    public List<Book> sortAsc(){
        return bookService.sortAsc();
    }

    @GetMapping("/sortDesc")
    public List<Book> sortDesc(){
        return bookService.sortDesc();
    }
}
