package com.example.streamversebe.Controller;

import com.example.streamversebe.DTOs.Reponse.RegisterRes;
import com.example.streamversebe.DTOs.Request.RegisterReq;
import com.example.streamversebe.Service.Interface.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthenController {

    private final AuthenService authenService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRes> register (@Valid @RequestBody RegisterReq registerReq) {
        RegisterRes registerRes = authenService.register(registerReq);
        return ResponseEntity
                .status(registerRes.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(registerRes);
    }

    
}
