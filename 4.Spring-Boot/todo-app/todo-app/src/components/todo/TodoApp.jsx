import React from 'react'
import "./TodoApp.css"
import LoginComponent from './LoginComponent'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import WelcomeComponent from './WelcomeComponent'
import ErrorComponent from './ErrorComponent'
import ListTodosComponent from './ListTodosComponent'
import HeaderComponent from './HeaderComponent'
import FooterComponent from './FooterComponent'
import LogoutComponent from './LogoutComponent'
import AuthProvider, { useAuth } from './security/AuthContext'
import TodoComponent from './TodoComponent'
function TodoApp() {
  function AuthenticatedRoute({ children }) {
    const auth = useAuth()
    if(auth.isAuthenticated)
    return children
  else
  return <Navigate to="/" />
  }
  return (
    <div className='TodoApp'>
      <AuthProvider>
        <HeaderComponent />

        <Routes>
          <Route path='/' element={<LoginComponent />}></Route>
          <Route path='/login' element={<LoginComponent />}></Route>
          <Route path='/welcome/:username' element={<AuthenticatedRoute><WelcomeComponent /></AuthenticatedRoute>}></Route>

          <Route path='/todos' element={<AuthenticatedRoute><ListTodosComponent /></AuthenticatedRoute>}></Route>
          <Route path='/logout' element={<AuthenticatedRoute><LogoutComponent /></AuthenticatedRoute>}></Route>
          <Route path='/todo/:id' element={<AuthenticatedRoute><TodoComponent /></AuthenticatedRoute>}></Route>

          <Route path='*' element={<ErrorComponent />}></Route>

        </Routes>

        <FooterComponent />
      </AuthProvider>
    </div>
  )
}

export default TodoApp
