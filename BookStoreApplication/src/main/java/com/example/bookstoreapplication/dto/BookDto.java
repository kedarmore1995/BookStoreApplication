package com.example.bookstoreapplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BookDto {

    @Size(min = 3, message = "Name should have more than 3 characters")
    public String bookName;
    @Size(min = 3, message = "autherName should have more than 3 characters")
    public  String  autherName;
    @Size(min = 3, max = 25)
    public String bookDescription;

    @NotEmpty
    public String bookImg;
    @Min(1)
    public int price;

    @Min(1)
    public int quantity;
}
