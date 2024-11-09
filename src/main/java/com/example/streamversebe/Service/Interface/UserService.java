package com.example.streamversebe.Service.Interface;

import com.example.streamversebe.DTOs.DTO.UserDTO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(ObjectId id, UserDTO userDTO);

    Optional<UserDTO> getUserById(ObjectId id);

    Optional<UserDTO> getUserByEmail(String email);

    Optional<UserDTO> getUserByUsername(String username);

    boolean deleteUser(ObjectId id);

    List<UserDTO> getAllUsers();
}
