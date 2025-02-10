import React, { useEffect,useState } from 'react'
import { retrieveAllBorrowRecords } from '../api/BorrowingApi'
import { useAuth } from '../Security/AuthContext';
import BorrowGrid from './BorrowGrid';
import UserNavbar from '../navbars/UserNavbar';

function ManageBorrowRecords() {
    const [borrow,setBorrow] = useState(null)
    const auth = useAuth()
    const fetchBorrows=async()=>{
       const response =  await retrieveAllBorrowRecords()
       const borrowAll = response.data
       const reqBorrow = borrowAll.filter(borrow=>borrow.user.id==auth.userId)
       setBorrow(reqBorrow)
    }
    useEffect(()=>{
        fetchBorrows()
    },[borrow])
  return (
    <div>
        <UserNavbar/>
      <BorrowGrid borrows={borrow}/>
    </div>
  )
}

export default ManageBorrowRecords
