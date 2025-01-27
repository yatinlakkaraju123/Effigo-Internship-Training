package com.myprojects.OnetoOne.Profile.Controller;

import com.myprojects.OnetoOne.Profile.Model.Profile;
import com.myprojects.OnetoOne.Profile.Services.ProfileServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    ProfileServices profileServices;

    public ProfileController(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }

    @PostMapping("/users/{id}/profiles")
    public ResponseEntity<String> AddProfile(@PathVariable int id, @RequestBody Profile profile){
        return profileServices.insertProfile(id,profile);
    }

    @GetMapping("/users/{id}/profiles")
    public ResponseEntity<Profile> retrieveProfilesByUser(@PathVariable int id){
        return profileServices.retrieveProfilesForUser(id);
    }
    @PutMapping("/users/{id}/profiles")
    public ResponseEntity<Profile> updateProfilesByUser(@PathVariable int id,@RequestBody Profile profile){
        return profileServices.updateProfileForUser(id,profile);
    }
    @DeleteMapping("/users/{id}/profiles")
    public ResponseEntity<String> deleteProfileByUser(@PathVariable int id){
        return profileServices.deleteProfileByUser(id);
    }
}
