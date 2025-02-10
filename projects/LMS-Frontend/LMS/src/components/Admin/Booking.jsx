import React, { useEffect, useState } from 'react'
import { useParams,useNavigate } from 'react-router-dom'
import { useAuth } from '../Security/AuthContext'
import { addBook, retrieveBookingById, updateBook } from '../api/BookingApi'
import AdminNavbar from '../navbars/AdminNavbar'
function Booking() {
    const {id} = useParams()
    const navigate = useNavigate()
    const auth = useAuth()
    const [name,setName] = useState("")
    const [totalQuantity,setTotalQuantity] = useState(null)
    useEffect(()=>{
        const fetch =async ()=>{
            if(id!=-1){
                try {
                    const response = await retrieveBookingById(id)
                    console.log(response)
                setName(response.data.name)
                setTotalQuantity(response.data.totalQuantity)
                } catch (error) {
                    console.log(error)
                }
                
            }
        }
        fetch()
    },[id])
    const update = (book)=>{
            updateBook(id,book).then(()=>navigate('/manageBooking'))
            .catch(err=>console.log(err))
        }
    const add = (book)=>{
            addBook(book).then(()=>navigate('/manageBooking'))
            .catch(err=>console.log(err))
    }
    const submit = (e)=>{
        e.preventDefault();
        const book = {
            name:name,
            totalQuantity:totalQuantity
        }
        if(id!=-1){
            update(book)
        }else if(id==-1){
                add(book)
        }
    }
  return (
    <div>
        <AdminNavbar/>
      <div className='container'>
      <form>
  <div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <input type="text" class="form-control" 
    id="name" aria-describedby="emailHelp" onChange={(e)=>setName(e.target.value)} value={name}/>
  </div>
  <div class="mb-3">
    <label for="totalQuantity" class="form-label">Total Quantity</label>
    <input type="number" class="form-control" 
    id="totalQuantity" onChange={(e)=>setTotalQuantity(e.target.value)} value={totalQuantity}/>
  </div>
  
  
  <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
</form>
      </div>
    </div>
  )
}

export default Booking
