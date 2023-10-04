package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepo extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email=:e")
    public User getUserByEmail(@Param("e") String emailId );

//    @Query("SELECT u FROM User u WHERE u.password=:p")
//    public User getUserByPassword(@Param("p") String password );

}
