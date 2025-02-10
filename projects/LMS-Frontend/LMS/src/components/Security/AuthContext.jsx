import React, { createContext, useContext, useState } from "react";
export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)
import { executeJWTAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";
import { jwtDecode } from "jwt-decode";
function AuthProvider({children}) {
  const [isAuthenticated,setIsAuthenticated] = useState(false)
  const [username, setUsername] = useState(null)
  const [token,setToken] = useState(null)
  const [userId,setUserId] = useState(null)
  const [role,setRole] = useState(null)
  async function login(username, password) {

    try {
      const response = await executeJWTAuthenticationService(username,password)
      //console.log("jwt token:",response)
      if (response.status == 200) {
      const retrievedToken = response.data.token
        const jwtToken = 'Bearer '+retrievedToken
        const decodedToken = jwtDecode(retrievedToken)
        //console.log("decoded token:",decodedToken)
        const userId = decodedToken.userId
        //console.log(decodedToken)
        //console.log(typeof(decodedToken.scope))
        setRole(decodedToken.scope)
        //console.log("role:"+role)
        setUserId(userId)
        setUsername(username);
        setIsAuthenticated(true);
        setToken(jwtToken)
        apiClient.interceptors.request.use(
          (config) => {
            //console.log("intercepting and adding token")
            config.headers.Authorization = jwtToken
            return config;
          }
        )
        return true
      } else {
        logout()
        return false
      }
    } catch (error) {
      logout()

      return false
    }

  }
  function logout() {
    setIsAuthenticated(false)
    setUsername(null)

    setToken(null)

  }
  return (
    <AuthContext.Provider value={{login,isAuthenticated,username,token,logout,userId,role}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider
