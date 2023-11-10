package com.maliha.doctormanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer roomNo;
    private Integer floorNo;

    public RoomEntity() {
    }

    public RoomEntity(Integer id, Integer roomNo, Integer floorNo) {
        this.id = id;
        this.roomNo = roomNo;
        this.floorNo = floorNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }
}
