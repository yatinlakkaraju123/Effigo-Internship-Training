package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Authority;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.UserMapper;
import com.myprojects.LMS.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
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
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public ResponseEntity<UserDTO> retrieveUserById(int id){
        try
        {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                UserDTO userDTO = userMapper.userToUserDTO(user.get());
                return new ResponseEntity<>(userDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertUser(UserDTO userDto) {
        try {   if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists"); // 400 Bad Request
        }
            Authority roleUser = authorityRepository.findByAuthority("USER")
                    .orElseGet(() -> {
                        Authority newRole = new Authority();
                        newRole.setAuthority("USER");
                        return authorityRepository.save(newRole);
                    });

            User user = userMapper.userDTOToUser(userDto);
            logger.info("user is:"+user.toString());
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setEnabled(true);
            user.getAuthorities().add(roleUser);

            userRepository.save(user);
            return new ResponseEntity<>("user inserted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserDTO> updateUser(int id, UserDTO userDTO) {
        try {
            User user = userMapper.userDTOToUser(userDTO);
            Optional<User> getUser = userRepository.findById(id);
            if (getUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                getUser.get().setUsername(user.getUsername());
                //getUser.get().setRole(user.getRole());
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
                User user = getUser.get();
                user.getAuthorities().clear();
                //userRepository.save(user);
                List<Books> books = getUser.get().getBooks();
                for(Books book:books){
                    book.getUsers().remove(getUser.get());
                }
                authorityRepository.deleteAllByUser(id);
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

