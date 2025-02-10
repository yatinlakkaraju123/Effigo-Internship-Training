import React from 'react'
import { Container, Row, Col,Card,Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
function BorrowGrid({borrows}) {
    const navigate = useNavigate()
    const returnBook = (borrowId)=>{
        navigate('/return',{state :{
            borrowId
        }})
    }
  return (
    <div>
         <Container>
                      <Row>
                          { borrows && borrows.map((borrow, index) => (
                              <Col key={index} xs={12} sm={6} md={4} lg={3}>
                                  <div className="grid-item">
                                      <Card style={{ width: '18rem' }}>
                                          <Card.Body>
                                              <Card.Title>{borrow.book.name}</Card.Title>
                                              <Card.Text>
                                                  <h6>Issue Date:{borrow.issueDate}</h6>
                                                  <h6>Due Date:{borrow.dueDate}</h6>
                                                  <h6>Return Date:{borrow.returnDate!=null
                                                  ?borrow.returnDate:"not returned"}</h6>
                                                  <h6>fine:{borrow.fine}</h6>
                                                  <h6>status:{borrow.status==="true"?"Returned":"Not returned"}</h6>

                                         </Card.Text>
                                              {(borrow.status==="false") ? <> <Button variant="primary" 
                                              onClick={()=>returnBook(borrow.id)}>Return</Button></>:<></>}
                                          </Card.Body>
                                      </Card>
                                  </div>
                              </Col>
                          ))}
                      </Row>
                  </Container>
    </div>
  )
}

export default BorrowGrid
