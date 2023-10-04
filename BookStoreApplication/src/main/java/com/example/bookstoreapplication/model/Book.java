package com.example.bookstoreapplication.model;


import com.example.bookstoreapplication.dto.BookDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookId;
    public String bookName;
    public String autherName;
    public String bookDescription;
    public String bookImg;
    public int price;
    public int quantity;

    public Book (BookDto bookDto){
        this.bookName = bookDto.bookName;
        this.autherName = bookDto.autherName;
        this.bookDescription = bookDto.bookDescription;
        this.bookImg = bookDto.bookImg;
        this.price = bookDto.price;
        this.quantity = bookDto.quantity;
    }

}
