package com.myprojects.JDBC_learning.Expenses.Repository;

import com.myprojects.JDBC_learning.Expenses.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ExpenseRepository {
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(ExpenseRepository.class);
    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static String SelectQuery =
            """
            
            SELECT * FROM Expense;
            """;
    public List<Expense> getAllExpenses(){
        String query = "SELECT EXISTS (" +
                "SELECT 1 " +
                "FROM information_schema.tables " +
                "WHERE table_name = ? AND table_schema = 'public'" +
                ")";
        logger.info("whether table exists:"+(jdbcTemplate.queryForObject(query, Boolean.class, "Expense")));
        List<Expense> expenses = jdbcTemplate.query(SelectQuery,
                new BeanPropertyRowMapper<>(Expense.class));
        return expenses;
    }


}


