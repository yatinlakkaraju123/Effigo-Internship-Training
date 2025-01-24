import React, { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService, executeJWTAuthenticationService } from "../api/AuthenticationApiService.js";
import { apiClient } from "../api/ApiClient.js"
export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)
function AuthProvider({ children }) {
  const [number, setNumber] = useState(0)
  const [username, setUsername] = useState(null)
  const [isAuthenticated, SetisAuthenticated] = useState(false)
  const [token, setToken] = useState(null)
  const valueToShare = { number, isAuthenticated, SetisAuthenticated }

  // async function login(username, password) {
  //   const baToken = 'Basic ' + window.btoa(username + ":" + password)
  //   try {
  //     const response = await executeBasicAuthenticationService(baToken)

  //     if (response.status == 200) {
  //       setUsername(username);
  //       SetisAuthenticated(true);
  //       setToken(baToken)
  //       apiClient.interceptors.request.use(
  //         (config) => {
  //           console.log("intercepting and adding token")
  //           config.headers.Authorization = baToken
  //           return config;
  //         }
  //       )
  //       return true
  //     } else {
  //       logout()
  //       return false
  //     }
  //   } catch (error) {
  //     logout()

  //     return false
  //   }
    async function login(username, password) {

      try {
        const response = await executeJWTAuthenticationService(username,password)

        if (response.status == 200) {
          const jwtToken = 'Bearer '+response.data.token
          setUsername(username);
          SetisAuthenticated(true);
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

      // if (username == "in28minutes" && password == "123") {
      //     setUsername(username)
      //     SetisAuthenticated(true)
      //     return true
      //   }
      //   else {
      //     SetisAuthenticated(false)
      //     setUsername(null)
      //  return false
      //   }
    }
    function logout() {
      SetisAuthenticated(false)
      setUsername(null)

      setToken(null)

    }
    return (
      <AuthContext.Provider value={{ number, isAuthenticated, SetisAuthenticated, login, logout, username, token }}>

        {children}
      </AuthContext.Provider>
    )
  }

  export default AuthProvider
