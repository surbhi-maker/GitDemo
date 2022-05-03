package com.element.fleet.ordering.verification;

public class DeliveringDealerTab {
	
	private RecommendedDealer recommendedDealerObject;
	private RequestNewDealer requestNewDealerObject;
	private SlectDifferentDealer selectDifferentDealerObject;

	public RecommendedDealer getRecommendedDealerObject() {
		if(recommendedDealerObject == null) {
			this.recommendedDealerObject = new RecommendedDealer();
		}
		requestNewDealerObject = null;
		selectDifferentDealerObject = null;
		return this.recommendedDealerObject;
	}

	public RequestNewDealer getRequestNewDealerObject() {
		if(requestNewDealerObject == null) {
			this.requestNewDealerObject = new RequestNewDealer();
		}
		recommendedDealerObject = null;
		selectDifferentDealerObject = null;
		return this.requestNewDealerObject;
	}

	public SlectDifferentDealer getSlectDifferentDealerObject() {
		if(selectDifferentDealerObject == null) {
			this.selectDifferentDealerObject = new SlectDifferentDealer();
		}
		recommendedDealerObject = null;
		requestNewDealerObject = null;
		return this.selectDifferentDealerObject;
	}

	public String toString() {
		return "\t"+"Recommended dealer: "+
				((recommendedDealerObject==null)?(null+"\n"):("\n"+recommendedDealerObject))+
				"\t"+"Request new dealer: "+
				((requestNewDealerObject==null)?(null+"\n"):("\n"+requestNewDealerObject))+
				"\t"+"Slect different dealer: "+
				((selectDifferentDealerObject==null)?(null+"\n"):("\n"+selectDifferentDealerObject));
	}
	
	public class RecommendedDealer {
		
		private String tierNumber;
		private String dealerName;
		private String city;
		private String state;
		private String zipcode;
		private String courtesyDeliveringFee;
		private String drivingDistance;
		private String manufacturerDealerCode;
		private String maximumDeliveryDistance;
		private String address;
		
		public String getTierNumber() {
			return tierNumber;
		}

		public void setTierNumber(String tierNumber) {
			this.tierNumber = tierNumber;
		}

		public String getDealerName() {
			return dealerName;
		}

		public void setDealerName(String dealerName) {
			this.dealerName = dealerName;
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

		public String getCourtesyDeliveringFee() {
			return courtesyDeliveringFee;
		}

		public void setCourtesyDeliveringFee(String courtesyDeliveringFee) {
			this.courtesyDeliveringFee = courtesyDeliveringFee;
		}

		public String getDrivingDistance() {
			return drivingDistance;
		}

		public void setDrivingDistance(String drivingDistance) {
			this.drivingDistance = drivingDistance;
		}

		public String getManufacturerDealerCode() {
			return manufacturerDealerCode;
		}

		public void setManufacturerDealerCode(String manufacturerDealerCode) {
			this.manufacturerDealerCode = manufacturerDealerCode;
		}

		public String getMaximumDeliveryDistance() {
			return maximumDeliveryDistance;
		}

		public void setMaximumDeliveryDistance(String maximumDeliveryDistance) {
			this.maximumDeliveryDistance = maximumDeliveryDistance;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String toString() {
			return "\t"+"\t"+"Tier number: "+tierNumber+"\n"+
					"\t"+"\t"+"Dealer name: "+dealerName+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n"+
					"\t"+"\t"+"Courtesy delivering fee: "+courtesyDeliveringFee+"\n"+
					"\t"+"\t"+"Driving distance: "+drivingDistance+"\n"+
					"\t"+"\t"+"Manufacturer dealer code: "+manufacturerDealerCode+"\n"+
					"\t"+"\t"+"Maximum delivery distance: "+maximumDeliveryDistance+"\n"+
					"\t"+"\t"+"Address: "+address+"\n";
		}
		
	}
	
	public class RequestNewDealer {
		
		private String dealerName;
		private String city;
		private String state;
		private String zipcode;
		private String address;
		private String county;
		private String country;
		private String dealerContactName;
		private String phoneNumber;
		private String extension;
		private String email;
		
		public String getDealerName() {
			return dealerName;
		}

		public void setDealerName(String dealerName) {
			this.dealerName = dealerName;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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

		public String getDealerContactName() {
			return dealerContactName;
		}

		public void setDealerContactName(String dealerContactName) {
			this.dealerContactName = dealerContactName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getExtension() {
			return extension;
		}

		public void setExtension(String extension) {
			this.extension = extension;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String toString() {
			return "\t"+"\t"+"Dealer name: "+dealerName+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n"+
					"\t"+"\t"+"Address: "+address+"\n"+
					"\t"+"\t"+"County: "+county+"\n"+
					"\t"+"\t"+"Country: "+country+"\n"+
					"\t"+"\t"+"Dealer contact name: "+dealerContactName+"\n"+
					"\t"+"\t"+"Phone number: "+phoneNumber+"\n"+
					"\t"+"\t"+"Extension: "+extension+"\n"+
					"\t"+"\t"+"Email: "+email+"\n";
		}
		
	}
	
	public class SlectDifferentDealer {
		
		private boolean activeDealer;
		private boolean useState;
		private String dealerName;
		private String city;
		private String state;
		private String zipcode;
		private String address;
		private String county;
		private String country;
		private String manufacturerDealerCode;
		
		public boolean isActiveDealer() {
			return activeDealer;
		}

		public void setActiveDealer(boolean activeDealer) {
			this.activeDealer = activeDealer;
		}

		public boolean isUseState() {
			return useState;
		}

		public void setUseState(boolean useState) {
			this.useState = useState;
		}

		public String getDealerName() {
			return dealerName;
		}

		public void setDealerName(String dealerName) {
			this.dealerName = dealerName;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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

		public String getManufacturerDealerCode() {
			return manufacturerDealerCode;
		}

		public void setManufacturerDealerCode(String manufacturerDealerCode) {
			this.manufacturerDealerCode = manufacturerDealerCode;
		}

		public String toString() {
			return "\t"+"\t"+"Use state: "+useState+"\n"+
					"\t"+"\t"+"Active dealer: "+activeDealer+"\n"+
					"\t"+"\t"+"Dealer name: "+dealerName+"\n"+
					"\t"+"\t"+"City: "+city+"\n"+
					"\t"+"\t"+"State: "+state+"\n"+
					"\t"+"\t"+"Zipcode: "+zipcode+"\n"+
					"\t"+"\t"+"Address: "+address+"\n"+
					"\t"+"\t"+"County: "+county+"\n"+
					"\t"+"\t"+"Country: "+country+"\n"+
					"\t"+"\t"+"Manufacturer dealer code: "+manufacturerDealerCode+"\n";
		}
		
	}

}
