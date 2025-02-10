package com.myprojects.LMS.Repositories;

import com.myprojects.LMS.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query(value = "select id from users where username=?",nativeQuery = true)
    int findIdByUsername(String username);// Method provided by JpaRepository


}
