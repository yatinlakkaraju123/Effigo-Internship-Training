package com.myprojects.Expenses.DTOs;

import com.myprojects.Expenses.Entities.Expenses;
import com.myprojects.Expenses.Entities.Profile;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private long userId;
    private String username;
    private Profile profile;
    private List<Expenses> expenses;
}
