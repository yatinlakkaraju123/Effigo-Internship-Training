package com.myprojects.Expenses.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class DateRangeRequestDTO {

    private Date from;
    private Date to;
}
