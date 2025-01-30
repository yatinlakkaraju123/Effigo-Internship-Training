package com.myprojects.LMS.Mappers;

import com.myprojects.LMS.DTOs.CommentsDTO;
import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Comments;
import com.myprojects.LMS.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    CommentsDTO commentsToCommentsDTO(Comments comments);
    Comments commentsDTOToComments(CommentsDTO commentsDTO);
}
