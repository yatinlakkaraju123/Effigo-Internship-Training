import React, { useEffect,useState } from 'react'
import AdminNavbar from '../navbars/AdminNavbar'
import { deleteBook, retrieveAllBooking } from '../api/BookingApi'
import { useNavigate } from 'react-router-dom'
function ManageBooking() {
  const navigate = useNavigate()
  const [books,setBooks] = useState(null)
  const fetchAllBooks = async ()=>{
    const response = await retrieveAllBooking()
    setBooks(response.data)
  }
  useEffect( ()=>{
    
    fetchAllBooks()
  },[])

  const update = (id)=>{
    const bookId =id
    console.log("book id:",bookId)
    navigate(`/booking/${bookId}`)
  }
  const add = ()=>{
    navigate('/booking/-1')
  }
  const Delete = (id)=>{
      deleteBook(id).then(fetchAllBooks()).catch(err=>console.log(err))
  }
  return (
    <div>
      <AdminNavbar/>
      <div className='container'>
      {books && <table class="table">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Total Quantity</th>
      <th scope="col">Available Quantity</th>
      <th scope="col">Update</th>
      <th scope="col">Delete</th>

    </tr>
  </thead>
  <tbody>
    {books.map(book=>(
      <tr key={book.id}>
        <td>{book.name}</td>
        <td>{book.totalQuantity}</td>
        <td>{book.availableQuantity}</td>
        <td><button type="button" class="btn btn-warning" onClick={()=>update(book.id)}>Update</button>
        </td>
        <td>
        <button type="button" class="btn btn-danger" onClick={()=>Delete(book.id)}>Delete</button>

        </td>
      </tr>
    ))}
    
    
  </tbody>
</table>}
<button type="button" class="btn btn-success" onClick={add}>Add book</button>

      </div>
    </div>
  )
}

export default ManageBooking
