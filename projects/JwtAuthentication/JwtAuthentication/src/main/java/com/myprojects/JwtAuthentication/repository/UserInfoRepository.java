package com.myprojects.JwtAuthentication.repository;

import com.myprojects.JwtAuthentication.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String name); // Use 'email' if that is the correct field for login

}
