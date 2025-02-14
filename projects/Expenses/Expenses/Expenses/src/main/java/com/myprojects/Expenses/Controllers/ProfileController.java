package com.myprojects.Expenses.Controllers;

import com.myprojects.Expenses.DTOs.ProfileRequestDTO;
import com.myprojects.Expenses.DTOs.ProfileResponseDTO;
import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/profile/")
public class ProfileController {
    @Autowired
    private ProfileServices profileServices;
    @GetMapping("retrieveAll")
    public ResponseEntity<List<ProfileResponseDTO>> retrieveAllProfiles(){
        return profileServices.retrieveAllProfiles();
    }
    @GetMapping("retrieve/{id}")
    public ResponseEntity<ProfileResponseDTO> retrieveProfileById(@PathVariable long id){
        return profileServices.retrieveProfileById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable long id
            ,@RequestBody ProfileRequestDTO profileRequestDTO){
        return profileServices.updateProfile(id,profileRequestDTO);
    }

}
