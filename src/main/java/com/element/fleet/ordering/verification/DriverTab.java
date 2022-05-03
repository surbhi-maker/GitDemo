package com.element.fleet.ordering.verification;

public class DriverTab {
	
	private String orderID;
	private DriverInformationSection driverInformationSectionObject;
	private GaragingAddressSection garagingAddressSectionObject;
	private DeliveryAddressSection deliveryAddressSectionObject;
	private CustomDriverFieldsSection customDriverFieldsSectionObject;
	
	public DriverInformationSection getDriverInformationSectionObject() {		
		if(driverInformationSectionObject == null) {
			this.driverInformationSectionObject = new DriverInformationSection();
		}		
		return this.driverInformationSectionObject;
	}
	
	public GaragingAddressSection getGaragingAddressSectionObject() {		
		if(garagingAddressSectionObject == null) {
			this.garagingAddressSectionObject = new GaragingAddressSection();
		}		
		return this.garagingAddressSectionObject;
	}
	
	public DeliveryAddressSection getDeliveryAddressSectionObject() {		
		if(getGaragingAddressSectionObject().getDeliveryAddress()) {
			if(deliveryAddressSectionObject == null) {
				this.deliveryAddressSectionObject = new DeliveryAddressSection();
			}
		}
		return this.deliveryAddressSectionObject;
	}
	
	public CustomDriverFieldsSection getCustomDriverFieldsSectionObject() {		
		if(customDriverFieldsSectionObject == null) {
			this.customDriverFieldsSectionObject = new CustomDriverFieldsSection();
		}		
		return this.customDriverFieldsSectionObject;
	}
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public String toString() {
		return "\t"+"Order ID: "+orderID+"\n"+
				"\t"+"Driver information: "+
				((driverInformationSectionObject==null)?(null+"\n"):("\n"+driverInformationSectionObject.toString()))+
				"\t"+"Garaging address: "+
				((garagingAddressSectionObject==null)?(null+"\n"):("\n"+garagingAddressSectionObject.toString()))+
				"\t"+"Delivery address: "+
				((deliveryAddressSectionObject==null)?(null+"\n"):("\n"+deliveryAddressSectionObject.toString()))+
				"\t"+"Custom driver fields: "+
				((customDriverFieldsSectionObject==null)?(null+"\n"):("\n"+customDriverFieldsSectionObject.toString()));
	}
	
	public class DriverInformationSection {
		
		private String firstName;
		private String middleName;
		private String lastName;
		private String employeeID;
		private String email;
		private String primaryPhone;
		private String breakdown;
		private String address1;
		private String address2;
		private String county;
		private String country;
		private String city;
		private String state;
		private String zipcode;
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmployeeID() {
			return employeeID;
		}

		public void setEmployeeID(String employeeID) {
			this.employeeID = employeeID;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPrimaryPhone() {
			return primaryPhone;
		}

		public void setPrimaryPhone(String primaryPhone) {
			this.primaryPhone = primaryPhone;
		}

		public String getBreakdown() {
			return breakdown;
		}

		public void setBreakdown(String breakdown) {
			this.breakdown = breakdown;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		
		public String toString() {
			return "\t"+"\t"+"First name: "+firstName+"\n"+
					"\t"+"\t"+"Middle name: "+middleName+"\n"+
					"\t"+"\t"+"Last number: "+lastName+"\n"+
					"\t"+"\t"+"Employee ID: "+employeeID+"\n"+
					"\t"+"\t"+"Email: "+email+"\n"+
					"\t"+"\t"+"Primary phone: "+primaryPhone+"\n"+
					"\t"+"\t"+"Breakdown: "+breakdown+"\n"+
					"\t"+"\t"+"Address1: "+address1+"\n"+
					"\t"+"\t"+"Address2: "+address2+"\n"+
					"\t"+"\t"+"County: "+county+"\n"+
					"\t"+"\t"+"Country: "+country+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n";
		}
		
	}
	
	public class GaragingAddressSection {
		
		private String address1;
		private String address2;
		private String county;
		private String country;
		private String city;
		private String state;
		private String zipcode;
		private boolean isDeliveryAddress;
		
		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public boolean getDeliveryAddress() {
			return isDeliveryAddress;
		}

		public void setDeliveryAddress(boolean isDeliveryAddress) {
			this.isDeliveryAddress = isDeliveryAddress;
			if(isDeliveryAddress)
				deliveryAddressSectionObject = getDeliveryAddressSectionObject();
			else
				deliveryAddressSectionObject = null;
			
		}
		
		public String toString() {
			return "\t"+"\t"+"Address1: "+address1+"\n"+
					"\t"+"\t"+"Address2: "+address2+"\n"+
					"\t"+"\t"+"County: "+county+"\n"+
					"\t"+"\t"+"Country: "+country+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n"+
					"\t"+"\t"+"Delivery address: "+isDeliveryAddress+"\n";
		}
		
	}
	
	public class DeliveryAddressSection {
		
		private String firstName;
		private String middleName;
		private String lastName;
		private String phone;
		private String extension;
		private String address1;
		private String address2;
		private String county;
		private String country;
		private String city;
		private String state;
		private String zipcode;
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getExtension() {
			return extension;
		}

		public void setExtension(String extension) {
			this.extension = extension;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		
		public String toString() {
			return "\t"+"\t"+"First name: "+firstName+"\n"+
					"\t"+"\t"+"Middle name: "+middleName+"\n"+
					"\t"+"\t"+"Last number: "+lastName+"\n"+
					"\t"+"\t"+"Phone: "+phone+"\n"+
					"\t"+"\t"+"Extension: "+extension+"\n"+
					"\t"+"\t"+"Address1: "+address1+"\n"+
					"\t"+"\t"+"Address2: "+address2+"\n"+
					"\t"+"\t"+"County: "+county+"\n"+
					"\t"+"\t"+"Country: "+country+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n";
		}
		
	}
	
	public class CustomDriverFieldsSection {
		
		private String inputLastOdometer;
		private String jobTitle;
		private String managersName;
		private String managersEmailAddress;
		private String managersPhoneNumber;
		private String driverField6;
		private String driverField7;
		private String driverField8;
		private String driverField9;
		private String driverField10;
		private String driverField11;
		private String driverField12;
		private String driverField13;
		private String driverField14;
		private String driverNote;
		
		public String getInputLastOdometer() {
			return inputLastOdometer;
		}

		public void setInputLastOdometer(String inputLastOdometer) {
			this.inputLastOdometer = inputLastOdometer;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getManagersName() {
			return managersName;
		}

		public void setManagersName(String managersName) {
			this.managersName = managersName;
		}

		public String getManagersEmailAddress() {
			return managersEmailAddress;
		}

		public void setManagersEmailAddress(String managersEmailAddress) {
			this.managersEmailAddress = managersEmailAddress;
		}

		public String getManagersPhoneNumber() {
			return managersPhoneNumber;
		}

		public void setManagersPhoneNumber(String managersPhoneNumber) {
			this.managersPhoneNumber = managersPhoneNumber;
		}

		public String getDriverField6() {
			return driverField6;
		}

		public void setDriverField6(String driverField6) {
			this.driverField6 = driverField6;
		}

		public String getDriverField7() {
			return driverField7;
		}

		public void setDriverField7(String driverField7) {
			this.driverField7 = driverField7;
		}

		public String getDriverField8() {
			return driverField8;
		}

		public void setDriverField8(String driverField8) {
			this.driverField8 = driverField8;
		}

		public String getDriverField9() {
			return driverField9;
		}

		public void setDriverField9(String driverField9) {
			this.driverField9 = driverField9;
		}

		public String getDriverField10() {
			return driverField10;
		}

		public void setDriverField10(String driverField10) {
			this.driverField10 = driverField10;
		}

		public String getDriverField11() {
			return driverField11;
		}

		public void setDriverField11(String driverField11) {
			this.driverField11 = driverField11;
		}

		public String getDriverField12() {
			return driverField12;
		}

		public void setDriverField12(String driverField12) {
			this.driverField12 = driverField12;
		}

		public String getDriverField13() {
			return driverField13;
		}

		public void setDriverField13(String driverField13) {
			this.driverField13 = driverField13;
		}

		public String getDriverField14() {
			return driverField14;
		}

		public void setDriverField14(String driverField14) {
			this.driverField14 = driverField14;
		}

		public String getDriverNote() {
			return driverNote;
		}

		public void setDriverNote(String driverNote) {
			this.driverNote = driverNote;
		}

		public String toString() {
			return "\t"+"\t"+"Input last odometer: "+inputLastOdometer+"\n"+
					"\t"+"\t"+"Job title: "+jobTitle+"\n"+
					"\t"+"\t"+"Managers name: "+managersName+"\n"+
					"\t"+"\t"+"Managers email address: "+managersEmailAddress+"\n"+
					"\t"+"\t"+"Managers phone number: "+managersPhoneNumber+"\n"+
					"\t"+"\t"+"Driver field 6: "+driverField6+"\n"+
					"\t"+"\t"+"Driver field 7: "+driverField7+"\n"+
					"\t"+"\t"+"Driver field 8: "+driverField8+"\n"+
					"\t"+"\t"+"Driver field 9: "+driverField9+"\n"+
					"\t"+"\t"+"Driver field 10: "+driverField10+"\n"+
					"\t"+"\t"+"Driver field 11: "+driverField11+"\n"+
					"\t"+"\t"+"Driver field 12: "+driverField12+"\n"+
					"\t"+"\t"+"Driver field 13: "+driverField13+"\n"+
					"\t"+"\t"+"Driver field 14: "+driverField14+"\n"+
					"\t"+"\t"+"Driver note: "+driverNote+"\n";
		}
		
	}

}
