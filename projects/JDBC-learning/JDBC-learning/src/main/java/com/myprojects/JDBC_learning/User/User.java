package com.myprojects.JDBC_learning.User;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class User {
@Id
private int id;

private String name;
private String email;
}
