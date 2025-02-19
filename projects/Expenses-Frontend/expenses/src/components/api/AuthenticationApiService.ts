import { apiClient } from "./ApiClient"
export const executeJWTAuthenticationService = (username:string,password:string)=>
    apiClient.post('/authenticate',{
        username,password
    })

    export const registerUser = (username:string,password:string)=>{
        return apiClient.post('/api/users/create',{
            username,password
        })
    }