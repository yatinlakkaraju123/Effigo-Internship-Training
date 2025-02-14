package com.myprojects.Expenses.Repositories;

import com.myprojects.Expenses.Entities.Authorities;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities,Long> {
    Optional<Authorities> findByAuthority(String authority);
    @Modifying
    @Transactional
    @Query(value = "delete from user_authorities where user_id=?",nativeQuery = true)
    public void deleteAllByUser(int userId);
}
