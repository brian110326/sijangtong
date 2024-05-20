package com.example.sijangtong.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthMemberDto extends User {

    private MemberDto memberDto;

    public AuthMemberDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }

    public AuthMemberDto(MemberDto memberDto) {
        super(memberDto.getMemberEmail(), memberDto.getMemberPwd(),
                List.of(new SimpleGrantedAuthority("ROLE_" + memberDto.getMemberRole())));

        this.memberDto = memberDto;

    }

}
