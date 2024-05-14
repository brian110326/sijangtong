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
@ToString(exclude = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductImg {

    @Id
    @SequenceGenerator(name = "store_productimg_seq_gen", sequenceName = "store_productimg_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_productimg_seq_gen")
    private Long imgId;

    private String uuid;

    private String path;

    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
