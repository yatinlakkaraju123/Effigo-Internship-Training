import axios from "axios";

import { apiClient } from "./ApiClient";
export const retrieveAllTodosforUser = (username)=>{
    return apiClient.get(`/users/${username}/todos`)
}   
export const deleteTodo = (username,id)=>{
    return apiClient.delete(`/users/${username}/todos/${id}`)
}

export const retrieveTodoForId = (username,id) =>{
    return apiClient.get(`/users/${username}/todos/${id}`)
}

export const updateTodoForId = (username,id,todo)=>{
    return apiClient.put(`/users/${username}/todos/${id}`,todo, { headers: {
        'Content-Type': 'application/json', // Specify JSON content
    },})
}

export const addTodo = (username,todo)=>{
    return apiClient.post(`/users/${username}/todos`,todo,{
        headers:{
            'Content-Type': 'application/json', // Specify JSON content

        }
    })
}