package com.example.sijangtong.entity;

import com.example.sijangtong.constant.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    private String memberEmail;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private String memberAddress;

    @Column(nullable = false)
    private String memberPwd;

    @Column
    private String memberName;

    @Column
    private String memberTell;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;
}
