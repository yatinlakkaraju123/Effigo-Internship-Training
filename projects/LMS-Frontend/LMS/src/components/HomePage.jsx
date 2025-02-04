import React from 'react'
import Header from './fragments/Header'
import Footer from './fragments/Footer'
import LoginPage from './Security/LoginPage'
import { useAuth } from '../components/Security/AuthContext';

function HomePage() {
      const auth = useAuth()
      const username = auth.username
  return (
    <div>
     <Header/>
  
      <h1>Welcome, {username}</h1>
        <Footer/>
    </div>
  )
}

export default HomePage
