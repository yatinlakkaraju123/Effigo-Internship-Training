import React,{useState} from 'react'
import { Link, useParams } from 'react-router-dom'
import { RetrieveHelloWorld } from './api/HelloWorldApiService'
import { useAuth } from './security/AuthContext'
function WelcomeComponent() {
    const {username} = useParams()
    const [message,setMessage] = useState(null)
    const auth = useAuth()
    const helloWorldRestApi = ()=>{
      const helloWorld = RetrieveHelloWorld(auth.token)
      helloWorld.then(
        (response)=>successfulResponse(response)
      ).catch((error)=>errorResponse(error))
    }
    function successfulResponse(response)
    {
      setMessage(response.data)
    }
    function errorResponse(error)
    {
      console.log(error)
    }
  return (
    <div>
    <div>
      Welcome {username}
      Your Todos - <Link to="/todos"><a>Here</a></Link>
    </div>
    <div>
    <button className='btn btn-success' onClick={helloWorldRestApi}>Call Hello World API </button>
      <div className="text-info">{message}</div>
    </div>
    </div>
  )
}

export default WelcomeComponent
