package it.example.hotelautomation.interfaces;

import it.example.hotelautomation.interfaces.entity.HotelDetailView;
import it.example.hotelautomation.interfaces.entity.MovementView;

public interface IHotelAutomationValidator {

    void doMovementValidation(final MovementView movementView, final HotelDetailView hotelDetailView);

}