package com.element.fleet.ordering.verification;

import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;


public class BillingAndRegistrationTab {

	private Billing billingSectionObject;
	private Registration registrationSectionObject;
	private TitleOwner titleOwnerObject;
	private RegisteredOwner registeredOwnerObject;
	private LienHolder lienHolderObject;

	public Billing getBillingSectionObject() {
		if (billingSectionObject == null) {
			this.billingSectionObject = new Billing();
		}
		return this.billingSectionObject;
	}

	public Registration getRegistrationSectionObject() {
		if(registrationSectionObject == null) {
			this.registrationSectionObject = new Registration();
		}
		return this.registrationSectionObject;
	}

	public TitleOwner getTitleOwnerObject() {
		if (titleOwnerObject == null) {
			this.titleOwnerObject = new TitleOwner();
		}
		return this.titleOwnerObject;
	}

	public RegisteredOwner getRegisteredOwnerObject() {
		if (registeredOwnerObject == null) {
			this.registeredOwnerObject = new RegisteredOwner();
		}
		return this.registeredOwnerObject;
	}

	public LienHolder getLienHolderObject() {
		if (lienHolderObject == null) {
			this.lienHolderObject = new LienHolder();
		}
		return this.lienHolderObject;
	}


	public String toString() {
		return "\t" + "Billing: " +
				((billingSectionObject == null) ? (null + "\n") : ("\n" + billingSectionObject.toString())) +
				"\t" + "Registration: " +
				((registrationSectionObject == null) ? (null + "\n") : ("\n" + registrationSectionObject.toString())) +
				"\t" + "TitleOwner: " +
				((titleOwnerObject == null) ? (null + "\n") : ("\n" + titleOwnerObject.toString())) +
				"\t" + "RegistredOwner: " +
				((registeredOwnerObject == null) ? (null + "\n") : ("\n" + registeredOwnerObject.toString())) +
		       "\t" + "Lienholder: " +
				((lienHolderObject == null) ? (null + "\n") : ("\n" + lienHolderObject.toString()));
	}

	public class Billing {

		private String newVehicleBreakdown;
		private String contractType;
		private String leaseTerm;
		private String fundingType;
		private String fundingIndex;
		private String intrestAdder;
		private String productClass;
		private String pricingID;
		private String financeSource;
		private String contractNo;
		private String assetID;
		private String clientPurchaseOrderNumber;

		public String getNewVehicleBreakdown() {
			return newVehicleBreakdown;
		}

		public void setNewVehicleBreakdown(String newVehicleBreakdown) {
			this.newVehicleBreakdown = newVehicleBreakdown;
		}

		public String getContractType() {
			return contractType;
		}

		public void setContractType(String contractType) {
			this.contractType = contractType;
		}

		public String getLeaseTerm() {
			return leaseTerm;
		}

		public void setLeaseTerm(String leaseTerm) {
			switch (leaseTerm) {
				case "Select one...":
					this.leaseTerm = leaseTerm;
					break;
				case "024":
					this.leaseTerm = leaseTerm;
					break;
				case "030":
					this.leaseTerm = leaseTerm;
					break;
				case "036":
					this.leaseTerm = leaseTerm;
					break;
				case "040":
					this.leaseTerm = leaseTerm;
					break;
				case "042":
					this.leaseTerm = leaseTerm;
					break;
				case "045":
					this.leaseTerm = leaseTerm;
					break;
				case "048":
					this.leaseTerm = leaseTerm;
					break;
				case "050":
					this.leaseTerm = leaseTerm;
					break;
				case "055":
					this.leaseTerm = leaseTerm;
					break;
				default:
					throw new  InvalidSwitchCaseException(leaseTerm + " is a invalid Lease Term");
			}
		}

		public String getFundingType() {
			return fundingType;
		}

		public void setFundingType(String fundingType) {
			this.fundingType = fundingType;
		}

		public String getFundingIndex() {
			return fundingIndex;
		}

		public void setFundingIndex(String fundingIndex) {
			this.fundingIndex = fundingIndex;
		}

		public String getIntrestAdder() {
			return intrestAdder;
		}

		public void setIntrestAdder(String intrestAdder) {
			this.intrestAdder = intrestAdder;
		}

		public String getProductClass() {
			return productClass;
		}

		public void setProductClass(String productClass) {
			this.productClass = productClass;
		}

		public String getPricingID() {
			return pricingID;
		}

		public void setPricingID(String pricingID) {
			this.pricingID = pricingID;
		}

		public String getFinanceSource() {
			return financeSource;
		}

		public void setFinanceSource(String financeSource) {
			this.financeSource = financeSource;
		}

		public String getContractNo() {
			return contractNo;
		}

		public void setContractNo(String contractNo) {
			this.contractNo = contractNo;
		}

		public String getAssetID() {
			return assetID;
		}

		public void setAssetID(String assetID) {
			this.assetID = assetID;
		}

		public String getClientPurchaseOrderNumber() {
			return clientPurchaseOrderNumber;
		}

		public void setClientPurchaseOrderNumber(String clientPurchaseOrderNumber) {
			this.clientPurchaseOrderNumber = clientPurchaseOrderNumber;
		}

		public String toString() {
			return "\t" + "\t" + "New vehicle breakdown: " + newVehicleBreakdown + "\n" +
					"\t" + "\t" + "Contract type: " + contractType + "\n" +
					"\t" + "\t" + "Lease term: " + leaseTerm + "\n" +
					"\t" + "\t" + "Funding type: " + fundingType + "\n" +
					"\t" + "\t" + "Funding index: " + fundingIndex + "\n" +
					"\t" + "\t" + "Intrest adder: " + intrestAdder + "\n" +
					"\t" + "\t" + "Product class: " + productClass + "\n" +
					"\t" + "\t" + "Pricing ID: " + pricingID + "\n" +
					"\t" + "\t" + "Finance source: " + financeSource + "\n" +
					"\t" + "\t" + "Contract no: " + contractNo + "\n" +
					"\t" + "\t" + "Asset ID: " + assetID + "\n" +
					"\t" + "\t" + "Client PO number: " + clientPurchaseOrderNumber + "\n";
		}

	}

	public class Registration {

		private String whoToTitle;
		private String plateType;
		private String titleOwnerName;
		private String titleOwnerAddressLine1;
		private String titleOwnerAddressLine2;
		private String registeredOwnerName;
		private String registeredOwnerAddressLine1;
		private String registeredOwnerAddressLine2;
		private String leinholderName;
		private String leinholderAddressLine1;
		private String leinholderAddressLine2;
		private String vehicleRegisteredText;
		private String stateAbbrevText;
		private String stateValueText;

		public String getWhoToTitle() {
			return whoToTitle;
		}

		public void setWhoToTitle(String whoToTitle) {
			switch (whoToTitle) {
				case "Select one...":
					this.whoToTitle = "";
					break;
				case "Client":
					this.whoToTitle = whoToTitle;
					break;
				case "Dealer":
					this.whoToTitle = whoToTitle;
					break;
				case "Element":
					this.whoToTitle = whoToTitle;
					break;
				case "Transporter":
					this.whoToTitle = whoToTitle;
					break;
				default:
					throw new InvalidSwitchCaseException(whoToTitle + " is a invalid Who to tile option");
			}
		}

		public String getPlateType() {
			return plateType;
		}

		public void setPlateType(String plateType) {

			switch (plateType) {
				case "Select one...":
					this.plateType = "";
					break;
				case "Passenger":
					this.plateType = plateType;
					break;
				case "Commercial":
					this.plateType = plateType;
					break;
				case "Trailer":
					this.plateType = plateType;
					break;
				case "IRP":
					this.plateType = plateType;
					break;
				case "Combination":
					this.plateType = plateType;
					break;
				default:
					throw new InvalidSwitchCaseException(plateType + " is a invalid Plate type option");
			}

		}

		public String getTitleOwnerName() {
			return titleOwnerName;
		}

		public void setTitleOwnerName(String titleOwnerName) {
			this.titleOwnerName = titleOwnerName;
		}

		public String getTitleOwnerAddressLine1() {
			return titleOwnerAddressLine1;
		}

		public void setTitleOwnerAddressLine1(String titleOwnerAddressLine1) {
			this.titleOwnerAddressLine1 = titleOwnerAddressLine1;
		}

		public String getTitleOwnerAddressLine2() {
			return titleOwnerAddressLine2;
		}

		public void setTitleOwnerAddressLine2(String titleOwnerAddressLine2) {
			this.titleOwnerAddressLine2 = titleOwnerAddressLine2;
		}

		public String getRegisteredOwnerName() {
			return registeredOwnerName;
		}

		public void setRegisteredOwnerName(String registeredOwnerName) {
			this.registeredOwnerName = registeredOwnerName;
		}

		public String getRegisteredOwnerAddressLine1() {
			return registeredOwnerAddressLine1;
		}

		public void setRegisteredOwnerAddressLine1(String registeredOwnerAddressLine1) {
			this.registeredOwnerAddressLine1 = registeredOwnerAddressLine1;
		}

		public String getRegisteredOwnerAddressLine2() {
			return registeredOwnerAddressLine2;
		}

		public void setRegisteredOwnerAddressLine2(String registeredOwnerAddressLine2) {
			this.registeredOwnerAddressLine2 = registeredOwnerAddressLine2;
		}

		public String getLeinholderName() {
			return leinholderName;
		}

		public void setLeinholderName(String leinholderName) {
			this.leinholderName = leinholderName;
		}

		public String getLeinholderAddressLine1() {
			return leinholderAddressLine1;
		}

		public void setLeinholderAddressLine1(String leinholderAddressLine1) {
			this.leinholderAddressLine1 = leinholderAddressLine1;
		}

		public String getLeinholderAddressLine2() {
			return leinholderAddressLine2;
		}

		public void setLeinholderAddressLine2(String leinholderAddressLine2) {
			this.leinholderAddressLine2 = leinholderAddressLine2;
		}

		public String getVehicleRegisteredText() {
			return vehicleRegisteredText;
		}

		public void setVehicleRegisteredText(String vehicleRegisteredText) {
			this.vehicleRegisteredText = vehicleRegisteredText;
		}
		
		public String getStateAbbrevText() {
			return stateAbbrevText;
		}

		public void setStateAbbrevText(String stateAbbrevText) {
			this.stateAbbrevText = stateAbbrevText;
		}
		
		public String getStateValueText() {
			return stateValueText;
		}

		public void setStateValueText(String stateValueText) {
			this.stateValueText = stateValueText;
		}
		
		public String toString() {
			return "\t" + "\t" + "Who to title: " + whoToTitle + "\n" +
					"\t" + "\t" + "Plate type: " + plateType + "\n" +
					"\t" + "\t" + "Vehicle Registration: " + vehicleRegisteredText + "\n" +
					"\t" + "\t" + "Title owner name: " + titleOwnerName + "\n" +
					"\t" + "\t" + "Title owner address line 1: " + titleOwnerAddressLine1 + "\n" +
					"\t" + "\t" + "Title owner address line 2: " + titleOwnerAddressLine2 + "\n" +
					"\t" + "\t" + "Registered owner name: " + registeredOwnerName + "\n" +
					"\t" + "\t" + "Registered owner address line 1: " + registeredOwnerAddressLine1 + "\n" +
					"\t" + "\t" + "Registered owner address line 2: " + registeredOwnerAddressLine2 + "\n" +
					"\t" + "\t" + "Leinholder name: " + leinholderName + "\n" +
					"\t" + "\t" + "Leinholder address line 1: " + leinholderAddressLine1 + "\n" +
					"\t" + "\t" + "Leinholder address line 2: " + leinholderAddressLine2 + "\n" +
					"\t" + "\t" + "Abbrev: " + stateAbbrevText + "\n" +
					"\t" + "\t" + "State: " + stateValueText + "\n";
		}

	}

	public class TitleOwner {
		private String titleName;
		private String titleOwner;
		private String titleAddress;
		private String titleCityStateZIP;
		private String titleFederalID;
		private String titleStateID;
		private String titleTaxExempt;

		public String getTitleName() {
			return titleName;
		}

		public String getTitleOwner() {
			return titleOwner;
		}

		public String getTitleAddress() { return titleAddress;}

		public String getTitleCityStateZIP() {
			return titleCityStateZIP;
		}

		public String getTitleFederalID() {
			return titleFederalID;
		}

		public String getTitlestateID() {
			return titleStateID;
		}

		public String getTitleTaxExempt() {
			return titleTaxExempt;
		}


		public void setTitleName(String titleName) {
			this.titleName = titleName;
		}

		public void setTitleOwner(String titleOwner) {
			this.titleOwner = titleOwner;
		}
		
		public void setTitleAddress(String titleAddress) {
			this.titleAddress = titleAddress;
		}

		public void settitleCityStateZIP(String titleCityStateZIP) {
			this.titleCityStateZIP = titleCityStateZIP;
		}

		public void setTitleFederalID(String titleFederalID) {
			this.titleFederalID = titleFederalID;
		}

		public void setTitleStateID(String titleStateID) {
			this.titleStateID = titleStateID;
		}

		public void setTitleTaxExempt(String titleTaxExempt) {
			this.titleTaxExempt = titleTaxExempt;
		}

		public String toString() {
			return  "\t" + "\t" + "Title Name: " + titleName + "\n"  +
					"\t" + "\t" + "Title Owner: " + titleOwner + "\n" +
					"\t" + "\t" + "Title Address: " + titleAddress + "\n" +
					"\t" + "\t" + "Title CityStateZIP: " + titleCityStateZIP + "\n" +
					"\t" + "\t" + "Title FederalID: " + titleFederalID + "\n" +
					"\t" + "\t" + "Title StateID: " + titleStateID + "\n" +
					"\t" + "\t" + "Tilte TaxExempt: " + titleTaxExempt + "\n" ;
		}

	}
	public class RegisteredOwner {
		private String registeredName;
		private String registeredOwner;
		private String registeredAddress;
		private String registeredCityStateZIP;
		private String registeredFederalID;
		private String registeredStateID;


		public String getRegisteredName() {
			return registeredName;
		}

		public String getRegisteredOwner() {
			return registeredOwner;
		}

		public String getRegisteredAddress() { return registeredAddress;}

		public String getRegisteredCityStateZIP() {
			return registeredCityStateZIP;
		}

		public String getRegisteredFederalID() {
			return registeredFederalID;
		}

		public String getRegisteredStateID() {
			return registeredStateID;
		}
		
		public void setRegisteredName(String registeredName) {
			this.registeredName = registeredName;
		}

		public void setRegisteredOwner(String registeredOwner) {
			this.registeredOwner = registeredOwner;
		}

		public void setRegisteredAddress(String registeredAddress) {
			this.registeredAddress = registeredAddress;
		}

		public void setregisteredCityStateZIP(String registeredCityStateZIP) {
			this.registeredCityStateZIP = registeredCityStateZIP;
		}

		public void setRegisteredFederalID(String registeredFederalID) {
			this.registeredFederalID = registeredFederalID;
		}
		
		public void setRegisteredStateID(String registeredStateID) {
			this.registeredStateID = registeredStateID;
		}

		public String toString() {
			return "\t" + "\t" + "Registered Name: " + registeredName + "\n" +
					"\t" + "\t" + "Registered Owner: " + registeredOwner + "\n" +
					"\t" + "\t" + "Registered Address: " + registeredAddress + "\n" +
					"\t" + "\t" + "Registered CityStateZIP: " + registeredCityStateZIP + "\n" +
					"\t" + "\t" + "Registered FederalID: " + registeredFederalID + "\n" +
					"\t" + "\t" + "Registered StateID: " + registeredStateID + "\n" ;

		}

		}
	public class LienHolder {
		private String lienHolderName;
		private String lienHolderAddress;
		private String lienHolderCityStateZIP;
		private String lienHolderFederalID;
		private String lienHolderStateID;


		public String getLienHolderName() {
			return lienHolderName;
		}

		public String getLienHolderAddress() { return lienHolderAddress;}

		public String getLienHolderCityStateZIP() {
			return lienHolderCityStateZIP;
		}

		public String getLienHolderFederalID() {
			return lienHolderFederalID;
		}
		
		public String getLienHolderStateID() {
			return lienHolderStateID;
		}

		public void setLienHolderName(String lienHolderName) {
			this.lienHolderName = lienHolderName;
		}

		public void setLienHolderAddress(String lienHolderAddress) {
			this.lienHolderAddress = lienHolderAddress;
		}

		public void setlienHolderCityStateZIP(String lienHolderCityStateZIP) {
			this.lienHolderCityStateZIP = lienHolderCityStateZIP;
		}

		public void setLienHolderFederalID(String lienHolderFederalID) {
			this.lienHolderFederalID = lienHolderFederalID;
		}
		
		public void setLienHolderStateID(String lienHolderStateID) {
			this.lienHolderStateID = lienHolderStateID;
		}


		public String toString() {
			return  "\t" + "\t" + "LienHolder Name: " + lienHolderName + "\n"  +
					"\t" + "\t" + "LienHolder Address: " + lienHolderAddress + "\n" +
					"\t" + "\t" + "LienHolder CityStateZIP: " + lienHolderCityStateZIP + "\n" +
					"\t" + "\t" + "LienHolder FederalID: " + lienHolderFederalID + "\n" +
					"\t" + "\t" + "LienHolder StateID: " + lienHolderStateID + "\n"
			 ;

		}

	}

}



