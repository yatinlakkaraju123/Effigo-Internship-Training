import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useAuth } from './security/AuthContext'
import { addTodo, retrieveTodoForId, updateTodoForId } from './api/TodoApiService'
import DatePicker from 'react-date-picker';

function TodoComponent() {
    const {id} = useParams()
    const auth = useAuth()
    const navigate = useNavigate()
    const [todo,setTodo] = useState(null)
    const [description,setDescription] = useState("")
    const [targetDate,setTargetDate] = useState(null)
    const [message,setMessage] = useState(null)
    const [message2,setMessage2] = useState(null)

    useEffect(()=>{
        retrieveTodo()
    },[id])
   const retrieveTodo = ()=>{
        if(id!=-1)
        {
            
        retrieveTodoForId(auth.username,id).then((response)=>{
            setDescription(response.data.description)
            setTargetDate(response.data.targetDate)
    }).catch(error=>console.log(error))
        }
        
   }
   const updateTodo = (todo)=>{
    //console.log("in updatetodo")
    //console.log(todo)
    updateTodoForId(auth.username,id,todo).then(response=>{
            //console.log(response)
            navigate('/todos')
    }).catch(error=>console.log(error))
   }
   const submit = (e)=>{
    e.preventDefault();
    //console.log(description)
    //console.log(targetDate)
    if(id!=-1)
    {
        validate(description)
    const todo = {
        id:id,
        username:auth.username,
        description:description,
        targetDate:targetDate,
        done:false

    }
    updateTodo(todo)
    }
    else if(id==-1)
    {
        validate(description)
        const todo = {
            username:auth.username,
        description:description,
        targetDate:targetDate,
        done:false
        }
        addTodo(auth.username,todo).then(response=>navigate('/todos'))
        .catch(error=>console.log(error))
    }
    
   }
   const validate = (text)=>{
        if(text.length<5)
        {
            setMessage("enter valid description")
        }
        if(targetDate==null || targetDate == "")
        {
            setMessage2(" valid date ")
        }
   }
  return (
    <div className='container'>
      <h1> Enter todo details</h1>
      {message && <div className='alert alert-warning'>{message}</div>}
      {message2 && <div className='alert alert-warning'>{message2}</div>}

      <form className=''>
      <div>
            <label>Description</label>
            <input type='text' name="description" value={description} onChange={(description)=>setDescription(description.target.value)} />
          </div>
          <div className=''>
          
            <label className=''>Target date</label>
            <input type="date" class=""  onChange={(date) => setTargetDate(date.target.value)}
            value={targetDate}
 />

           
          </div>
          <div>
            <button type='button' name='submit' onClick={submit} className='btn btn-success'>submit</button>
          </div>
      </form>
    </div>
  )
}

export default TodoComponent
