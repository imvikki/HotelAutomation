package it.example.hotelautomation.common.utils;

import it.example.hotelautomation.common.HotelAutomationConstants;

import java.time.LocalTime;

public class DateTimeUtil {

    public static Boolean isDayTime() {
        final LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(LocalTime.parse(HotelAutomationConstants.FROM_TIME.getValue()))
                && currentTime.isBefore(LocalTime.parse(HotelAutomationConstants.TO_TIME.getValue()));
    }

}
