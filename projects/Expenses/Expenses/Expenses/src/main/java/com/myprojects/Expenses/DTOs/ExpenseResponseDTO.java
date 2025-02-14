package com.myprojects.Expenses.DTOs;

import com.myprojects.Expenses.Entities.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseResponseDTO {
    private long expenseId;
    private String name;
    private int price;
    private String description;
    private Date date;
    private Category category;
}
