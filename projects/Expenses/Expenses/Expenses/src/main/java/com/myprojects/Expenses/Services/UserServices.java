package com.myprojects.Expenses.Services;

import com.myprojects.Expenses.DTOs.DateRangeRequestDTO;
import com.myprojects.Expenses.DTOs.ExpenseResponseDTO;
import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Entities.*;
import com.myprojects.Expenses.Mappers.ExpenseMappers;
import com.myprojects.Expenses.Mappers.UserMappers;
import com.myprojects.Expenses.Repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserMappers userMappers;
    @Autowired
    private ExpenseMappers expenseMappers;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public ResponseEntity<List<UserResponseDTO>> retrieveAllUsers(){
        try{
            List<Users> usersList = userRepository.findAll();
            List<UserResponseDTO> userResponseDTOS = usersList.stream().map(
                    user -> userMappers.UsersToUserResponseDTO(user)
            ).toList();
            return new ResponseEntity<>(userResponseDTOS,HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in retrieving all users:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<UserResponseDTO> retrieveUserById(long id){
        try {
            Optional<Users> user = userRepository.findById(id);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(userMappers
                        .UsersToUserResponseDTO(user.get()),HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving a user by id:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> registerUser(UserRequestDTO userRequestDTO){
        try {
            if(userRepository.existsByUsername(userRequestDTO.getUsername()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Users user = userMappers.UserRequestDTOToUsers(userRequestDTO);
            Authorities roleUser = authorityRepository.findByAuthority("USER")
                    .orElseGet(() -> {
                        Authorities newRole = new Authorities();
                        newRole.setAuthority("USER");
                        return authorityRepository.save(newRole);
                    });

            // Save the user first to generate the ID
            Users savedUser = userRepository.save(user);
            savedUser.getAuthoritiesList().add(roleUser);
            String encodedPassword = bCryptPasswordEncoder.encode(savedUser.getPassword());
            savedUser.setPassword(encodedPassword);

            Profile profile = new Profile();
            profile.setUsername(savedUser.getUsername());
            profile.setUser(savedUser);

            // Save the profile now that the user has been saved
            profileRepository.save(profile);

            // Update the user with the profile
            savedUser.setProfile(profile);
            userRepository.save(savedUser);

            return new ResponseEntity<>("New user created", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in creating user: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserResponseDTO> updateUser(long id,UserRequestDTO userRequestDTO){
        try
        {
            Optional<Users> user = userRepository.findById(id);
            Users requestedUser = userMappers.UserRequestDTOToUsers(userRequestDTO);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
              user.get().setUsername(requestedUser.getUsername());
              user.get().setPassword(requestedUser.getPassword());
              if(user.get().getProfile()!=null) user.get().getProfile().setUsername(requestedUser.getUsername());
              return new ResponseEntity<>(userMappers
                      .UsersToUserResponseDTO(user.get()),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            log.info("error in update user:{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteUser(long id){
        try {
            Optional<Users> user = userRepository.findById(id);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Profile userProfile = user.get().getProfile();
           if(userProfile!=null)
           {    userProfile.setUser(null);
               user.get().setProfile(null);
               profileRepository.delete(userProfile);
           }
           List<Expenses> userExpenses = user.get().getExpenses();
           for(Expenses expense:userExpenses){
               expense.setUser(null);
               expense.setCategory(null);
               expenseRepository.delete(expense);
           }
            userRepository.delete(user.get());
            return new ResponseEntity<>("user deleted",HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in delete user:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByUserId(long id){
        try {
            Optional<Users> user = userRepository.findById(id);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                List<ExpenseResponseDTO> expenseResponses = user.get().getExpenses().stream().map(
                        expenses -> expenseMappers.ExpensesToExpenseResponse(expenses)
                ).toList();
                return new ResponseEntity<>(expenseResponses,HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving an expense by userId:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByUserAndCategory(long uid,
                                                                                      long cid)
    {

        try {
            Optional<Users> user = userRepository.findById(uid);
            Optional<Category> category = categoryRepository.findById(cid);
            if(user.isEmpty() || category.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                List<ExpenseResponseDTO> expenseResponses = userRepository
                        .findExpensesByCategoryAndUser(uid,cid).stream().map(
                        expenses -> expenseMappers.ExpensesToExpenseResponse(expenses)
                ).toList();
                return new ResponseEntity<>(expenseResponses,HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving an expense by userId and category Id:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByDateRange(long id
            , DateRangeRequestDTO dateRangeRequestDTO){
        try {
            Optional<Users> user = userRepository.findById(id);
            if(user.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                List<ExpenseResponseDTO> expenseResponses = userRepository
                        .findExpensesByDate(dateRangeRequestDTO.getFrom()
                                ,dateRangeRequestDTO.getTo()).stream().map(
                                expenses -> expenseMappers.ExpensesToExpenseResponse(expenses)
                        ).toList();
                return new ResponseEntity<>(expenseResponses,HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving an expense by userId and date range:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
