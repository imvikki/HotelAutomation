package it.example.hotelautomation.interfaces.entity;

public class SubCorridor {

    private Integer subCorridorNo;
    private String lightStatus;
    private String acStatus;

    public Integer getSubCorridorNo() {
        return subCorridorNo;
    }

    public void setSubCorridorNo(Integer subCorridorNo) {
        this.subCorridorNo = subCorridorNo;
    }

    public String getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(String lightStatus) {
        this.lightStatus = lightStatus;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }
}
