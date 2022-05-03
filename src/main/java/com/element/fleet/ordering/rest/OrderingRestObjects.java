package com.element.fleet.ordering.rest;

public interface OrderingRestObjects {

	String RESOURCE_COOKIE = "/login";
	String RESOURCE_AUTH_TOKEN = "/getWebUser";
	String RESOURCE_DELETE_FEATURE_TOGGLE = "/api3/admin/feature/delete";
	String RESOURCE_GET_LOG_NUMBER = "/api3/solr/orderInfo";
	String RESOURCE_CLIENT_INFO = "/api3/solr/client";
	String RESOURCE_START_HERE_PAGE = "/api3/preOrder/startHere";
	String RESOURCE_DRIVER_PAGE = "/api3/preOrder/driver";
	String RESOURCE_DRIVER_INFORMATION = "/api3/driverService/driverUpdate/getDriverInformationByDriverId";
	String RESOURCE_DRIVER_SEARCH = "api3/solr/driverSearch";
	String RESOURCE_VEHICLE_PAGE = "/api3/preOrder/vehicle";
	String RESOURCE_BILLING_PAGE = "api3/preOrder/billing";
	String RESOURCE_SEARCH_DEALER = "/api3/solr/searchDeliveringDealers";
	String RESOURCE_DEALER_PAGE = "api3/preOrder/dealer";
	String RESOURCE_SUBMIT_FOR_APPROVAL_ORDER = "api3/preOrder/submitForApproval";
	String RESOURCE_SUBMIT_ORDER = "api3/preOrder/submit";
	String RESOURCE_CREATE_DIO = "/api3/admin/dealerinstalledoptions/save";
	String RESOURCE_DELETE_DIO = "/api3/admin/dealerinstalledoptions/delete";

	
	public enum OrderingRestOperations {
		START_HERE_PAGE("Start Here Page"),
		DRIVER_SEARCH("Driver Search"),
		DRIVER_PAGE("Driver Page"),
		VEHICLE_PAGE("Vehicle Page"),
		BILLING_PAGE("Billing Page"),
		DEALER_PAGE("Dealer Page"),
		;
			
		private final String value;

		OrderingRestOperations(final String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}
}

