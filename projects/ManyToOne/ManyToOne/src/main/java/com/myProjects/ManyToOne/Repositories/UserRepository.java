package com.myProjects.ManyToOne.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProjects.ManyToOne.Entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
