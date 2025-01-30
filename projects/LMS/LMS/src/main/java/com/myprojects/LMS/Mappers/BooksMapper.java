package com.myprojects.LMS.Mappers;

import com.myprojects.LMS.DTOs.BooksDTO;
import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksMapper {

    BooksDTO booksToBooksDTO(Books books);
    Books booksDTOTobooks(BooksDTO booksDTO);
}
