package com.myprojects.OnetoOne.Profile.Services;

import com.myprojects.OnetoOne.Profile.Model.Profile;
import com.myprojects.OnetoOne.Profile.Repository.ProfileRepository;
import com.myprojects.OnetoOne.User.Model.User;
import com.myprojects.OnetoOne.User.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServices {
    private ProfileRepository profileRepository;
    private UserRepository userRepository;

    public ProfileServices(ProfileRepository profileRepository,UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }
//    public ResponseEntity<List<Profile>> getAllProfilesFromUser(){
//        try
//        {
//            List<Profile> = profileRepository.findAllById()
//        } catch (Exception e) {
//
//        }
//    }

    public ResponseEntity<String> insertProfile(int id,Profile profile){
        try
        {
            Optional<User> getUser = userRepository.findById(id);
            if(!getUser.isEmpty())
            {
                profile.setUser(getUser.get());
                profileRepository.save(profile);
                getUser.get().setProfile(profile);
                userRepository.save(getUser.get());
                return new ResponseEntity<>("profile inserted",HttpStatus.OK);

            }
            else
            {
                return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Profile> retrieveProfilesForUser(int id){
        try{
            Optional<User> getUser = userRepository.findById(id);
            if(getUser.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                return new ResponseEntity<>(getUser.get().getProfile(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Profile> updateProfileForUser(int id,Profile profile){
        try{
            Optional<User> getUser = userRepository.findById(id);
            if(getUser.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {   Profile getProfile = getUser.get().getProfile();
                getProfile.setEmail(profile.getEmail());
                profileRepository.save(getProfile);
                return new ResponseEntity<>(getUser.get().getProfile(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteProfileByUser(int id){
        try{
            Optional<User> getUser = userRepository.findById(id);
            if(getUser.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {   Profile getProfile = getUser.get().getProfile();
                profileRepository.delete(getProfile);
                getUser.get().setProfile(null);
                userRepository.save(getUser.get());
                return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
