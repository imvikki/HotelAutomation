package it.example.hotelautomation.interfaces.entity;

public class MovementView {

    private Boolean isMovement;
    private Integer floorNo;
    private Integer subCorridorNo;

    public Boolean getIsMovement() {
        return isMovement;
    }

    public void setIsMovement(Boolean movement) {
        isMovement = movement;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getSubCorridorNo() {
        return subCorridorNo;
    }

    public void setSubCorridorNo(Integer subCorridorNo) {
        this.subCorridorNo = subCorridorNo;
    }
}
