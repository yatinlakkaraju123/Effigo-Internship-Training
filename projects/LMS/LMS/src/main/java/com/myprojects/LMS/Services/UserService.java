package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.UserMapper;
import com.myprojects.LMS.Repositories.BooksRepository;
import com.myprojects.LMS.Repositories.BorrowRepository;
import com.myprojects.LMS.Repositories.CommentsRepository;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    public ResponseEntity<List<UserDTO>> retrieveAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOS = users.stream().map(user ->
                    userMapper.userToUserDTO(user)).toList();

            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertUser(UserDTO userDto) {
        try {
            User user = userMapper.userDTOToUser(userDto);
            userRepository.save(user);
            return new ResponseEntity<>("user inserted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserDTO> updateUser(int id, UserDTO userDTO) {
        try {
            User user = userMapper.userDTOToUser(userDTO);
            Optional<User> getUser = userRepository.findById(id);
            if (getUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                getUser.get().setName(user.getName());
                getUser.get().setRole(user.getRole());
                userRepository.save(getUser.get());
                UserDTO userDTO1 = userMapper.userToUserDTO(userRepository.findById(id).get());
                return new ResponseEntity<>(userDTO1, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteUser(int id){
        try {
            Optional<User> getUser = userRepository.findById(id);
            if (getUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Books> books = getUser.get().getBooks();
                for(Books book:books){
                    book.getUsers().remove(getUser.get());
                }
                borrowRepository.deleteAllByUser(id);
                commentsRepository.deleteAllByUser(id);
                userRepository.delete(getUser.get());
                return new ResponseEntity<>("deleted", HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

