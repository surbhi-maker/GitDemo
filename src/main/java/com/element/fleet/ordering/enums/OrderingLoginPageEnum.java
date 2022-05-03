package com.element.fleet.ordering.enums;

public enum OrderingLoginPageEnum {

	ORDEING_LOGIN_USERNAME_TEXTFIELD_ID("loginUserId"), ORDEING_LOGIN_PASSWORD_TEXTFIELD_ID("loginPasswordId"),
	ORDEING_LOGIN_LOGIN_BUTTON_ID("login"),
	ORDEING_LOGIN_USERNAME_BO_TEXTFIELD_ID("backofficeUserId"),ORDEING_LOGIN_PASSWORD_BO_TEXTFIELD_ID("backofficePasswordId"),
	;

	private String value;

	OrderingLoginPageEnum(String value) {
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

