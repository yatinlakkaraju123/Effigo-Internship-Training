import axios from 'axios'

export function RetrieveHelloWorld(token)
{
    return axios.get('http://localhost:8080/hello-world',{
        headers:{
            Authorization:token
        }
    })
}

