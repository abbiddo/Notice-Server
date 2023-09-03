package com.noticeserver.SpringSecurity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequestDTO {
    private String userid;
    private String password;
    private String team;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private String address;
}
