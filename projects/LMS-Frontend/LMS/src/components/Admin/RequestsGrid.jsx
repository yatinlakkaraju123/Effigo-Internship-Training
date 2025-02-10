import React, { useEffect } from 'react'
import { Container, Row, Col,Card,Button } from 'react-bootstrap';
import { useAuth } from '../Security/AuthContext';
import { useNavigate } from 'react-router-dom';
import { createRequest, deleteRequest } from '../api/RequestsBooksApi';

function Requests({ requests }) {
    const auth = useAuth()
    const navigate = useNavigate()
          const userId = auth.userId
          useEffect(()=>{
            console.log("Requests:",requests)
          },[])
    const requestB = async(bookId,userId,requestId)=>{
        navigate('/Request',{
            state:{
                userId,bookId,requestId
            }
        })
        
            //await createRequest(userId,bookId).then(navigate('/manageBorrow'))
            //.catch(err=>console.log(err))
    }
    const rejectB = async(requestId)=>{
        await deleteRequest(requestId)
        navigate('/manageRequests')
    }
    return (
        <div>
            <Container>
                <Row>
                    { requests && requests.map((request, index) => (
                        <Col key={index} xs={12} sm={6} md={4} lg={3}>
                            <div className="grid-item">
                                <Card style={{ width: '18rem' }}>
                                    <Card.Body>
                                        <Card.Title>{request.requestBook.name}</Card.Title>
                                        <Card.Text>
                                            <h6>User:{request.requestedUser.username}</h6>
                                   </Card.Text>
                                        <Button variant="primary" onClick={
                                            ()=>requestB(request.requestBook.id
                                            ,request.requestedUser.id,request.id)}>Accept</Button>
                                            
                                            <Button variant="danger" onClick={
                                            ()=>rejectB(request.id)} className='float-end'>Reject</Button>
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

export default Requests