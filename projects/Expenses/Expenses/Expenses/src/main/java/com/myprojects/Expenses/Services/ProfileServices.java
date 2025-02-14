package com.myprojects.Expenses.Services;

import com.myprojects.Expenses.DTOs.ProfileRequestDTO;
import com.myprojects.Expenses.DTOs.ProfileResponseDTO;
import com.myprojects.Expenses.Entities.Profile;
import com.myprojects.Expenses.Mappers.ProfileMappers;
import com.myprojects.Expenses.Repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileServices {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileMappers profileMappers;
    public ResponseEntity<List<ProfileResponseDTO>> retrieveAllProfiles(){
        try{
            List<Profile> profilesList = profileRepository.findAll();
            List<ProfileResponseDTO> profileResponseDTOS = profilesList.stream().map(
                    profile -> profileMappers.ProfileToProfileResponse(profile)
            ).toList();
            return new ResponseEntity<>(profileResponseDTOS, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in retrieving all profiles:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<ProfileResponseDTO> retrieveProfileById(long id){
        try {
            Optional<Profile> profile = profileRepository.findById(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(profileMappers
                        .ProfileToProfileResponse(profile.get()),HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving profile by id:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<ProfileResponseDTO> updateProfile(long id,ProfileRequestDTO profileRequestDTO){
        try
        {
            Optional<Profile> profile = profileRepository.findById(id);
            Profile requestedProfile = profileMappers.ProfileRequestToProfile(profileRequestDTO);
            if(profile.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
               if(requestedProfile.getProfilePic()!=null) profile.get().setProfilePic(requestedProfile.getProfilePic());
               if(requestedProfile.getAddress()!=null) profile.get().setAddress(requestedProfile.getAddress());
               if(requestedProfile.getEmail()!=null) profile.get().setEmail(requestedProfile.getEmail());
               if(requestedProfile.getFirstName()!=null)profile.get().setFirstName(requestedProfile.getFirstName());
               if(requestedProfile.getLastName()!=null)profile.get().setLastName(requestedProfile.getLastName());
               if(requestedProfile.getPhoneNumber()!=null)profile.get().setPhoneNumber(requestedProfile.getPhoneNumber());

               return new ResponseEntity<>(profileMappers
                        .ProfileToProfileResponse(profile.get()),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            log.info("error in updating profile:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
