package com.noticeserver.SpringSecurity.Service;

import com.noticeserver.SpringSecurity.Domain.Member;
import com.noticeserver.SpringSecurity.DTO.JwtRequestDTO;
import com.noticeserver.SpringSecurity.DTO.JwtResponseDTO;
import com.noticeserver.SpringSecurity.DTO.MemberSignupRequestDTO;
import com.noticeserver.SpringSecurity.Repository.MemberRepository;
import com.noticeserver.SpringSecurity.Security.JwtTokenProvider;
import com.noticeserver.SpringSecurity.Security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponseDTO login(JwtRequestDTO request) throws Exception{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return createJwtToken(authentication);
    }

    private JwtResponseDTO createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);

        return new JwtResponseDTO(token);
    }

    public String signup(MemberSignupRequestDTO request) {
        //중복 유저 점검
        boolean existMember = memberRepository.existsById(request.getUserid());

        if (existMember)
            return null;

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);


        memberRepository.save(member);

        return member.getEmail();
    }
}
