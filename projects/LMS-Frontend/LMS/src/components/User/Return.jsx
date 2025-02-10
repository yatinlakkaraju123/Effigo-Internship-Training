import React, { useState } from 'react'
import { useLocation } from 'react-router-dom';
import { createReturnRecord } from '../api/BorrowingApi';
import { useNavigate } from 'react-router-dom';
import UserNavbar from '../navbars/UserNavbar';
function Return() {
    const location = useLocation()
    const [returnDate,setReturnDate] = useState(null)
    const borrowId = location.state.borrowId
    const navigate = useNavigate()
    const submit = async(e)=>{
        console.log("borrow id:",borrowId)
        e.preventDefault();
        const returnRecord = {
            returnDate:returnDate
        }
        await createReturnRecord(borrowId,returnRecord)
        navigate('/manageBorrowRecords')
    }
  return (
    <div>
        <UserNavbar/>
      <div className='container'>
      <form>
  <div class="mb-3">
    <label for="requestDate" class="form-label">Return date</label>
    <input type="date" class="form-control" 
    id="requestDate" aria-describedby="emailHelp" 
    onChange={(e)=>setReturnDate(e.target.value)} value={returnDate}/>
  </div>
  
  
  
  <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
</form>
      </div>
    </div>
  )
}

export default Return
