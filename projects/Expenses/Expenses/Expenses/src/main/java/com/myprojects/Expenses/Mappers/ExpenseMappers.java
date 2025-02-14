package com.myprojects.Expenses.Mappers;

import com.myprojects.Expenses.DTOs.ExpenseRequestDTO;
import com.myprojects.Expenses.DTOs.ExpenseResponseDTO;
import com.myprojects.Expenses.Entities.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMappers {
    @Mapping(source = "expenseId",target = "id")
    Expenses ExpenseRequestToExpenses(ExpenseRequestDTO expenseRequestDTO);
    @Mapping(target= "expenseId",source = "id")

    ExpenseResponseDTO ExpensesToExpenseResponse(Expenses expenses);
}
