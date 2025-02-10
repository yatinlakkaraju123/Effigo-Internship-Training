import React,{useEffect, useState} from 'react'
import Header from '../fragments/Header'
import { Link } from 'react-router';
import { useAuth } from './AuthContext';
import { useNavigate } from 'react-router-dom';
import { executeJWTAuthenticationService } from '../api/AuthenticationApiService';
import LoginNavbar from '../navbars/LoginNavbar';

function LoginPage() {
    const containerStyle = {
        height: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      };
    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")
    const navigate = useNavigate()

    const [showErrorMessage, setShowErrorMessage] = useState(false)
    const auth = useAuth()
    useEffect(()=>{
        if(auth.isAuthenticated){
          if(auth.role==='USER')
            navigate('/userHome')
          else if(auth.role==='ADMIN')
            navigate('/adminHome')
        }
    },[auth.isAuthenticated,auth.role,navigate])
    const submit = async (e)=>{
            e.preventDefault();
            
            
            if(await auth.login(username,password))
            {   
               setShowErrorMessage(false)
            }
            else{
              setShowErrorMessage(true)
            }
    }
  return (
    <>
    <LoginNavbar/>
    <div className='container' style={containerStyle}>
      
      <form>
      {showErrorMessage && <div class="alert alert-danger alert-dismissible fade show" role="alert">
          Login failed check credentials and try again.
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>}
  <div class="mb-3">
    <label for="username" class="form-label">User Name</label>
    <input type="text" class="form-control" 
    id="username" aria-describedby="emailHelp" 
    value={username} onChange={(e)=>setUsername(e.target.value)}/>
  </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password</label>
    <input type="password" class="form-control" 
    id="password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
  </div>
  
  <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
  <Link to="/register"><a href=''>Register</a></Link>

</form>
    </div>
    </>
  )
}

export default LoginPage
