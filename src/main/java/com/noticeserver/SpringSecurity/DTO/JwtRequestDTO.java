package com.noticeserver.SpringSecurity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDTO {
    private String email;
    private String password;
}
