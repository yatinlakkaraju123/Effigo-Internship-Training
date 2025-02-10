import axios from "axios"
import { apiClient } from "./ApiClient"


export const retrieveAllBooking = ()=>{
    return apiClient.get('api/getBooks')
}

export const retrieveBookingById =(id)=>{
    return apiClient.get(`api/getBook/${id}`)
}

export const addBook = (book)=>{
    return apiClient.post('api/insertBooks',book, { headers: {
        'Content-Type': 'application/json', // Specify JSON content
    },})
}
export const updateBook = (id,book)=>{
    return apiClient.put(`api/update/books/${id}`,book,{
        headers:{
            'Content-Type':'application/json'
        }
    })
}
export const deleteBook = (id)=>{
return apiClient.delete(`api/delete/deleteBooks/${id}`)
}