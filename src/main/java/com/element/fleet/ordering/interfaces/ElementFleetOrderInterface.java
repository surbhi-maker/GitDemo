package com.element.fleet.ordering.interfaces;

import com.element.fleet.ordering.verification.BillingAndRegistrationTab;
import com.element.fleet.ordering.verification.DeliveringDealerTab;
import com.element.fleet.ordering.verification.DriverTab;
import com.element.fleet.ordering.verification.StartHereTab;
import com.element.fleet.ordering.verification.SummaryTab;
import com.element.fleet.ordering.verification.VehicleTab;

public interface ElementFleetOrderInterface {
	public StartHereTab getStartHereTabObject();
	public DriverTab getDriverTabObject();
	public VehicleTab getVehicleTabObject();
	public BillingAndRegistrationTab getBillingAndRegistrationTabObject();
	public DeliveringDealerTab getDeliveringDealerTabObject();
	public SummaryTab getSummaryTabObject();
}
