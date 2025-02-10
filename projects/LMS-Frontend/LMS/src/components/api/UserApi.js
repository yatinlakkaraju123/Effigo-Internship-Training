import axios from "axios"
import { apiClient } from "./ApiClient"
export const retrieveUserById = (id)=>{
    return apiClient.get(`/api/getUser/${id}`)
}