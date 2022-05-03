package com.element.fleet.ordering.enums;

public enum OrderingBOTransportersPageEnum {	
	
	Ordering_BO_TP_TransportersTxt_Expected("Transporters"),
	Ordering_BO_TP_TransporterMantienanceTxt_Expected("Transporter Maintenance"),
	;
	
	private String value;

	private OrderingBOTransportersPageEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	
}
