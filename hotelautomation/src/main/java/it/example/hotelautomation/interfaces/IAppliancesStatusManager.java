package it.example.hotelautomation.interfaces;

import it.example.hotelautomation.interfaces.entity.HotelAppliancesView;
import it.example.hotelautomation.interfaces.entity.HotelDetailView;

public interface IAppliancesStatusManager {

    HotelAppliancesView getHotelAppliancesStatus(final HotelDetailView hotelDetailView);

}