package com.example.sijangtong.service;

import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Rider;

public interface RiderService {

    Long createRider();

    public default RiderDto entityToDto(Rider rider) {

        return RiderDto.builder()
                .riderId(rider.getRiderId())
                .riderName(rider.getRiderName())
                .riderTel(rider.getRiderTel())
                .riderStatus(rider.getRiderStatus()).build();

    }


    public defau

}
