package com.example.streamversebe.Controller;

import com.example.streamversebe.DTOs.DTO.UserDTO;
import com.example.streamversebe.Model.Entity.Users;
import com.example.streamversebe.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable ObjectId id, @RequestBody UserDTO userDTO) {
        UserDTO updateUserDTO = userService.updateUser(id, userDTO);
        return updateUserDTO != null ? ResponseEntity.ok(updateUserDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getInfoById(@PathVariable ObjectId id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user/find")
    public ResponseEntity<?> findByEmailOrUsername(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username) {
        Optional<UserDTO> user;
        if (email != null) {
            return userService.getUserByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (username != null) {
            return userService.getUserByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().body("Email or Username is required");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable ObjectId id) {
        boolean success = userService.deleteUser(id);
        String message = success ? "User successfully deleted" : "User not found";

        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(message);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    @GetMapping("/chat")
//    public ResponseEntity<UserDTO> getCChatUsdr(){
//        var user = userService.getUserByEmail("vanman160504@gmail.com");
//        return ResponseEntity.ok(new UserDTO().builder()
//                        .username(user.get().getUsername())
//                        .status(user.get().getStatus())
//                        .username(user.get().getUsername())
//                .build() );
//    }
    @MessageMapping("/chat.addUser")
    @SendTo("/user/topic")
     public Users addChatUser( @Payload Users chatUserDTO) {
         userService.saveUser(chatUserDTO);
         return chatUserDTO;
     }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public Users disconnectUser(@Payload Users disChatuser) {
        userService.disconnect(disChatuser);
        return disChatuser;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectUsers());
    }
}
