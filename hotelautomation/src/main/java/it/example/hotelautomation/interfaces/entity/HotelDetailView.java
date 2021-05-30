package it.example.hotelautomation.interfaces.entity;

import java.util.List;

public class HotelDetailView {

    private Integer noOfFloors;
    private Integer noOfMainCorridorsPerFloor;
    private Integer noOfSubCorridorsPerFloor;
    private List<MovementView> movement;

    public Integer getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(Integer noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public Integer getNoOfMainCorridorsPerFloor() {
        return noOfMainCorridorsPerFloor;
    }

    public void setNoOfMainCorridorsPerFloor(Integer noOfMainCorridorsPerFloor) {
        this.noOfMainCorridorsPerFloor = noOfMainCorridorsPerFloor;
    }

    public Integer getNoOfSubCorridorsPerFloor() {
        return noOfSubCorridorsPerFloor;
    }

    public void setNoOfSubCorridorsPerFloor(Integer noOfSubCorridorsPerFloor) {
        this.noOfSubCorridorsPerFloor = noOfSubCorridorsPerFloor;
    }

    public List<MovementView> getMovement() {
        return movement;
    }

    public void setMovement(List<MovementView> movement) {
        this.movement = movement;
    }
}
