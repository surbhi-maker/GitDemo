package com.element.fleet.ordering.verification;

import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;

public class StartHereTab {
	
	private String corpCode;
	private String clientName;
	private String newUnitNumber;
	private boolean autoAssignNewUnitNumber;
	private OrderTypeSection orderTypeSectionObject;
	private UsedUnitInformationSection usedUnitInformationSectionObject;

	public OrderTypeSection getOrderTypeSectionObject() {		
		if(orderTypeSectionObject == null) {
			this.orderTypeSectionObject = new OrderTypeSection();
		}		
		return this.orderTypeSectionObject;
	}
	
	public UsedUnitInformationSection getUsedUnitInformationSectionObject() {		
		if(usedUnitInformationSectionObject == null) {
			this.usedUnitInformationSectionObject = new UsedUnitInformationSection();
		}		
		return this.usedUnitInformationSectionObject;
	}
	
	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getNewUnitNumber() {
		return newUnitNumber;
	}

	public void setNewUnitNumber(String newUnitNumber) {
		this.newUnitNumber = newUnitNumber;
	}

	public boolean isAutoAssignNewUnitNumber() {
		return autoAssignNewUnitNumber;
	}

	public void setAutoAssignNewUnitNumber(boolean autoAssignNewUnitNumber) {
		this.autoAssignNewUnitNumber = autoAssignNewUnitNumber;
	}

	public String toString() {
		return "\t"+"Corp code: "+corpCode+"\n"+
				"\t"+"Client name: "+clientName+"\n"+
				"\t"+"New unit number: "+newUnitNumber+"\n"+
				"\t"+"Auto assign new unit number: "+autoAssignNewUnitNumber+"\n"+
				"\t"+"Order type: "+
				((orderTypeSectionObject==null)?(null+"\n"):("\n"+orderTypeSectionObject.toString()))+
				"\t"+"Used unit information: "+
				((usedUnitInformationSectionObject==null)?(null+"\n"):("\n"+usedUnitInformationSectionObject.toString()));
	}

	public class OrderTypeSection implements OrderTypeInterface{
		private FactoryOrder factoryOrderObject;
		private DealerOrder dealerOrderObject;
		private StockOrder stockOrderObject;
		private String orderType;

		public FactoryOrder getOrderTypeAsFactoryOrder() {
			if(factoryOrderObject == null) {
				this.factoryOrderObject = new FactoryOrder();
				orderType = "Factory Order";
			}
			dealerOrderObject = null;
			stockOrderObject = null;
			return this.factoryOrderObject;
		}

		public DealerOrder getOrderTypeAsDealerOrder() {
			if(dealerOrderObject == null) {
				this.dealerOrderObject = new DealerOrder();
				orderType = "Dealer Order";
			}
			factoryOrderObject = null;
			stockOrderObject = null;
			return this.dealerOrderObject;
		}

		public StockOrder getOrderTypeAsStockOrder() {
			if(stockOrderObject == null) {
				this.stockOrderObject = new StockOrder();
				orderType = "Stock Order";
			}
			factoryOrderObject = null;
			dealerOrderObject = null;
			return this.stockOrderObject;
		}

		public String getOrderType() {
			return orderType;
		}

		public String toString() {
			return "\t"+"\t"+"Factory Order: "+
					((factoryOrderObject==null)?(null+"\n"):("\n"+factoryOrderObject))+
					"\t"+"\t"+"Dealer Order: "+
					((dealerOrderObject==null)?(null+"\n"):("\n"+dealerOrderObject))+
					"\t"+"\t"+"Stock Order: "+
					((stockOrderObject==null)?(null+"\n"):("\n"+stockOrderObject));
		}
	}

	private interface OrderTypeInterface{

		class FactoryOrder{	

			private boolean isPoolOrder;
			private String poolType;

			private void setIsPoolOrder(boolean isPoolOrder) {
				this.isPoolOrder = isPoolOrder;
			}

			public boolean checkIsPoolOrder() {
				return isPoolOrder;
			}

			public boolean setPoolType(String poolType){
				setIsPoolOrder(true);
				switch(poolType) {
				case "Bailment":
					this.poolType = "Bailment";
					break;
				case "OEM":
					this.poolType = "OEM";
					break;
				case "Vendor":
					this.poolType = "Vendor";
					break;
				default:
					throw new  InvalidSwitchCaseException(poolType+" is a invalid Pool Type");
				}
				return true;
			}

			public String getPoolType() {
				return poolType;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"Is Pool Order: "+isPoolOrder+"\n"+
						"\t"+"\t"+"\t"+"Pool type: "+poolType+"\n";
			}

		}

		class DealerOrder{

			private boolean isPoolOrder;
			private String poolType;
			private ClientContactInformation clientContactInformationObject;
			private DealerContactInformation dealerContactInformationObject;
			
			private void setIsPoolOrder(boolean isPoolOrder) {
				this.isPoolOrder = isPoolOrder;
			}

			public boolean checkIsPoolOrder() {
				return isPoolOrder;
			}

			public boolean setPoolType(String poolType){
				setIsPoolOrder(true);
				switch(poolType) {
				case "Bailment":
					this.poolType = "Bailment";
					break;
				case "OEM":
					this.poolType = "OEM";
					break;
				case "Vendor":
					this.poolType = "Vendor";
					break;
				default:
					throw new  InvalidSwitchCaseException(poolType+" is a invalid Pool Type");
				}
				return true;
			}

			public String getPoolType() {
				return poolType;
			}

			public ClientContactInformation getClientContactInformationObject() {		
				if(clientContactInformationObject == null) {
					this.clientContactInformationObject = new ClientContactInformation();
				}		
				return this.clientContactInformationObject;
			}
			
			public DealerContactInformation getDealerContactInformationObject() {		
				if(dealerContactInformationObject == null) {
					this.dealerContactInformationObject = new DealerContactInformation();
				}		
				return this.dealerContactInformationObject;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"Is Pool Order: "+isPoolOrder+"\n"+
						"\t"+"\t"+"\t"+"Pool type: "+poolType+"\n"+
						"\t"+"\t"+"\t"+"Client contact information: "+
						((this.clientContactInformationObject==null)?(null+"\n"):("\n"+this.clientContactInformationObject.toString()))+
						"\t"+"\t"+"\t"+"Dealer contact information: "+
						((this.dealerContactInformationObject==null)?(null+"\n"):("\n"+this.dealerContactInformationObject.toString()));
			}
		}

		class StockOrder{	

			private String whoWillBeLocatingVehicle;
			private ClientContactInformation clientContactInformationObject;
			private DealerContactInformation dealerContactInformationObject;

			public boolean setWhoWillBeLocatingVehicleTo(String whoWillBeLocatingVehicle){
				switch(whoWillBeLocatingVehicle) {
				case "Client":
					this.whoWillBeLocatingVehicle = "Client";
					break;
				case "Element":
					this.whoWillBeLocatingVehicle = "Element";
					break;
				default:
					throw new  InvalidSwitchCaseException(whoWillBeLocatingVehicle+" is a invalid Pool Type");
				}
				return true;
			}

			public String getPoolType() {
				return whoWillBeLocatingVehicle;
			}

			public ClientContactInformation getClientContactInformationObject() {		
				if(clientContactInformationObject == null) {
					this.clientContactInformationObject = new ClientContactInformation();
				}		
				return this.clientContactInformationObject;
			}
			
			public DealerContactInformation getDealerContactInformationObject() {		
				if(dealerContactInformationObject == null) {
					this.dealerContactInformationObject = new DealerContactInformation();
				}		
				return this.dealerContactInformationObject;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"Who will be locating vehicle: "+whoWillBeLocatingVehicle+"\n"+
						"\t"+"\t"+"\t"+"Client contact information: "+
						((this.clientContactInformationObject==null)?(null+"\n"):("\n"+this.clientContactInformationObject.toString()))+
						"\t"+"\t"+"\t"+"Dealer contact information: "+
						((this.dealerContactInformationObject==null)?(null+"\n"):("\n"+this.dealerContactInformationObject.toString()));
			}

		}

		class ClientContactInformation{

			private String firstName;
			private String lastName;
			private String phone;
			private String phoneExt;
			private String email;

			public String getFirstName() {
				return firstName;
			}

			public void setFirstName(String firstName) {
				this.firstName = firstName;
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

			public String getPhoneExt() {
				return phoneExt;
			}

			public void setPhoneExt(String phoneExt) {
				this.phoneExt = phoneExt;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"\t"+"First Name: "+firstName+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Last Name: "+lastName+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Phone: "+phone+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Phone Ext: "+phoneExt+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Email: "+email+"\n";
			}

		}
		
		class DealerContactInformation{

			private String dealerShipName;
			private String firstName;
			private String lastName;
			private String phone;
			private String phoneExt;
			private String email;

			public String getDealerShipName() {
				return dealerShipName;
			}

			public void setDealerShipName(String dealerShipName) {
				this.dealerShipName = dealerShipName;
			}
			
			public String getFirstName() {
				return firstName;
			}

			public void setFirstName(String firstName) {
				this.firstName = firstName;
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

			public String getPhoneExt() {
				return phoneExt;
			}

			public void setPhoneExt(String phoneExt) {
				this.phoneExt = phoneExt;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"\t"+"DealerShip Name: "+dealerShipName+"\n"+
						"\t"+"\t"+"\t"+"\t"+"First Name: "+firstName+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Last Name: "+lastName+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Phone: "+phone+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Phone Ext: "+phoneExt+"\n"+
						"\t"+"\t"+"\t"+"\t"+"Email: "+email+"\n";
			}
		}

	}
	
	public class UsedUnitInformationSection {

		private boolean addUsedUnit;
		private String fleetName;
		private String unitNumber;
		private String year;
		private String make;
		private String model;
		private String trim;
		private String vin;
		private String whoToSellUsedUnit;
		private String competitorList;
		private boolean copyDriveAndBillingDataToNewUnit;

		public boolean isAddUsedUnit() {
			return addUsedUnit;
		}

		public void setAddUsedUnit(boolean addUsedUnit) {
			this.addUsedUnit = addUsedUnit;
		}

		public String getFleetName() {
			return fleetName;
		}

		public void setFleetName(String fleetName) {
			this.fleetName = fleetName;
		}

		public String getUnitNumber() {
			return unitNumber;
		}

		public void setUnitNumber(String unitNumber) {
			this.unitNumber = unitNumber;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getTrim() {
			return trim;
		}

		public void setTrim(String trim) {
			this.trim = trim;
		}

		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}

		public String getWhoToSellUsedUnit() {
			return whoToSellUsedUnit;
		}

		public void setWhoToSellUsedUnit(String whoToSellUsedUnit) {
			switch(whoToSellUsedUnit) {
			case "Element":
				this.whoToSellUsedUnit = "Element";
				break;
			case "Notify Competitor":
				this.whoToSellUsedUnit = "Notify Competitor";
				break;
			case "Reference Only":
				this.whoToSellUsedUnit = "Reference Only";
				break;
			default:
				throw new  InvalidSwitchCaseException(whoToSellUsedUnit+" is a invalid who to sell used unit");
			}
		}

		public String getCompetitorList() {
			return competitorList;
		}

		public void setCompetitorList(String competitorList) {
			switch(competitorList) {
			case "0":
				this.competitorList = "";
				break;
			case "ARI":
				this.competitorList = "ARI";
				break;
			case "BBL Fleet Services":
				this.competitorList = "BBL Fleet Services";
				break;
			case "DONLEN":
				this.competitorList = "DONLEN";
				break;
			case "EMKAY":
				this.competitorList = "EMKAY";
				break;
			case "ENTERPRISE":
				this.competitorList = "ENTERPRISE";
				break;
			case "LEASEPLAN":
				this.competitorList = "LEASEPLAN";
				break;
			case "MIKE ALBERT LEASING":
				this.competitorList = "MIKE ALBERT LEASING";
				break;
			case "MRC RAIL":
				this.competitorList = "MRC RAIL";
				break;
			case "MERCHANTS":
				this.competitorList = "MERCHANTS";
				break;
			case "WHEELS":
				this.competitorList = "WHEELS";
				break;
			default:
				throw new  InvalidSwitchCaseException(competitorList+" is a invalid competitor list");
			}
		}

		public boolean isCopyDriveAndBillingDataToNewUnit() {
			return copyDriveAndBillingDataToNewUnit;
		}

		public void setCopyDriveAndBillingDataToNewUnit(boolean copyDriveAndBillingDataToNewUnit) {
			this.copyDriveAndBillingDataToNewUnit = copyDriveAndBillingDataToNewUnit;
		}
		
		public String toString() {
			return "\t"+"\t"+"Add used unit: "+addUsedUnit+"\n"+
					"\t"+"\t"+"Fleet name: "+fleetName+"\n"+
					"\t"+"\t"+"Unit number: "+unitNumber+"\n"+
					"\t"+"\t"+"Year: "+year+"\n"+
					"\t"+"\t"+"Make: "+make+"\n"+
					"\t"+"\t"+"Model: "+model+"\n"+
					"\t"+"\t"+"Trim: "+trim+"\n"+
					"\t"+"\t"+"VIN: "+vin+"\n"+
					"\t"+"\t"+"Who to sell used unit: "+whoToSellUsedUnit+"\n"+
					"\t"+"\t"+"Competitior list: "+competitorList+"\n"+
					"\t"+"\t"+"Copy drive and billing data to new unit: "+copyDriveAndBillingDataToNewUnit+"\n";
		}

	}
}
