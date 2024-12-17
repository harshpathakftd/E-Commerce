package com.ex.smt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ex.smt.entities.User;

public interface UserRepo extends JpaRepository<User , String>{

  
   
}