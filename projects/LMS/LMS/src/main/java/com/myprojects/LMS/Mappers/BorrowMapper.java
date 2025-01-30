package com.myprojects.LMS.Mappers;

import com.myprojects.LMS.DTOs.BorrowDTO;
import com.myprojects.LMS.Entities.Borrow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface BorrowMapper {

    BorrowDTO borrowToBorrowDTO(Borrow borrow);
    Borrow borrowDTOToBorrow(BorrowDTO borrowDTO);
}
