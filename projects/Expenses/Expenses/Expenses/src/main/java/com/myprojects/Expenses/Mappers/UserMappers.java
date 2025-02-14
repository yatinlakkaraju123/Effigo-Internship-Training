package com.myprojects.Expenses.Mappers;

import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMappers {
    @Mapping(source = "userId",target = "id")
    Users UserRequestDTOToUsers(UserRequestDTO userRequestDTO);
    @Mapping(target = "userId",source = "id")

    UserResponseDTO UsersToUserResponseDTO(Users user);
}
