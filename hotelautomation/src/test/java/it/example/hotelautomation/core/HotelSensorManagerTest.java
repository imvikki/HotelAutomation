package it.example.hotelautomation.core;

import it.example.hotelautomation.interfaces.IHotelAutomationValidator;
import it.example.hotelautomation.interfaces.entity.Floor;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import org.mockito.Mockito;

import java.util.List;

public class HotelSensorManagerTest {

    private HotelSensorManager hotelSensorManager;
    private IHotelAutomationValidator hotelAutomationValidator;

    public void switchOFFSCACIfPowerExceeds(final List<Floor> floors, final HotelDetailView hotelDetailView) {
        hotelSensorManager = new HotelSensorManager();
        hotelAutomationValidator = Mockito.mock(IHotelAutomationValidator.class);
        hotelSensorManager.setHotelAutomationValidator(hotelAutomationValidator);
        hotelSensorManager.SwitchOFFSCACIfPowerExceeds(floors, hotelDetailView);
    }

    public void changeSCLightStatusBasedOnMovement(final List<Floor> floors, final HotelDetailView hotelDetailView) {
        hotelSensorManager = new HotelSensorManager();
        hotelAutomationValidator = Mockito.mock(IHotelAutomationValidator.class);
        hotelSensorManager.setHotelAutomationValidator(hotelAutomationValidator);
        hotelSensorManager.changeSCLightStatusBasedOnMovement(floors, hotelDetailView);
    }

}