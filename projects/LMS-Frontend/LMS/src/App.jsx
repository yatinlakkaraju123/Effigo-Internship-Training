
//import './App.css'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import HomePage from './components/HomePage'
import RegisterPage from './components/Security/RegisterPage'
import LoginPage from './components/Security/LoginPage'
import AuthProvider from './components/Security/AuthContext'
function App() {
 

  return (
    <>
    <AuthProvider>
     <BrowserRouter>
     <Routes>

      <Route path='/' element={<HomePage/>}></Route>
      <Route path='/register' element={<RegisterPage/>}></Route>
      <Route path='/login' element={<LoginPage/>}></Route>
     </Routes>
     </BrowserRouter> 
     </AuthProvider>
    </>
  )
}

export default App
