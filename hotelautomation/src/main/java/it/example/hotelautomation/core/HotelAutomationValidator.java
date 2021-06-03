package it.example.hotelautomation.core;

import it.example.hotelautomation.common.HotelAutomationErrorConstants;
import it.example.hotelautomation.interfaces.IHotelAutomationValidator;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.entity.MovementView;
import it.example.hotelautomation.interfaces.exception.HotelAutomationException;
import org.springframework.stereotype.Component;

@Component
public class HotelAutomationValidator implements IHotelAutomationValidator {

    public void doMovementValidation(final MovementView movementView, final HotelDetailView hotelDetailView) {
        if(movementView.getFloorNo() > hotelDetailView.getNoOfFloors()) {
            throw new HotelAutomationException(HotelAutomationErrorConstants.INVALID_MOVEMENT_FLOOR.getValue());
        }
        if(movementView.getSubCorridorNo() > hotelDetailView.getNoOfSubCorridorsPerFloor()) {
            throw new HotelAutomationException(HotelAutomationErrorConstants.INVALID_MOVEMENT_SUB_CORRIDOR.getValue());
        }
    }

}
