import React, { useEffect,useState } from 'react'
import AdminNavbar from '../navbars/AdminNavbar'
import Requests from './RequestsGrid'
import { retrieveAllRequests } from '../api/RequestsBooksApi'

function ManageRequests() {
    const [requests,setRequests] = useState(null)
    const fetchRequests = async()=>{
        const response = await retrieveAllRequests()
        const allRequests = response.data
        setRequests(allRequests)
    }

    useEffect(()=>{
        fetchRequests()
        console.log(requests)
    },[])
  return (
    <div>
      <AdminNavbar/>
      <div className='container'>
      <h3>Manage the requests recieved for borrowing books here</h3>

      </div>
      { requests && <Requests requests={requests}/>}
    </div>
  )
}

export default ManageRequests
