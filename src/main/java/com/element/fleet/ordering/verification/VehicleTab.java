package com.element.fleet.ordering.verification;

import java.util.ArrayList;
import java.util.List;

public class VehicleTab {

	private String fleetSpecStatus;
	private String clientNumber;
	private String fleetSpecID;
	private String revisionNumber;
	private String fleetSpecName;
	private String year;
	private String make;
	private String model;
	private String trim;
	private String modelCode;
	private String fleetSpecDate;
	private String lastUpdatedBy;
	private String vin;
	private boolean retailonlyflag;
	private String driverpayprice;
	private VehicleInformationSection vehicleInformationSectionObject;
	private DealerInstalledOptionsSection dealerInstalledOptionsSectionObject;
	private UpfitInformationSection upfitInformationSectionObject;
	private String priceAndConfigSpecID;

	public VehicleInformationSection getVehicleInformationSectionObject() {		
		if(vehicleInformationSectionObject == null) {
			this.vehicleInformationSectionObject = new VehicleInformationSection();
		}		
		return this.vehicleInformationSectionObject;
	}

	public DealerInstalledOptionsSection getDealerInstalledOptionsSectionObject() {		
		if(dealerInstalledOptionsSectionObject == null) {
			this.dealerInstalledOptionsSectionObject = new DealerInstalledOptionsSection();
		}		
		return this.dealerInstalledOptionsSectionObject;
	}

	public UpfitInformationSection getUpfitInformationSectionObject() {		
		if(upfitInformationSectionObject == null) {
			this.upfitInformationSectionObject = new UpfitInformationSection();
		}		
		return this.upfitInformationSectionObject;
	}

	public String getFleetSpecStatus() {
		return fleetSpecStatus;
	}

	public void setFleetSpecStatus(String fleetSpecStatus) {
		this.fleetSpecStatus = fleetSpecStatus;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getFleetSpecID() {
		return fleetSpecID;
	}

	public void setFleetSpecID(String fleetSpecID) {
		this.fleetSpecID = fleetSpecID;
	}
	
	public String getPriceAndConfigSpecID() {
		return priceAndConfigSpecID;
	}

	public void setPriceAndConfigSpecID(String priceAndConfigSpecID) {
		this.priceAndConfigSpecID = priceAndConfigSpecID;
	}

	public String getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(String revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public String getFleetSpecName() {
		return fleetSpecName;
	}

	public void setFleetSpecName(String fleetSpecName) {
		this.fleetSpecName = fleetSpecName;
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

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getFleetSpecDate() {
		return fleetSpecDate;
	}

	public void setFleetSpecDate(String fleetSpecDate) {
		this.fleetSpecDate = fleetSpecDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	public String getVIN() {
		return vin;
	}

	public void setVIN(String vin) {
		this.vin = vin;
	}
	
	public boolean getretailonlyflag() {
		return retailonlyflag;
	}

	public void setretailonlyflag(boolean retailonlyflag) {
		this.retailonlyflag = retailonlyflag;
	}
	
	public String getdriverpayprice() {
		return driverpayprice;
	}
	
	public void setdriverpayprice(String driverpayprice) {
		this.driverpayprice = driverpayprice;
	}


	public String toString() {
		return "\t"+"Fleet spec status: "+fleetSpecStatus+"\n"+
				"\t"+"Client number: "+clientNumber+"\n"+
				"\t"+"Fleet spec id: "+fleetSpecID+"\n"+
				"\t"+"Revision number: "+revisionNumber+"\n"+
				"\t"+"Fleet spec name: "+fleetSpecName+"\n"+
				"\t"+"Year: "+year+"\n"+
				"\t"+"Make: "+make+"\n"+
				"\t"+"Model: "+model+"\n"+
				"\t"+"Trim: "+trim+"\n"+
				"\t"+"Model code: "+modelCode+"\n"+
				"\t"+"Fleet spec date: "+fleetSpecDate+"\n"+
				"\t"+"Last updated by: "+lastUpdatedBy+"\n"+
				"\t"+"VIN: "+vin+"\n"+
				"\t"+"Vehicle information: "+((vehicleInformationSectionObject==null)?(null+"\n"):("\n"+vehicleInformationSectionObject.toString()))+
				"\t"+"Dealer installed options: "+((dealerInstalledOptionsSectionObject==null)?(null+"\n"):("\n"+dealerInstalledOptionsSectionObject.toString()))+
				"\t"+"Upfit information: "+((upfitInformationSectionObject==null)?(null+"\n"):("\n"+upfitInformationSectionObject.toString()));
	}

	public class VehicleInformationSection {

		private List<FleetSpec> fleetSpecs = new ArrayList<>();

		public void addFleetSpec(String code, String description, String invoice, String msrp) {
			this.fleetSpecs.add(new FleetSpec(code, description, invoice, msrp));
		}		

		class FleetSpec {

			private String code;
			private String description;
			private String invoice;
			private String msrp;

			FleetSpec(String code, String description, String invoice, String msrp){
				this.code = code;
				this.description = description;
				this.invoice = invoice;
				this.msrp = msrp;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"[Code: "+code+", Description: "+description+", Invoice: "+invoice+", MSRP: "+msrp+"]\n";
			}

		}

		private String getAllFleetSpec() {
			StringBuilder sb = new StringBuilder();
			for(FleetSpec fleetSpec: fleetSpecs) {
				sb.append(fleetSpec);
			}
			return sb.toString();
		}

		public String toString() {
			return "\t"+"\t"+"Fleet Specs: "+((fleetSpecs.isEmpty())?(null+"\n"):("\n"+getAllFleetSpec()));
		}

	}

	public class DealerInstalledOptionsSection {

		private List<DealerInstalledOption> dealerInstalledOptions = new ArrayList<>();

		public void addDIO(String quantity, String optionCode, String description, String priceNotToExceed, boolean postProductionOption) {
			this.dealerInstalledOptions.add(new DealerInstalledOption(quantity, optionCode, description, priceNotToExceed, postProductionOption));
		}	

		class DealerInstalledOption {
			private String quantity;
			private String optionCode;
			private String description;
			private String priceNotToExceed;
			private boolean postProductionOption;

			DealerInstalledOption(String quantity, String optionCode, String description, String priceNotToExceed, boolean postProductionOption) {
				this.quantity = quantity;
				this.optionCode = optionCode;
				this.description = description;
				this.priceNotToExceed = priceNotToExceed;
				this.postProductionOption = postProductionOption;
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"[Quantity: "+quantity+", Option code: "+optionCode+", Descrption: "+description+", Price not to exceed: "+priceNotToExceed+", Post production option: "+postProductionOption+"]\n";
			}

		}

		private String getAllDealerInstalledOptions() {
			StringBuilder sb = new StringBuilder();
			for(DealerInstalledOption dealerInstalledOption: dealerInstalledOptions) {
				sb.append(dealerInstalledOption);
			}
			return sb.toString();
		}

		public String toString() {
			return "\t"+"\t"+"Dealer installed options: "+((dealerInstalledOptions.isEmpty())?(null+"\n"):("\n"+getAllDealerInstalledOptions()));
		}

	}

	public class UpfitInformationSection {
		
		private List<AvailableUpfitSpecification> availableUpfitSpecifications = new ArrayList<>();
		private List<UpfitPurchaseOrder> upfitPurchaseOrders = new ArrayList<>();		
		private AvailableUpfitSpecification availableUpfitSpecification;
		private UpfitPurchaseOrder upfitPurchaseOrder;

		public void addAvailableUpfitSpecification() {
			this.availableUpfitSpecifications.add(this.availableUpfitSpecification);
			this.availableUpfitSpecification = null;
		}
		
		public void addUpfitPurchaseOrder() {
			this.upfitPurchaseOrders.add(this.upfitPurchaseOrder);
			this.upfitPurchaseOrder = null;
		}
		
		public AvailableUpfitSpecification newAvailableUpfitSpecification() {
			if(this.availableUpfitSpecification == null) {
				this.availableUpfitSpecification = new AvailableUpfitSpecification();				
			}
			return this.availableUpfitSpecification;
		}
		
		public UpfitPurchaseOrder newUpfitPurchaseOrder() {
			if(this.upfitPurchaseOrder == null) {
				this.upfitPurchaseOrder = new UpfitPurchaseOrder();				
			}
			return this.upfitPurchaseOrder;
		}
		
		public AvailableUpfitSpecification getSpecificAvailableUpfitSpecifications(int postion) {
			return availableUpfitSpecifications.get(postion);
		}
		
		public String getAllAvailableUpfitSpecifications() {
			StringBuilder sb = new StringBuilder();
			for(AvailableUpfitSpecification availableUpfitSpecification: availableUpfitSpecifications) {
				sb.append(availableUpfitSpecification);
			}
			return sb.toString();
		}
		
		public UpfitPurchaseOrder getSpecificUpfitPurchaseOrders(int postion) {
			return upfitPurchaseOrders.get(postion);
		}
		
		public String getAllUpfitPurchaseOrders() {
			StringBuilder sb = new StringBuilder();
			for(UpfitPurchaseOrder upfitPurchaseOrder: upfitPurchaseOrders) {
				sb.append(upfitPurchaseOrder);
			}
			return sb.toString();
		}
		
		public String toString() {
			return "\t"+"\t"+"Available upfit specifications: "+((availableUpfitSpecifications.isEmpty())?(null+"\n"):("\n"+getAllAvailableUpfitSpecifications()))+
					"\t"+"\t"+"Upfit purchase orders: "+((upfitPurchaseOrders.isEmpty())?(null+"\n"):("\n"+getAllUpfitPurchaseOrders()));
		}

		public class AvailableUpfitSpecification {

			private String upfitSpecificationName;
			private String supplierId;
			private String supplierType;
			private String supplierName;
			private String city;
			private String state;
			private String zipcode;
			private String contactFirstName;
			private String contactLastName;
			private String contactPhone;
			private String contactEmail;
			private String supplierCode;
			private String code;
			private String category;
			private String description;
			private String extendedDescription;
			private String quantity;
			private String optionPrice;
			private String estimatedCost;
			
			public String getUpfitSpecificationName() {
				return upfitSpecificationName;
			}

			public void setUpfitSpecificationName(String upfitSpecificationName) {
				this.upfitSpecificationName = upfitSpecificationName;
			}
			
			public String getSupplierId() {
				return supplierId;
			}

			public void setSupplierId(String supplierId) {
				this.supplierId = supplierId;
			}

			public String getSupplierType() {
				return supplierType;
			}

			public void setSupplierType(String supplierType) {
				this.supplierType = supplierType;
			}

			public String getSupplierName() {
				return supplierName;
			}

			public void setSupplierName(String supplierName) {
				this.supplierName = supplierName;
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

			public String getContactFirstName() {
				return contactFirstName;
			}

			public void setContactFirstName(String contactFirstName) {
				this.contactFirstName = contactFirstName;
			}

			public String getContactLastName() {
				return contactLastName;
			}

			public void setContactLastName(String contactLastName) {
				this.contactLastName = contactLastName;
			}

			public String getContactPhone() {
				return contactPhone;
			}

			public void setContactPhone(String contactPhone) {
				this.contactPhone = contactPhone;
			}

			public String getContactEmail() {
				return contactEmail;
			}

			public void setContactEmail(String contactEmail) {
				this.contactEmail = contactEmail;
			}

			public String getSupplierCode() {
				return supplierCode;
			}

			public void setSupplierCode(String supplierCode) {
				this.supplierCode = supplierCode;
			}

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			public String getCategory() {
				return category;
			}

			public void setCategory(String category) {
				this.category = category;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getExtendedDescription() {
				return extendedDescription;
			}

			public void setExtendedDescription(String extendedDescription) {
				this.extendedDescription = extendedDescription;
			}

			public String getQuantity() {
				return quantity;
			}

			public void setQuantity(String quantity) {
				this.quantity = quantity;
			}

			public String getOptionPrice() {
				return optionPrice;
			}

			public void setOptionPrice(String optionPrice) {
				this.optionPrice = optionPrice;
			}

			public String getEstimatedCost() {
				return estimatedCost;
			}

			public void setEstimatedCost(String estimatedCost) {
				this.estimatedCost = estimatedCost;
			}
			
			public String toString() {
				return "\t"+"\t"+"\t"+"[Upfit specification name: "+upfitSpecificationName
						+", Supplier Id: "+supplierId
						+", Supplier type: "+supplierType
						+", Supplier name: "+supplierName
						+", City: "+city
						+", State: "+state
						+", Zipcode: "+zipcode
						+", Contact first name: "+contactFirstName
						+", Contact last name: "+contactLastName
						+", Contact phone: "+contactPhone
						+", Contact email: "+contactEmail
						+", Supplier code: "+supplierCode
						+", Code: "+code
						+", Category: "+category
						+", Description: "+description
						+", Extended description: "+extendedDescription
						+", Quantity: "+quantity
						+", Option price: "+optionPrice
						+", Estimated cost: "+estimatedCost+"]\n";
			}

		}

		public class UpfitPurchaseOrder {

			private String upfitterName;
			private String address;
			private String city;
			private String state;
			private String zipcode;
			private List<LineItem> lineItems = new ArrayList<>();
			private LineItem lineItem;
			
			public String getUpfitterName() {
				return upfitterName;
			}

			public void setUpfitterName(String upfitterName) {
				this.upfitterName = upfitterName;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
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
			
			public void addLineItem() {
				this.lineItems.add(this.lineItem);
				this.lineItem = null;
			}
			
			public LineItem newlineItem() {
				if(this.lineItem == null) {
					this.lineItem = new LineItem();				
				}
				return this.lineItem;
			}
			
			private String getAllLineItems() {
				StringBuilder sb = new StringBuilder();
				for(LineItem lineItem: lineItems) {
					sb.append(lineItem);
				}
				return sb.toString();
			}

			public String toString() {
				return "\t"+"\t"+"\t"+"[Upfitter name: "+upfitterName
						+", Address: "+address
						+", City: "+city
						+", State: "+state
						+", Zipcode: "+zipcode+"]\n"+
						"\t"+"\t"+"\t"+"\t"+"Line items: "+((lineItems.isEmpty())?(null+"\n"):("\n"+getAllLineItems()));
			}
			
			public class LineItem {
				
				private String code;
				private String category;
				private String description;
				private String extendedDescription;
				private String quantity;
				private String optionPrice;
				private String estimatedCost;			

				public String getCode() {
					return code;
				}

				public void setCode(String code) {
					this.code = code;
				}

				public String getCategory() {
					return category;
				}

				public void setCategory(String category) {
					this.category = category;
				}

				public String getDescription() {
					return description;
				}

				public void setDescription(String description) {
					this.description = description;
				}

				public String getExtendedDescription() {
					return extendedDescription;
				}

				public void setExtendedDescription(String extendedDescription) {
					this.extendedDescription = extendedDescription;
				}

				public String getQuantity() {
					return quantity;
				}

				public void setQuantity(String quantity) {
					this.quantity = quantity;
				}

				public String getOptionPrice() {
					return optionPrice;
				}

				public void setOptionPrice(String optionPrice) {
					this.optionPrice = optionPrice;
				}

				public String getEstimatedCost() {
					return estimatedCost;
				}

				public void setEstimatedCost(String estimatedCost) {
					this.estimatedCost = estimatedCost;
				}
				
				public String toString() {
					return "\t"+"\t"+"\t"+"\t"+"\t"+"[ Code: "+code
							+", Category: "+category
							+", Description: "+description
							+", Extended description: "+extendedDescription
							+", Quantity: "+quantity
							+", Option price: "+optionPrice
							+", Estimated cost: "+estimatedCost+"]\n";
				}
				
			}
			
		}

	}

}
