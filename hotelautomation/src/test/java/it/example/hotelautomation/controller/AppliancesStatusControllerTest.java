package it.example.hotelautomation.controller;

import it.example.hotelautomation.interfaces.IAppliancesStatusManager;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.entity.MovementView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class AppliancesStatusControllerTest {

    private AppliancesStatusController appliancesStatusController;
    private IAppliancesStatusManager appliancesStatusManager;

    @Before
    public void setUp() {
        appliancesStatusController = new AppliancesStatusController();
        appliancesStatusManager = Mockito.mock(IAppliancesStatusManager.class);
        appliancesStatusController.setAppliancesStatusManager(appliancesStatusManager);
    }

    @Test
    public void manageAppliancesStatusOK() {
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
        final ResponseEntity responseEntity = appliancesStatusController.manageAppliancesStatus(hotelDetailView);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}