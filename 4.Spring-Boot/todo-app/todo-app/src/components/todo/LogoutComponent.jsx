import React from 'react'
import { useAuth } from './security/AuthContext'

function LogoutComponent() {
   const auth = useAuth()
auth.logout() 
 return (
    <div>
      You are logged out!
    </div>
  )
}

export default LogoutComponent
