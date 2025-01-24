import React, { useEffect, useState } from 'react'
import { deleteTodo, retrieveAllTodosforUser } from './api/TodoApiService'
import { useAuth } from './security/AuthContext'
import { useNavigate } from 'react-router-dom'
function ListTodosComponent() {
    const today = new Date()
    const auth = useAuth()
    const navigate = useNavigate()
    const targetDate = new Date(today.getFullYear()+12,today.getMonth(),today.getDay())
    const [todos,setTodos] = useState([])
    const [message,setMessage] = useState(null)
    useEffect(
      ()=>{
        refreshTodos()
      },[]
    )
    function refreshTodos()
    {
      retrieveAllTodosforUser(auth.username).
      then(response=>
        {
          setTodos(response.data)
        }).
      catch(error=>
        
        console.log(error))

    }
    const Delete = (id)=>{
      deleteTodo(auth.username,id).then(()=>{
        setMessage(`The Todo with id:${id} has been deleted`)
        refreshTodos()
      }).catch(error=>console.log(error))
   
    }
    const UpdateTodo = (id)=>{
      //console.log("clicked:",id)
      navigate(`/todo/${id}`)
    }
    const AddTodo = ()=>{
        navigate('/todo/-1')
    }
  return (
    <div className='container'>
      <h1>Things You Want To Do</h1>
      <div>
        {message &&           <div className='alert alert-warning'>{message}</div>
      }
            <table className='table'>
                <thead>
                    <tr>
                        
                        <th>Description</th>
                        <th>Done</th>
                        <th>Target Date</th>
                        <th>Delete</th>
                        <th>Update</th>

                    </tr>
                </thead>
                <tbody>
                    {todos.map(element=>(
                          <tr key={element.id}>
                       
                          
                         
                         <td>{element.description}</td>
                         <td>{element.done.toString()}</td>
                         <td>{element.targetDate}</td>
                         <td><button className='btn btn-warning' onClick={()=>Delete(element.id)}>Delete</button></td>
                         <td><button className='btn btn-success' onClick={()=>UpdateTodo(element.id)}>Update</button></td>
                         </tr>
                         
                    ))}
                  
                </tbody>
            </table>
            <button className='btn btn-success' onClick={AddTodo}>Add todo</button>
      </div>
    </div>
  )
}

export default ListTodosComponent
