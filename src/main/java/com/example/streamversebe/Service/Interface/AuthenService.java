package com.example.streamversebe.Service.Interface;

import com.example.streamversebe.DTOs.Reponse.LoginRes;
import com.example.streamversebe.DTOs.Reponse.RegisterRes;
import com.example.streamversebe.DTOs.Request.LoginReq;
import com.example.streamversebe.DTOs.Request.RegisterReq;

public interface AuthenService {
    RegisterRes register(RegisterReq request);

    LoginRes login(LoginReq request);

}
