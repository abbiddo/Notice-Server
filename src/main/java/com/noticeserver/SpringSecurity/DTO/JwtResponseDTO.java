package com.noticeserver.SpringSecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
}
