package com.myprojects.Expenses.Repositories;

import com.myprojects.Expenses.Entities.Category;
import com.myprojects.Expenses.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    @Query(value = "select * from categories where user_id=?",nativeQuery = true)
    List<Category> retrieveByUser(long userId);
    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM categories c WHERE c.name = ?1 AND c.user_id = ?2", nativeQuery = true)
    boolean existsByNameAndUserId(String name, long userId);


}
