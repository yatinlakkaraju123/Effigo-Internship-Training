package com.myprojects.Expenses.DTOs;

import lombok.Data;

@Data
public class UserRequestDTO {
    private long userId;
    private String username;
    private String password;

}
