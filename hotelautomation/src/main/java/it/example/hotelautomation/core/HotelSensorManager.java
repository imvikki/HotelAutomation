package it.example.hotelautomation.core;

import it.example.hotelautomation.common.HotelAutomationConstants;
import it.example.hotelautomation.common.utils.DateTimeUtil;
import it.example.hotelautomation.interfaces.IHotelAutomationValidator;
import it.example.hotelautomation.interfaces.IHotelSensorManager;
import it.example.hotelautomation.interfaces.entity.Floor;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.entity.MovementView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelSensorManager implements IHotelSensorManager {

    private IHotelAutomationValidator hotelAutomationValidator;

    @Autowired
    public void setHotelAutomationValidator(IHotelAutomationValidator hotelAutomationValidator) {
        this.hotelAutomationValidator = hotelAutomationValidator;
    }

    @Override
    public void changeSCLightStatusBasedOnMovement(final List<Floor> floors, final HotelDetailView hotelDetailView) {
        for(final MovementView movementView : hotelDetailView.getMovement()){
            hotelAutomationValidator.doMovementValidation(movementView, hotelDetailView);
            if(movementView.getIsMovement()) {
                changeLightStatus(movementView, floors, DateTimeUtil.isDayTime() ? HotelAutomationConstants.OFF.getValue() : HotelAutomationConstants.ON.getValue());
            } else {
                changeLightStatus(movementView, floors, HotelAutomationConstants.OFF.getValue());
            }
        }
    }

    @Override
    public void SwitchOFFSCACIfPowerExceeds(final List<Floor> floors, final HotelDetailView hotelDetailView) {
        final Integer maxPowerConsumptionLimit = getMaxPowerConsumptionLimit(hotelDetailView);
        for(Floor floor : floors) {
            final Long noOfLightsOnInMainCorridor = floor.getMainCorridorList().stream().filter(m -> HotelAutomationConstants.ON.getValue().equalsIgnoreCase(m.getLightStatus())).count();
            final Long noOfLightsOnInSubCorridor = floor.getSubCorridorList().stream().filter(s -> HotelAutomationConstants.ON.getValue().equalsIgnoreCase(s.getLightStatus())).count();
            final Long noOfACsOnInMainCorridor = floor.getMainCorridorList().stream().filter(m -> HotelAutomationConstants.ON.getValue().equalsIgnoreCase(m.getAcStatus())).count();
            final Long noOfACsOnInSubCorridor = floor.getSubCorridorList().stream().filter(s -> HotelAutomationConstants.ON.getValue().equalsIgnoreCase(s.getAcStatus())).count();
            final Long totalPowerConsumed = ((noOfLightsOnInMainCorridor + noOfLightsOnInSubCorridor) * HotelAutomationConstants.UNIT_CONSUMED_BY_LIGHT.getNumber()) +
                    ((noOfACsOnInMainCorridor + noOfACsOnInSubCorridor) * HotelAutomationConstants.UNIT_CONSUMED_BY_AC.getNumber());
            if(totalPowerConsumed > maxPowerConsumptionLimit) {
                floor.getSubCorridorList().stream().filter(s -> HotelAutomationConstants.ON.getValue().equalsIgnoreCase(s.getAcStatus()))
                        .findFirst().get().setAcStatus(HotelAutomationConstants.OFF.getValue());
                SwitchOFFSCACIfPowerExceeds(floors, hotelDetailView);
            }
        }
    }

    private Integer getMaxPowerConsumptionLimit(final HotelDetailView hotelDetailView) {
        final Integer maxPowerConsumptionLimit = (hotelDetailView.getNoOfMainCorridorsPerFloor() * HotelAutomationConstants.MAIN_CORRIDOR_MAX_POWER.getNumber()) +
                (hotelDetailView.getNoOfSubCorridorsPerFloor() * HotelAutomationConstants.SUB_CORRIDOR_MAX_POWER.getNumber());
        return maxPowerConsumptionLimit;
    }

    private void changeLightStatus(final MovementView movementView, final List<Floor> floors, final String lightStatus) {
        floors.stream().forEach(f -> f.getSubCorridorList().stream()
                .filter(s -> movementView.getSubCorridorNo() == s.getSubCorridorNo() && movementView.getFloorNo() == f.getFloorNo())
                .forEach(l -> l.setLightStatus(lightStatus)));
    }

}
