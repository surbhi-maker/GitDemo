package com.element.fleet.ordering.verification;

public class Test {
	
	public static void main(String[] args) {
		ElementOrder a = new ElementOrder();
		
		a.getStartHereTabObject();
		a.getStartHereTabObject().setNewUnitNumber("9995");
		a.getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().setPoolType("OEM");
		a.getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setFirstName("Shivam");
		a.getStartHereTabObject().getOrderTypeSectionObject().getOrderTypeAsDealerOrder().getClientContactInformationObject().setLastName("Singh");
		
		a.getDriverTabObject();
		a.getDriverTabObject().getDriverInformationSectionObject().setFirstName("Shivam");
		a.getDriverTabObject().getDriverInformationSectionObject().setMiddleName("Singh");
		a.getDriverTabObject().getDriverInformationSectionObject().setLastName("Singh");
		a.getDriverTabObject().getGaragingAddressSectionObject().setAddress1("Address1");
		a.getDriverTabObject().getGaragingAddressSectionObject().setDeliveryAddress(true);
		a.getDriverTabObject().getDeliveryAddressSectionObject().setFirstName("DelFirstname");
		
		a.getVehicleTabObject();
		a.getVehicleTabObject().getVehicleInformationSectionObject().addFleetSpec("001", "1st Fleet Spec","","");
		a.getVehicleTabObject().getVehicleInformationSectionObject().addFleetSpec("002", "2nd Fleet Spec","","");
		a.getVehicleTabObject().getVehicleInformationSectionObject().addFleetSpec("003", "3rd Fleet Spec","","");
		a.getVehicleTabObject().getDealerInstalledOptionsSectionObject().addDIO("1", "1", "Nai pata1", "1", false);
		a.getVehicleTabObject().getDealerInstalledOptionsSectionObject().addDIO("2", "2", "Nai pata2", "2", false);
		a.getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification().setUpfitSpecificationName("Upfit 1");
		a.getVehicleTabObject().getUpfitInformationSectionObject().addAvailableUpfitSpecification();
		a.getVehicleTabObject().getUpfitInformationSectionObject().newAvailableUpfitSpecification().setUpfitSpecificationName("Upfit 2");
		a.getVehicleTabObject().getUpfitInformationSectionObject().addAvailableUpfitSpecification();
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().setUpfitterName("UN1");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().setZipcode("11");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().newlineItem().setCategory("1");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().addLineItem();
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().newlineItem().setCategory("2");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().addLineItem();
		a.getVehicleTabObject().getUpfitInformationSectionObject().addUpfitPurchaseOrder();
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().setUpfitterName("UN2");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().setZipcode("2");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().newlineItem().setCategory("1");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().addLineItem();
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().newlineItem().setCategory("2");
		a.getVehicleTabObject().getUpfitInformationSectionObject().newUpfitPurchaseOrder().addLineItem();
		a.getVehicleTabObject().getUpfitInformationSectionObject().addUpfitPurchaseOrder();
		
		a.getBillingAndRegistrationTabObject();
		a.getBillingAndRegistrationTabObject().getBillingSectionObject().setLeaseTerm("024");
		a.getBillingAndRegistrationTabObject().getRegistrationSectionObject().setWhoToTitle("Client");
		
		a.getDeliveringDealerTabObject();
		a.getDeliveringDealerTabObject().getRecommendedDealerObject().setDealerName("The Rajputs");
		
		System.out.println(a);
	}
	
}
