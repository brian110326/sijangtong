package com.example.sijangtong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.repository.riderAllocate.RiderRepositoryAllocate;

import java.util.List;
import com.example.sijangtong.constant.RiderStatus;

public interface RiderRepository extends JpaRepository<Rider, Long>, RiderRepositoryAllocate {

}
