package it.example.hotelautomation.core;

import it.example.hotelautomation.common.HotelAutomationConstants;
import it.example.hotelautomation.common.utils.DateTimeUtil;
import it.example.hotelautomation.interfaces.IAppliancesStatusManager;
import it.example.hotelautomation.interfaces.IHotelSensorManager;
import it.example.hotelautomation.interfaces.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppliancesStatusManager implements IAppliancesStatusManager {

    @Autowired
    private IHotelSensorManager hotelSensorManager;

    @Override
    public HotelAppliancesView getHotelAppliancesStatus(final HotelDetailView hotelDetailView) {
        final HotelAppliancesView hotelAppliancesView = getDefaultHotelAppliancesUtility(hotelDetailView);
        final List<Floor> floorUtilityList = hotelAppliancesView.getFloorList();
        hotelSensorManager.changeSCLightStatusBasedOnMovement(floorUtilityList, hotelDetailView);
        hotelSensorManager.SwitchOFFSCACIfPowerExceeds(floorUtilityList, hotelDetailView);
        return hotelAppliancesView;
    }

    public HotelAppliancesView getDefaultHotelAppliancesUtility(final HotelDetailView hotelDetailView) {
        final HotelAppliancesView hotelAppliancesView = new HotelAppliancesView();
        final List<Floor> floorUtilityList = getHotelAppliancesUtility(hotelDetailView);
        hotelAppliancesView.setFloorList(floorUtilityList);
        return hotelAppliancesView;
    }

    public  List<Floor> getHotelAppliancesUtility(final HotelDetailView hotelDetailView) {
        int floorNumber = 1;
        final List<Floor> floors = new ArrayList<>(hotelDetailView.getNoOfFloors());
        for(int i = 0; i < hotelDetailView.getNoOfFloors(); i++) {
            final Floor floor = new Floor();
            floor.setFloorNo(floorNumber);
            floor.setMainCorridorList(getMainCorridor(hotelDetailView.getNoOfMainCorridorsPerFloor()));
            floor.setSubCorridorList(getSubCorridor(hotelDetailView.getNoOfSubCorridorsPerFloor()));
            floors.add(floor);
            floorNumber++;
        }
        return floors;
    }

    public List<MainCorridor> getMainCorridor(final int noOfMainCorridorsPerFloor) {
        int mainCorridorNo = 1;
        final List<MainCorridor> mainCorridors = new ArrayList<>(noOfMainCorridorsPerFloor);
        final String lightStatus = DateTimeUtil.isDayTime() ? HotelAutomationConstants.OFF.getValue() : HotelAutomationConstants.ON.getValue();
        for(int j = 0; j < noOfMainCorridorsPerFloor; j++) {
            final MainCorridor mainCorridor = new MainCorridor();
            mainCorridor.setMainCorridorNo(mainCorridorNo);
            mainCorridor.setLightStatus(lightStatus);
            mainCorridor.setAcStatus(HotelAutomationConstants.ON.getValue());
            mainCorridors.add(mainCorridor);
            mainCorridorNo++;
        }
        return mainCorridors;
    }

    public List<SubCorridor> getSubCorridor(final int noOfSubCorridorsPerFloor) {
        int subCorridorNo = 1;
        final List<SubCorridor> subCorridors = new ArrayList<>(noOfSubCorridorsPerFloor);
        for(int j = 0; j < noOfSubCorridorsPerFloor; j++) {
            final SubCorridor subCorridor = new SubCorridor();
            subCorridor.setSubCorridorNo(subCorridorNo);
            subCorridor.setLightStatus(HotelAutomationConstants.OFF.getValue());
            subCorridor.setAcStatus(HotelAutomationConstants.ON.getValue());
            subCorridors.add(subCorridor);
            subCorridorNo++;
        }
        return subCorridors;
    }

}
