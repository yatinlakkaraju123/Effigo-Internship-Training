import React, { useEffect, useState } from 'react'
import Header from '../fragments/Header'
import Footer from '../fragments/Footer'
import LoginPage from '../Security/LoginPage'
import { useAuth } from '../Security/AuthContext';
import { retrieveUserById } from '../api/UserApi';

function HomePage() {
      const auth = useAuth()
      const username = auth.username
      const userId = auth.userId
      const [userDetails,setUserDetails] = useState(null)
      useEffect(() => {
        const fetchUserDetails = async () => {  // Make the inner function async
            if (userId) { // Check if userId has a value
                try {
                    const response = await retrieveUserById(userId);
                    setUserDetails(response.data);
                    console.log(response.data); // Log the actual data
                } catch (error) {
                    console.error("Error fetching user details:", error);
                    // Handle the error appropriately (e.g., display an error message)
                }
            }
        };

        fetchUserDetails(); // Call the inner function
    }, [userId]); // Add userId to the dependency array

    if (!userDetails) { // Loading state
        return <div>Loading...</div>; // Or a spinner component
    }
  return (
    <div >
     <Header/>
      <div className='container'>
      <h1>Welcome, {username}</h1>


      </div>
        <Footer/>
    </div>
  )
}

export default HomePage
