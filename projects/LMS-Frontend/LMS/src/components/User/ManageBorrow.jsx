import React, { useEffect, useState } from 'react'
import { retrieveAllBooking } from '../api/BookingApi'
import UserNavbar from "../navbars/UserNavbar"
import BooksGrid from './BooksGrid'
function ManageBorrow() {
    const [books,setBooks] = useState(null)
    const fetchBooks = async ()=>{
        const response = await retrieveAllBooking()
        const allBooks = response.data
        const allBooksAvailable = allBooks.filter(book=>book.availableQuantity>0)
        setBooks(allBooksAvailable)
        //console.log("books:",books)

    }
    useEffect(()=>{
        fetchBooks()
    },[books])
  return (
    <div>
      <UserNavbar/>
      <div className='container'>
      <BooksGrid books={books}/>
      </div>
    </div>
  )
}

export default ManageBorrow
