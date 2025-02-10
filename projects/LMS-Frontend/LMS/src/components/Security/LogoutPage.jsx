import React, { useEffect } from 'react'
import { useAuth } from './AuthContext'
import Header from '../fragments/Header'
import LoginNavbar from '../navbars/LoginNavbar'
function LogoutPage() {
  const auth = useAuth()
  useEffect(()=>{
    auth.logout()
  },[])
  return (
    <div>
      <LoginNavbar/>
      <div className='container'>
      You are logged out
      </div>
      
    </div>
  )
}

export default LogoutPage
