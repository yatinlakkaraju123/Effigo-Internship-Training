package com.myprojects.Expenses.Repositories;

import com.myprojects.Expenses.Entities.Category;
import com.myprojects.Expenses.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);


}
