package com.myprojects.OnetoOne.User.Services;

import com.myprojects.OnetoOne.User.Model.User;
import com.myprojects.OnetoOne.User.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers(){
        try
        {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

        } catch (RuntimeException e) {
            logger.error("error:"+e.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertUser(User user){
        try
        {   //logger.info("name recorded:",user.getName());
            User usr = userRepository.save(user);
            //logger.info("user:{} added",usr.toString());
            return new ResponseEntity<>("added new user",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<User> getUserById(int id){
        try{
            User user = userRepository.findById(id).get();
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> updateUser(int id, User user){
        try{
            Optional<User> retrieveUserById = userRepository.findById(id);
            if(retrieveUserById.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                retrieveUserById.get().setName(user.getName());
                userRepository.save(retrieveUserById.get());
                return new ResponseEntity<>(userRepository.findById(id).get(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> deleteUser(int id){
        try
        {
            userRepository.deleteById(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
