import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import { useAuth } from './components/Security/AuthContext';
import HomePage from './components/User/HomePage';
import AdminHomePage from './components/Admin/HomePage';
import RegisterPage from './components/Security/RegisterPage';
import LoginPage from './components/Security/LoginPage';
import AuthProvider from './components/Security/AuthContext';
import LogoutPage from './components/Security/LogoutPage';
import { useEffect, useState } from 'react';
import ManageBooking from './components/Admin/ManageBooking';
import Booking from './components/Admin/Booking';
import 'bootstrap/dist/css/bootstrap.min.css';
import ManageBorrow from './components/User/ManageBorrow';
import ManageRequests from './components/Admin/ManageRequests';
import Request from './components/Admin/Request';
import ManageBorrowRecords from './components/User/ManageBorrowRecords';
import Return from './components/User/Return';
function AuthenticatedRoute({ children, allowedRoles }) {
    const auth = useAuth();

    if (!auth.isAuthenticated) {
        return <Navigate to="/login" />;
    }

    if (allowedRoles && !allowedRoles.includes(auth.role)) {
        return <Navigate to="/unauthorized" />;
    }

    return children;
}

function App() {
    

    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/register" element={<RegisterPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/logout" element={<LogoutPage />} />
                    <Route path="/unauthorized" element={<div>Unauthorized!</div>} />

                    <Route
                        path="/userHome" // Match all routes
                        element={
                            <AuthenticatedRoute>
                              <HomePage/>
                            </AuthenticatedRoute>
                        }
                    />
                    <Route path='/adminHome' element={
                      <AuthenticatedRoute>
                        <AdminHomePage/>
                      </AuthenticatedRoute>
                      
                      }></Route>
                    <Route path='/manageBooking' element={
                                            <AuthenticatedRoute>
                                            <ManageBooking/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                    <Route path='/booking/:id' element={
                                            <AuthenticatedRoute>
                                            <Booking/>
                                          </AuthenticatedRoute>
                    
                      }></Route>

                      <Route path='/manageBorrow' element={
                                            <AuthenticatedRoute>
                                            <ManageBorrow/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                       <Route path='/manageRequests' element={
                                            <AuthenticatedRoute>
                                            <ManageRequests/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                      <Route path='/Request' element={
                                            <AuthenticatedRoute>
                                            <Request/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                      <Route path='/manageBorrowRecords' element={
                                            <AuthenticatedRoute>
                                            <ManageBorrowRecords/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                      <Route path='/return' element={
                                            <AuthenticatedRoute>
                                            <Return/>
                                          </AuthenticatedRoute>
                    
                      }></Route>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;
