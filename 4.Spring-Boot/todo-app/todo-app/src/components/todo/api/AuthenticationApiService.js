import { apiClient } from "./ApiClient"
import axios from "axios"
export const executeBasicAuthenticationService = (token)=>{
    return axios.get('http://localhost:8080/basicauth',{
        headers:{
            Authorization:token
        }
    })
}

export const executeJWTAuthenticationService = (username,password)=>
    apiClient.post('/authenticate',{
        username,password
    })
