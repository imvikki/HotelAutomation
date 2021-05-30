package it.example.hotelautomation.common;

public enum HotelAutomationConstants {

    ON("ON"),
    OFF("OFF"),
    FROM_TIME("06:00:00"),
    TO_TIME("18:00:00"),
    MAIN_CORRIDOR_MAX_POWER(15),
    SUB_CORRIDOR_MAX_POWER(10),
    UNIT_CONSUMED_BY_LIGHT(5),
    UNIT_CONSUMED_BY_AC(10);

    private String value;
    private Integer number;

    HotelAutomationConstants(String value) {
        this.value = value;
    }

    HotelAutomationConstants(Integer number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public Integer getNumber() {
        return number;
    }

}