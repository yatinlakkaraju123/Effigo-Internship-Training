package com.myprojects.Expenses.Mappers;

import com.myprojects.Expenses.DTOs.ProfileRequestDTO;
import com.myprojects.Expenses.DTOs.ProfileResponseDTO;
import com.myprojects.Expenses.Entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMappers {
    @Mapping(source = "profileId",target = "id")
    Profile ProfileRequestToProfile(ProfileRequestDTO profileRequestDTO);
    @Mapping(target = "profileId",source = "id")
    ProfileResponseDTO ProfileToProfileResponse(Profile profile);
}
