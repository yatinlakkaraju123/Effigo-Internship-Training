import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import AdminNavbar from '../navbars/AdminNavbar';
import { createBorrowRecord } from '../api/BorrowingApi';
import { deleteRequest } from '../api/RequestsBooksApi';
function Request() {
    const navigate = useNavigate()
    const location = useLocation()
    const data = location.state;
    const [requestDate,setRequestDate] = useState(null)
    const [dueDate,setDueDate] = useState(null)
   //userId,bookId,requestId console.log(data)
   console.log(data)
   const submit = async (e) => {
    e.preventDefault();
    const borrow = {
        issueDate: requestDate,
        dueDate: dueDate
    };

    try {
        // Create borrow record
        const [response1, response2]= await Promise.all(
            [
                createBorrowRecord(data.userId, data.bookId, borrow),
                deleteRequest(data.requestId)


            ]
        );
        // Delete request
        console.log("response 1:",response1)
        console.log("response 2:",response2)
        // Navigate to manageRequests
        navigate('/manageRequests');
    } catch (err) {
        console.log(err);
    }
};

  return (
    <div>
     <AdminNavbar/>
     <div className='container'>
      <form>
  <div class="mb-3">
    <label for="requestDate" class="form-label">Request date</label>
    <input type="date" class="form-control" 
    id="requestDate" aria-describedby="emailHelp" 
    onChange={(e)=>setRequestDate(e.target.value)} value={requestDate}/>
  </div>
  <div class="mb-3">
    <label for="dueDate" class="form-label">Due Date</label>
    <input type="date" class="form-control" 
    id="dueDate" onChange={(e)=>setDueDate(e.target.value)} value={dueDate}/>
  </div>
  
  
  <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
</form>
      </div>
    </div>
  )
}

export default Request
