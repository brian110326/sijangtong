package com.example.sijangtong.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
public class Info extends BaseEntity {

    @Id
    @SequenceGenerator(name = "storeinfoid_seq_gen", sequenceName = "storeinfoid_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storeinfoid_seq_gen")
    private Long storeInfoId;

    private String storeTel; // 전화번호

    private String storeTime; // 영업시간

    private String storeAddress; //주소

    private String storeName; // 이름

    private String storeDetail; // 매장 상세정보

    @OneToOne
    private Store store;




    
}
