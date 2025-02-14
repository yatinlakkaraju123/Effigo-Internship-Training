package com.myprojects.Expenses.DTOs;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private long categoryId;
    private String name;
}
