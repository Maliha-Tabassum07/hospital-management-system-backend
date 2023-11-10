package com.maliha.doctormanagement.repository;


import com.maliha.doctormanagement.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
}
