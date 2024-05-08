package com.example.sijangtong.entity;

import com.example.sijangtong.constant.StoreCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Store {

    @Id
    @SequenceGenerator(name = "store_seq_gen", sequenceName = "store_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq_gen")
    private Long storeId;

    @Enumerated(EnumType.STRING)
    private StoreCategory storeCategory;

    // store를 기준으로 info 조회가능
    @OneToOne(mappedBy = "store")
    private Info info;
}
