import React, { createContext, useContext, useState } from "react";
export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)
import { executeJWTAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";
function AuthProvider({children}) {
  const [isAuthenticated,setIsAuthenticated] = useState(false)
  const [username, setUsername] = useState(null)
  const [token,setToken] = useState(null)


  async function login(username, password) {

    try {
      const response = await executeJWTAuthenticationService(username,password)
      console.log(response.status)
      if (response.status == 200) {
        const jwtToken = 'Bearer '+response.data.token
        setUsername(username);
        setIsAuthenticated(true);
        setToken(jwtToken)
        apiClient.interceptors.request.use(
          (config) => {
            console.log("intercepting and adding token")
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
    <AuthContext.Provider value={{login,isAuthenticated,username,token}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider
