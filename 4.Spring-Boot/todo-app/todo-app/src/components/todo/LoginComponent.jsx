import React, { useState } from 'react'
import { useNavigate ,useParams} from 'react-router-dom'
import { useAuth } from './security/AuthContext'
import "./TodoApp.css"
//import { executeBasicAuthenticationService } from './api/HelloWorldApiService'
function LoginComponent() {
  const [username, setUsername] = useState("in28minutes")
  const [password, setPassword] = useState("")
  const [showErrorMessage, setShowErrorMessage] = useState(false)
  const navigate = useNavigate()
  const auth = useAuth()
  const handleUsernameChange = (event) => {
    setUsername(event.target.value)
  }
  const handlePasswordChange = (event) => {
    setPassword(event.target.value)
  }
  const submit = async (event) => {
    event.preventDefault();
    

    if (await auth.login(username,password)) {
      
      navigate(`/welcome/${username}`)
    }
    else {
      setShowErrorMessage(true)
    }
  }
  return (
    <div>
      <div className='Login'>
        
        {showErrorMessage && <div className='errorMessage'>Authenticated Failed. Please check your credentials.</div>
        }

        <div className='LoginForm'>
          <div>
            <label>User Name</label>
            <input type='text' name="username" value={username} onChange={handleUsernameChange} />
          </div>
          <div>
            <label>Password</label>
            <input type='password' name="password" value={password} onChange={handlePasswordChange} />
          </div>
          <div>
            <button type='submit' name='login' onClick={submit}>login</button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default LoginComponent
