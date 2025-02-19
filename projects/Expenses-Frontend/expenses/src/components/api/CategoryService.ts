import { apiClient } from "./ApiClient";

export const retrieveAllCategories = async()=>{
    return apiClient.get('/api/category/retrieveAll')
}
export const retrieveCategoryById = async(id:number)=>{
    return apiClient.get(`/api/category/retrieve/${id}`)
}
export const retrieveCategoriesByUsers = async(userId:number)=>{
    return apiClient.get(`/api/category/retrieveAll/user/${userId}`)
}
export const addCategory = async (name:string,userId:number)=>{
    return apiClient.post(`/api/category/create/user/${userId}`,{
        name:name
    })
}

export const updateCategory = async (id:number,name:string)=>{
    return apiClient.put(`/api/category/update/${id}`,{
        name:name
    })
}
export const deleteCategory = async (id:number)=>{
    return apiClient.delete(`/api/category/delete/${id}`)
}