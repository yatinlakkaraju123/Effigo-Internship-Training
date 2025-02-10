import axios from "axios"
import { apiClient } from "./ApiClient"
export const executeJWTAuthenticationService = (username,password)=>
    apiClient.post('/authenticate',{
        username,password
    })

    export const registerUser = (username,password)=>{
        return apiClient.post('/api/register',{
            username,password
        })
    }