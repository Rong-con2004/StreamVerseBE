package com.example.streamversebe.Controller;

import com.example.streamversebe.Service.Interface.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenController {

    private final AuthenService authenService;

    
}
