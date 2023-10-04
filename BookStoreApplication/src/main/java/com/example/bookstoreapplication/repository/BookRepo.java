package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {

    @Query("SELECT b FROM Book b WHERE b.bookName=:n")
    public Book getBookByBookName(@Param("n") String bookName);

}
