package com.example.streamversebe.Converter;

import com.example.streamversebe.DTOs.DTO.UserDTO;
import com.example.streamversebe.Model.Entity.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

    // Copy entity -> DTO
    public UserDTO toUserDTO(Users user) {
        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    // Copy DTO -> entity
    public Users toUser(UserDTO userDTO) {
        if (userDTO == null) return null;

        Users user = new Users();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    // Copy RegisterReq -> entity


}
