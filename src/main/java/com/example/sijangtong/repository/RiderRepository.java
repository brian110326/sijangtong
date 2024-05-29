package com.example.sijangtong.repository;

import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.repository.riderAllocate.RiderRepositoryAllocate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RiderRepository
  extends JpaRepository<Rider, Long>, RiderRepositoryAllocate {
  @Query("SELECT r FROM Rider r")
  Page<Rider> getRiderList(Pageable pageable);
}
