import axios from "axios"
import { apiClient } from "./ApiClient"

export const retrieveAllBorrowRecords = ()=>{
    return apiClient.get('/api/getBorrows')
}

export const createBorrowRecord = (userId,bookId,borrow)=>{
    return apiClient.post(`api/borrow/users/${userId}/books/${bookId}`,borrow,{ headers: {
        'Content-Type': 'application/json', // Specify JSON content
    },})
}

export const createReturnRecord = (borrowId,returnRecord)=>{
    return apiClient.post(`api/return/borrow/${borrowId}`,returnRecord,{ headers: {
        'Content-Type': 'application/json', // Specify JSON content
    },})
}