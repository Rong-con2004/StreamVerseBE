package com.example.streamversebe.Service.Implement;

import com.example.streamversebe.DTOs.Reponse.LoginRes;
import com.example.streamversebe.DTOs.Reponse.RegisterRes;
import com.example.streamversebe.DTOs.Request.LoginReq;
import com.example.streamversebe.DTOs.Request.RegisterReq;
import com.example.streamversebe.Model.Entity.Users;
import com.example.streamversebe.Model.Enum.ERole;
import com.example.streamversebe.Repository.Interface.UserRepository;
import com.example.streamversebe.Security.Config.UserDetail;
import com.example.streamversebe.Service.Interface.AuthenService;
import com.example.streamversebe.Service.JWT.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements AuthenService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterRes register(RegisterReq request) {
        validateEmail(request.getEmail()); // check email
        validatePassword(request.getPassword()); // check password
        var user = Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.ROLE_USER)
                .isEnable(false)
                .build();
        userRepository.save(user);
        return RegisterRes.builder()
                .message("Register successfully. Please verify your account.")
                .email(user.getEmail())
                .status(true)
                .build();
    }

    @Override
    public LoginRes login(LoginReq request) {
        // Tìm user dựa trên email
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Kiểm tra tài khoản đã kích hoạt chưa
        if (!user.isEnable()) {
            throw new IllegalStateException("Account is not verified. Please verify your email.");
        }

        // Tạo Access Token và Refresh Token
        UserDetail userDetail = new UserDetail(user);
        String accessToken = jwtService.generateToken(userDetail);
        String refreshToken = jwtService.generateRefreshToken(userDetail);

        return LoginRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .status(true)
                .message("Login successful")
                .build();
    }

    private void validateEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().isEnable()) {
                throw new UsernameNotFoundException(String.format("User with email %s already exists" , email));
            }
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, include an uppercase letter, an lowercase letter and a number.");
        }
    }
}
