package com.example.sijangtong.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StoreImg {

    @Id
    @SequenceGenerator(name = "store_storeimg_seq_gen", sequenceName = "store_storeimg_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_storeimg_seq_gen")
    private Long storeImgId;

    private String stUuid;

    private String stPath;

    private String stImgName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
