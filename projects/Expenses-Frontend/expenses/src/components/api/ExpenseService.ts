import { ExpenseInterface } from "../../Interfaces";
import { apiClient } from "./ApiClient";

export const retrieveAllExpenses = async()=>{
    return apiClient.get('api/expenses/retrieveAll')
}
export const retrieveExpenseById = async(id:number)=>{
    return apiClient.get(`api/expenses/retrieve/${id}`)
}
export const addExpense = async(userId:number,categoryId:number,expense:ExpenseInterface)=>{
    return apiClient.post(`api/expenses/create/user/${userId}/category/${categoryId}`
        ,{
            name:expense.name,
            price:expense.price,
            description:expense.description,
            date: expense.date
        }
    )
}
export const updateExpense = async(expenseId:number,expense:ExpenseInterface)=>{
    return apiClient.put(`api/expenses/update/${expenseId}`,
        {
            name:expense.name,
            price:expense.price,
            description:expense.description,
            date: expense.date
        }
    )
}
export const deleteExpense = async(expenseId:number)=>{
    return apiClient.delete(`api/expenses/delete/${expenseId}`)
}
export const retrieveExpensesByUser= async(userId:number)=>{
    return apiClient.get(`api/users/retrieveExpenses/${userId}`)
}
export const retrieveExpensesByUserAndCategory = async(userId:number,categoryId:number)=>{
    return apiClient.get(`api/users/retrieveExpenses/user/${userId}/category/${categoryId}`)
}
