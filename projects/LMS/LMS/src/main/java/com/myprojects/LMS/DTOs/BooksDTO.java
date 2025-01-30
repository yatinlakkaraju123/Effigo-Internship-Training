package com.myprojects.LMS.DTOs;

import com.myprojects.LMS.Entities.User;
import lombok.Data;

import java.util.List;
@Data
public class BooksDTO {
    private int id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    private List<User> users;

}
