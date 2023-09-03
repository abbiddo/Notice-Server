package com.noticeserver.SpringSecurity.Controller;

import com.noticeserver.SpringSecurity.DTO.JwtRequestDTO;
import com.noticeserver.SpringSecurity.DTO.JwtResponseDTO;
import com.noticeserver.SpringSecurity.DTO.MemberSignupRequestDTO;
import com.noticeserver.SpringSecurity.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO request) {

        try {
            return ResponseEntity.ok().body(authService.login(request));
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(new JwtResponseDTO("failed"));
        }

    }

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody MemberSignupRequestDTO request) {
        return authService.signup(request);
    }

}
