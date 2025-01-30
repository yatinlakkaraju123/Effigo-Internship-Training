package com.myprojects.LMS.Mappers;

import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDto);
}
