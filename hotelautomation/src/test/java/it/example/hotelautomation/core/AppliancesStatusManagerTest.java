package it.example.hotelautomation.core;

import it.example.hotelautomation.common.HotelAutomationConstants;
import it.example.hotelautomation.common.utils.DateTimeUtil;
import it.example.hotelautomation.interfaces.IHotelSensorManager;
import it.example.hotelautomation.interfaces.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class AppliancesStatusManagerTest {

    private AppliancesStatusManager appliancesStatusManager;
    private IHotelSensorManager hotelSensorManager;

    @Before
    public void setUp() {
        appliancesStatusManager = new AppliancesStatusManager();
        hotelSensorManager = Mockito.mock(IHotelSensorManager.class);
        appliancesStatusManager.setHotelSensorManager(hotelSensorManager);
    }

    @Test
    public void defaultStatusNightTime() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(!isDayTime) {
            for(final Floor floor : hotelAppliancesView.getFloorList()) {
                checkNightUtilityOfSubCorridorWithoutMovement(floor);
                checkNightUtilityOfMainCorridorWithoutMovement(floor);
            }
        }
    }

    private void checkNightUtilityOfSubCorridorWithoutMovement(final Floor floor) {
        for(final SubCorridor subCorridor : floor.getSubCorridorList()) {
            assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getLightStatus());
            assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getAcStatus());
        }
    }

    private void checkNightUtilityOfMainCorridorWithoutMovement(final Floor floor) {
        for(final MainCorridor mainCorridor : floor.getMainCorridorList()) {
            assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getLightStatus());
            assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getAcStatus());
        }
    }

    @Test
    public void defaultStatusDayTime() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(isDayTime) {
            for(final Floor floor : hotelAppliancesView.getFloorList()) {
                checkDayUtilityOfSubCorridorWithoutMovement(floor);
                checkDayUtilityOfMainCorridorWithoutMovement(floor);
            }
        }
    }

    @Test
    public void dayTimeWithMovementINSC2OFFirstFloor() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final MovementView movementView = new MovementView();
        movementView.setIsMovement(Boolean.TRUE);
        movementView.setFloorNo(1);
        movementView.setSubCorridorNo(2);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(isDayTime) {
            for(final Floor floor : hotelAppliancesView.getFloorList()) {
                checkDayUtilityOfSubCorridorWithoutMovement(floor);
                checkDayUtilityOfMainCorridorWithoutMovement(floor);
            }
        }
    }

    @Test
    public void dayTimeWithoutMovementINSC2OFFirstFloor() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final MovementView movementView = new MovementView();
        movementView.setIsMovement(Boolean.FALSE);
        movementView.setFloorNo(1);
        movementView.setSubCorridorNo(2);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(isDayTime) {
            for(final Floor floor : hotelAppliancesView.getFloorList()) {
                checkDayUtilityOfSubCorridorWithoutMovement(floor);
                checkDayUtilityOfMainCorridorWithoutMovement(floor);
            }
        }
    }

    @Test
    public void nightTimeWithMovementINSC2OFFirstFloor() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final MovementView movementView = new MovementView();
        movementView.setIsMovement(Boolean.TRUE);
        movementView.setFloorNo(1);
        movementView.setSubCorridorNo(2);
        List<MovementView> movementViewList = new ArrayList<>();
        movementViewList.add(movementView);
        hotelDetailView.setMovement(movementViewList);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(!isDayTime) {
            final List<Floor> powerOptimizedFloor = hotelAppliancesView.getFloorList();
            new HotelSensorManagerTest().changeSCLightStatusBasedOnMovement(powerOptimizedFloor, hotelDetailView);
            new HotelSensorManagerTest().switchOFFSCACIfPowerExceeds(powerOptimizedFloor, hotelDetailView);
            for(final Floor floor : powerOptimizedFloor) {
                checkNightUtilityOfSubCorridorWithMovement(floor);
                checkNightUtilityOfMainCorridorWithMovement(floor);
            }
        }
    }

    @Test
    public void nightTimeWithoutMovementINSC2OFFirstFloor() {
        final HotelDetailView hotelDetailView = new HotelDetailView();
        hotelDetailView.setNoOfFloors(2);
        hotelDetailView.setNoOfMainCorridorsPerFloor(1);
        hotelDetailView.setNoOfSubCorridorsPerFloor(2);
        final MovementView movementView = new MovementView();
        movementView.setIsMovement(Boolean.FALSE);
        movementView.setFloorNo(1);
        movementView.setSubCorridorNo(2);
        List<MovementView> movementViewList = new ArrayList<>();
        movementViewList.add(movementView);
        hotelDetailView.setMovement(movementViewList);
        final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
        assertEquals(2, hotelAppliancesView.getFloorList().size());
        final Boolean isDayTime = DateTimeUtil.isDayTime();
        if(!isDayTime) {
            final List<Floor> powerOptimizedFloor = hotelAppliancesView.getFloorList();
            new HotelSensorManagerTest().changeSCLightStatusBasedOnMovement(powerOptimizedFloor, hotelDetailView);
            new HotelSensorManagerTest().switchOFFSCACIfPowerExceeds(powerOptimizedFloor, hotelDetailView);
            for(final Floor floor : powerOptimizedFloor) {
                checkNightUtilityOfSubCorridorWithoutMovement(floor);
                checkNightUtilityOfMainCorridorWithoutMovement(floor);
            }
        }
    }

    private void checkNightUtilityOfMainCorridorWithMovement(final Floor floor) {
        for(final MainCorridor mainCorridor : floor.getMainCorridorList()) {
            if(floor.getFloorNo() == 1 && mainCorridor.getMainCorridorNo() == 1) {
                assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getAcStatus());
            }
            if(floor.getFloorNo() == 2 && mainCorridor.getMainCorridorNo() == 1) {
                assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getAcStatus());
            }
        }
    }

    private void checkNightUtilityOfSubCorridorWithMovement(final Floor floor) {
        for(final SubCorridor subCorridor : floor.getSubCorridorList()) {
            if(floor.getFloorNo() == 1 && subCorridor.getSubCorridorNo() == 1) {
                assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getAcStatus());
            }
            if(floor.getFloorNo() == 1 && subCorridor.getSubCorridorNo() == 2) {
                assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getAcStatus());
            }
            if(floor.getFloorNo() == 2 && subCorridor.getSubCorridorNo() == 1) {
                assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getAcStatus());
            }
            if(floor.getFloorNo() == 2 && subCorridor.getSubCorridorNo() == 2) {
                assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getLightStatus());
                assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getAcStatus());
            }
        }
    }

    private void checkDayUtilityOfSubCorridorWithoutMovement(final Floor floor) {
        for(final SubCorridor subCorridor : floor.getSubCorridorList()) {
            assertEquals(HotelAutomationConstants.OFF.getValue(), subCorridor.getLightStatus());
            assertEquals(HotelAutomationConstants.ON.getValue(), subCorridor.getAcStatus());
        }
    }

    private void checkDayUtilityOfMainCorridorWithoutMovement(final Floor floor) {
        for(final MainCorridor mainCorridor : floor.getMainCorridorList()) {
            assertEquals(HotelAutomationConstants.OFF.getValue(), mainCorridor.getLightStatus());
            assertEquals(HotelAutomationConstants.ON.getValue(), mainCorridor.getAcStatus());
        }
    }

}