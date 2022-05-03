package com.element.fleet.ordering.verification;

import com.element.fleet.ordering.interfaces.ElementFleetOrderInterface;

public class ElementOrder implements ElementFleetOrderInterface {
	
	private StartHereTab startHereTabObject;
	private DriverTab driverTabObject;
	private VehicleTab vehicleTabObject;
	private BillingAndRegistrationTab billingAndRegistrationTabObject;
	private DeliveringDealerTab deliveringDealerTabObject;
	private String logNumber;
	private String clientNumber;
	private SummaryTab summaryTabObject;

	public String getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(String logNumber) {
		this.logNumber = logNumber;
	}

	@Override
	public StartHereTab getStartHereTabObject() {
		if(startHereTabObject == null) {
			this.startHereTabObject = new StartHereTab();
		}		
		return this.startHereTabObject;
	}
	
	@Override
	public DriverTab getDriverTabObject() {
		if(driverTabObject == null) {
			this.driverTabObject = new DriverTab();
		}		
		return this.driverTabObject;
	}

	@Override
	public VehicleTab getVehicleTabObject() {
		if(vehicleTabObject == null) {
			this.vehicleTabObject = new VehicleTab();
		}		
		return this.vehicleTabObject;
	}
	
	@Override
	public BillingAndRegistrationTab getBillingAndRegistrationTabObject() {
		if(billingAndRegistrationTabObject == null) {
			this.billingAndRegistrationTabObject = new BillingAndRegistrationTab();
		}		
		return this.billingAndRegistrationTabObject;
	}
	
	@Override
	public DeliveringDealerTab getDeliveringDealerTabObject() {
		if(deliveringDealerTabObject == null) {
			this.deliveringDealerTabObject = new DeliveringDealerTab();
		}		
		return this.deliveringDealerTabObject;
	}
	
	@Override
	public SummaryTab getSummaryTabObject() {
		if(summaryTabObject == null) {
			this.summaryTabObject = new SummaryTab();
		}		
		return this.summaryTabObject;
	}
	
	public String toString() {
		return "-------------------"+"\n"+
				"Order Details: "+"\n"+
				"-------------------"+"\n"+
				"Log number: "+logNumber+"\n"+
				"Start here tab: "+
				((startHereTabObject==null)?(null+"\n"):("\n"+startHereTabObject.toString()))+
				"Driver tab: "+
				((driverTabObject==null)?(null+"\n"):("\n"+driverTabObject.toString()))+
				"Vehicle tab: "+
				((vehicleTabObject==null)?(null+"\n"):("\n"+vehicleTabObject.toString()))+
				"Billing and registration tab: "+
				((billingAndRegistrationTabObject==null)?(null+"\n"):("\n"+billingAndRegistrationTabObject.toString()))+
				"Dealer tab: "+
				((deliveringDealerTabObject==null)?(null+"\n"):("\n"+deliveringDealerTabObject.toString()))+
				"Summary tab: "+
				((summaryTabObject==null)?(null+"\n"):("\n"+summaryTabObject.toString()));
	}

	public String getClientNumber() {
		return logNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	

}
