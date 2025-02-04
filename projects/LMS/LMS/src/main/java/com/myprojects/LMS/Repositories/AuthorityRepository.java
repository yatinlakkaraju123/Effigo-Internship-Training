package com.myprojects.LMS.Repositories;

import com.myprojects.LMS.Entities.Authority;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    Optional<Authority> findByAuthority(String authority);
    @Modifying
    @Transactional
    @Query(value = "delete from user_authorities where user_id=?",nativeQuery = true)
    public void deleteAllByUser(int userId);
}
