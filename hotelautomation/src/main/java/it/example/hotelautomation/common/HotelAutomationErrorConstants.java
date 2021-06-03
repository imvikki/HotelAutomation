package it.example.hotelautomation.common;

public enum HotelAutomationErrorConstants {

    INVALID_MOVEMENT_FLOOR("Invalid floor in movement"),
    INVALID_MOVEMENT_SUB_CORRIDOR("Invalid sub-corridor in movement");

    private String value;

    HotelAutomationErrorConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
