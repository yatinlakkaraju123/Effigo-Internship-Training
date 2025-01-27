package com.myprojects.OnetoOne.Profile.Repository;

import com.myprojects.OnetoOne.Profile.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
