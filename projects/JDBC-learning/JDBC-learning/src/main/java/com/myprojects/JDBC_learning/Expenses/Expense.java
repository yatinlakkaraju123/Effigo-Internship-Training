package com.myprojects.JDBC_learning.Expenses;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Expense {
    private int id;
    private int UserId;
    private String name;
    private LocalDate date;
}
