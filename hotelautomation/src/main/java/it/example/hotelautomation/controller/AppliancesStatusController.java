package it.example.hotelautomation.controller;

import it.example.hotelautomation.interfaces.IAppliancesStatusManager;
import it.example.hotelautomation.interfaces.entity.HotelAppliancesView;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.exception.HotelAutomationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotelautomation")
public class AppliancesStatusController {

    private IAppliancesStatusManager appliancesStatusManager;

    @Autowired
    public void setAppliancesStatusManager(IAppliancesStatusManager appliancesStatusManager) {
        this.appliancesStatusManager = appliancesStatusManager;
    }

    @PostMapping(value = "appliancesstatus")
    public ResponseEntity manageAppliancesStatus(@RequestBody final HotelDetailView hotelDetailView) {
        ResponseEntity responseEntity;
        try {
            final HotelAppliancesView hotelAppliancesView = appliancesStatusManager.getHotelAppliancesStatus(hotelDetailView);
            responseEntity = new ResponseEntity(hotelAppliancesView, HttpStatus.OK);
        } catch(HotelAutomationException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity("Invalid input", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
