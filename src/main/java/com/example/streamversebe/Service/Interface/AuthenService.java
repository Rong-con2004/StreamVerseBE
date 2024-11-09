package com.example.streamversebe.Service.Interface;

import com.example.streamversebe.DTOs.Reponse.AuthRes;
import com.example.streamversebe.DTOs.Request.LoginReq;
import com.example.streamversebe.DTOs.Request.RegisterReq;

public interface AuthenService {
    AuthRes register(RegisterReq request);

    AuthRes login(LoginReq request);
}
