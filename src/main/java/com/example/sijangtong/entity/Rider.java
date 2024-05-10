package com.example.sijangtong.entity;

import com.example.sijangtong.constant.RiderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rider {

    @Id
    @SequenceGenerator(name = "rider_seq_gen", sequenceName = "rider_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rider_seq_gen")
    private Long riderId;

    @Column(nullable = false)
    private String riderName;

    @Column(nullable = false)
    private String riderTel;

    @Enumerated(EnumType.STRING)
    private RiderStatus riderStatus;
}
