package com.example.sijangtong.entity;

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

    private String storeTel;

    private String storeTime;

    private String storeAddress;

    private String storeName;

    private String storeDetail;

    private Store store;

}
