package com.myprojects.Expenses.Repositories;

import com.myprojects.Expenses.Entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Long> {
}
