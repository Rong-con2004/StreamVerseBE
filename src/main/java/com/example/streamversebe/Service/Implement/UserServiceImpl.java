package com.example.streamversebe.Service.Implement;

import com.example.streamversebe.Converter.UserConvert;
import com.example.streamversebe.DTOs.DTO.UserDTO;
import com.example.streamversebe.Model.Entity.Users;
import com.example.streamversebe.Model.Enum.EStatus;
import com.example.streamversebe.Repository.Interface.UserRepository;
import com.example.streamversebe.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserConvert userConvert;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        Users user = userConvert.toUser(userDTO);
        return userConvert.toUserDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDTO updateUser(ObjectId id, UserDTO userDTO) {
//        Optional<Users> existingUser = userRepository.findById(id);
//        if (existingUser.isPresent()) {
//            Users user = existingUser.get();
//            BeanUtils.copyProperties(userDTO, user, "id");
//            Users updatedUser = userRepository.save(user);
//            UserDTO updatedUserDTO = new UserDTO();
//            BeanUtils.copyProperties(updatedUser, updatedUserDTO, "id");
//            return updatedUserDTO;
//        }
//        return null;
        return userRepository.findById(id)
                .map(user -> {
                    Users users = userConvert.toUser(userDTO);
                    return userConvert.toUserDTO(userRepository.save(users));
                })
                .orElse(null);
    }

    @Override
    public Optional<UserDTO> getUserById(ObjectId id) {
        return userRepository.findById(id).map(userConvert::toUserDTO);
    }


    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userConvert::toUserDTO);
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userConvert::toUserDTO);
    }

    @Override
    public boolean deleteUser(ObjectId id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userConvert::toUserDTO)
                .toList();
    }

    @Override
    public void saveUser(Users chatUser) {
        chatUser.setStatus(EStatus.ACTIVE);
        userRepository.save(chatUser);
    }

    @Override
    public void disconnect(Users chatUser) {
        var storedUser = userRepository.findByUsername(chatUser.getUsername())
                .orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(EStatus.INACTIVE);
            userRepository.save(storedUser);
        }

    }

    public List<Users> findConnectUsers() {
        return userRepository.findByStatus(EStatus.ACTIVE);
    }

}
