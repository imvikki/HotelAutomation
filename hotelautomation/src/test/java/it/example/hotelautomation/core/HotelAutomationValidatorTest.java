package it.example.hotelautomation.core;

import it.example.hotelautomation.common.HotelAutomationErrorConstants;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.entity.MovementView;
import it.example.hotelautomation.interfaces.exception.HotelAutomationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class HotelAutomationValidatorTest {

    @Test
    public void movementInNonExistFloor() {
        final MovementView movementView = new MovementView();
        movementView.setFloorNo(3);
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        String actualError = "";
        try {
            new HotelAutomationValidator().doMovementValidation(movementView, hotelDetailView);
        } catch (HotelAutomationException e) {
            actualError = e.getMessage();
        }
        assertEquals(HotelAutomationErrorConstants.INVALID_MOVEMENT_FLOOR.getValue(), actualError);
    }

    @Test
    public void movementInExistedFloor() {
        final MovementView movementView = new MovementView();
        movementView.setFloorNo(2);
        movementView.setSubCorridorNo(2);
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        String actualError = "";
        try {
            new HotelAutomationValidator().doMovementValidation(movementView, hotelDetailView);
        } catch (HotelAutomationException e) {
            actualError = e.getMessage();
        }
        assertEquals("", actualError);
    }

    @Test
    public void movementInExistedSubCorridor() {
        final MovementView movementView = new MovementView();
        movementView.setFloorNo(3);
        movementView.setSubCorridorNo(2);
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(3);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        String actualError = "";
        try {
            new HotelAutomationValidator().doMovementValidation(movementView, hotelDetailView);
        } catch (HotelAutomationException e) {
            actualError = e.getMessage();
        }
        assertEquals("", actualError);
    }

    @Test
    public void movementInNonExistedSubCorridor() {
        final MovementView movementView = new MovementView();
        movementView.setFloorNo(3);
        movementView.setSubCorridorNo(4);
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(3);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        String actualError = "";
        try {
            new HotelAutomationValidator().doMovementValidation(movementView, hotelDetailView);
        } catch (HotelAutomationException e) {
            actualError = e.getMessage();
        }
        assertEquals(HotelAutomationErrorConstants.INVALID_MOVEMENT_SUB_CORRIDOR.getValue(), actualError);
    }

}