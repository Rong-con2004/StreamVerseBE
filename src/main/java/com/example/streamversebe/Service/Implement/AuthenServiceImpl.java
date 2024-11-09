package com.example.streamversebe.Service.Implement;

import com.example.streamversebe.DTOs.Reponse.AuthRes;
import com.example.streamversebe.DTOs.Request.LoginReq;
import com.example.streamversebe.DTOs.Request.RegisterReq;
import com.example.streamversebe.Service.Interface.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements AuthenService {

    @Override
    public AuthRes register(RegisterReq request) {
        return null;
    }

    @Override
    public AuthRes login(LoginReq request) {
        return null;
    }

}
