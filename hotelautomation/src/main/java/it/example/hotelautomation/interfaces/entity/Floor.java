package it.example.hotelautomation.interfaces.entity;

import java.util.List;

public class Floor {

    private Integer floorNo;
    private List<MainCorridor> mainCorridorList;
    private List<SubCorridor> subCorridorList;

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public List<MainCorridor> getMainCorridorList() {
        return mainCorridorList;
    }

    public void setMainCorridorList(List<MainCorridor> mainCorridorList) {
        this.mainCorridorList = mainCorridorList;
    }

    public List<SubCorridor> getSubCorridorList() {
        return subCorridorList;
    }

    public void setSubCorridorList(List<SubCorridor> subCorridorList) {
        this.subCorridorList = subCorridorList;
    }
}
