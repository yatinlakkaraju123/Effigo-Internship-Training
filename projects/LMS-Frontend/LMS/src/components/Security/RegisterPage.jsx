import React, { useState } from 'react'
import { registerUser } from '../api/AuthenticationApiService';
import { useNavigate } from 'react-router-dom';

function RegisterPage() {
    const containerStyle = {
        height: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      };
      const [username,setUsername] = useState("")
      const [password,setPassword] = useState("")
      const [repeatPassword,setRepeatPassword] = useState("")
      const [error,setError] = useState(false)
      const navigate = useNavigate()
      const submit = async(e)=>{
        e.preventDefault();
        if(password===repeatPassword)
        { try {
          const response = await registerUser(username,password)
          if(response.status==200){
              navigate('/login')
          }
          
        } catch (error) {
          //console.log(error)
          if(error.status==400){
            alert("user already exists")
          }
        }
            
        }
        else
        {
          setError(true)
        }

      }
  return (
    <div>
      <div className='container' style={containerStyle}>
      <form>
  <div class="mb-3">
    <label for="username" class="form-label">User Name</label>
    <input type="text" class="form-control" id="username" 
    aria-describedby="emailHelp" value={username} onChange={(e)=>setUsername(e.target.value)}/>
  </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password</label>
    <input type="password" class="form-control" 
    id="password" value={password} onChange={((e)=>setPassword(e.target.value))}/>
  </div>
  <div class="mb-3">
    <label for="passwordrepeat" class="form-label">Enter Password Again</label>
    <input type="password" class="form-control" 
    id="passwordrepeat" value={repeatPassword} onChange={(e)=>setRepeatPassword(e.target.value)}/>
  </div>
  <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
</form>
    </div>
    </div>
  )
}

export default RegisterPage
