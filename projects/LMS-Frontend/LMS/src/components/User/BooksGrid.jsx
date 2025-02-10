import React from 'react'
import { Container, Row, Col,Card,Button } from 'react-bootstrap';
import { useAuth } from '../Security/AuthContext';
import { useNavigate } from 'react-router-dom';
import { createRequest } from '../api/RequestsBooksApi';

function BooksGrid({ books }) {
    const auth = useAuth()
    const navigate = useNavigate()
          const userId = auth.userId
    const borrow = async(bookId)=>{
            await createRequest(userId,bookId).then(()=>{
                alert("borrow request sent")
                navigate('/manageBorrow')
            }
                )
            .catch(err=>console.log(err))
            
    }
    return (
        <div>
            <Container>
                <Row>
                    { books && books.map((book, index) => (
                        <Col key={index} xs={12} sm={6} md={4} lg={3}>
                            <div className="grid-item">
                                <Card style={{ width: '18rem' }}>
                                    <Card.Body>
                                        <Card.Title>{book.name}</Card.Title>
                                        <Card.Text>
                                            <h6>Available Quantity:{book.availableQuantity}</h6>
                                   </Card.Text>
                                        <Button variant="primary" onClick={()=>borrow(book.id)}>Borrow</Button>
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

export default BooksGrid
