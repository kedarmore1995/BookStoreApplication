package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.exception.BookNotFoundException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService implements iBookService {

//    (insert,getAll,getbyID,delete,searchbyBookName,updateBookbyID,sortingAsce,
//    sortingDesc,updateQuantity)

    @Autowired
    BookRepo bookRepo;
    @Override
    public Book insert(BookDto bookDto){
        Book book = new Book(bookDto);
        bookRepo.save(book);
        return book;
    }
    @Override
    public List<Book> getAllBooks(){
        List<Book> bookList =bookRepo.findAll();
        return bookList;
    }
    @Override
    public Book getById(int bookId){
        if (bookRepo.existsById(bookId)){
            return bookRepo.findById(bookId).get();
        }else {
            throw new BookNotFoundException("Book not found. Check UserId.");
        }
    }
    @Override
    public ResponseEntity<ResponseDto> deleteById(int bookId){
        if (bookRepo.existsById(bookId)){
            bookRepo.deleteById(bookId);
            ResponseDto responseDto = new ResponseDto("Book Deleted.", false);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else {
            throw new BookNotFoundException("Book not found. Check UserId.");
        }
    }
    @Override
    public Book searchByBookName(String bookName){
        Book book = bookRepo.getBookByBookName(bookName);
        if (book!=null){
            return book;
        }else {
            throw new BookNotFoundException("Book Not found. Book Name Incorrect.");
        }
    }

    @Override
    public Book updateBookById(int bookId, BookDto bookDto){
        if (bookRepo.existsById(bookId)){
            Book book = new Book(bookDto);
            book.setBookId(bookId);
            bookRepo.save(book);
            return book;
        }else {
            throw new BookNotFoundException("Book not found. Check bookId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateQuantity(int bookId, int quantity){
        if(bookRepo.existsById(bookId)){
            Book book = bookRepo.findById(bookId).get();
            book.setQuantity(quantity);
            bookRepo.save(book);
            ResponseDto responseDto = new ResponseDto("Book Quantity Updated", book );
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }else {
            throw new BookNotFoundException("No book found. Check bookId.");
        }
    }

    @Override
    public List<Book> sortAsc(){
        List<Book> bookList = bookRepo.findAll(Sort.by(Sort.Direction.ASC, "bookName"));
        return  bookList;
    }

    @Override
    public List<Book> sortDesc(){
        List<Book> bookList = bookRepo.findAll(Sort.by(Sort.Direction.DESC, "bookName"));
        return  bookList;
    }
}
