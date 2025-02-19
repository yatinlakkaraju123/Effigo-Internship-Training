import { apiClient } from "./ApiClient";

export const retrieveUserById = (id:number)=>{
return apiClient.get(`/api/users/retrieve/${id}`)
}