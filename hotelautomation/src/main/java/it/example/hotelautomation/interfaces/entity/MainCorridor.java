package it.example.hotelautomation.interfaces.entity;

public class MainCorridor {

    private Integer mainCorridorNo;
    private String lightStatus;
    private String acStatus;

    public Integer getMainCorridorNo() {
        return mainCorridorNo;
    }

    public void setMainCorridorNo(Integer mainCorridorNo) {
        this.mainCorridorNo = mainCorridorNo;
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
