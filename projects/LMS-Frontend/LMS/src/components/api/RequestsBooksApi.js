import axios from "axios"
import { apiClient } from "./ApiClient"


export const retrieveAllRequests= ()=>{
    return apiClient.get('/api/getRequests')
}

export const createRequest = (userId,bookId)=>{
    return apiClient.post(`/api/request/user/${userId}/book/${bookId}`)
}

export const deleteRequest = (reqId)=>{
    return apiClient.delete(`/api/request/${reqId}`)
}