package com.myprojects.LMS.Mappers;

import com.myprojects.LMS.DTOs.RequestBooksDTO;
import com.myprojects.LMS.Entities.RequestBooks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface RequestBooksMapper {
    @Mapping(source = "user",target = "requestedUser")
    @Mapping(source = "book",target = "requestBook")
    RequestBooksDTO requestBooksToRequestBooksDTO(RequestBooks requestBooks);

    @Mapping(target = "user",source = "requestedUser")
    @Mapping(target = "book",source = "requestBook")
    RequestBooks requestBooksDTOToRequestBooks(RequestBooksDTO requestBooksDTO);
}
