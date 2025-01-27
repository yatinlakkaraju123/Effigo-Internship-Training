package com.myprojects.JDBC_learning.User.Services;

import com.myprojects.JDBC_learning.User.Repository.UserRepository;
import com.myprojects.JDBC_learning.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<String> updateUser(int id, User user)
    {
        try
        {
            User getUser = userRepository.selectUserById(id);
            if(getUser==null)
            {
                return new ResponseEntity<>("no user found with the given id",HttpStatus.NOT_FOUND);
            }
            else
            {
                userRepository.updateUser(id,user);
                return new ResponseEntity<>(userRepository.selectUserById(id).toString(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("there is some error try again",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<String> deleteUser(int id)
    {
            try{
                userRepository.deleteUserById(id);
                return new ResponseEntity<>("user deleted successfully",HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("some error occured.Try again",HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
