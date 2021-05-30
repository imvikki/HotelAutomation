package it.example.hotelautomation.interfaces.entity;

import java.util.List;

public class HotelAppliancesView {

    List<Floor> floorList;

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
}
