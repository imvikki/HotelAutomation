package it.example.hotelautomation.interfaces;

import it.example.hotelautomation.interfaces.entity.Floor;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;

import java.util.List;

public interface IHotelSensorManager {

    Long SwitchOFFSCACIfPowerExceeds(final List<Floor> floors, final HotelDetailView hotelDetailView);

    void changeSCLightStatusBasedOnMovement(final List<Floor> floors, final HotelDetailView hotelDetailView);

}