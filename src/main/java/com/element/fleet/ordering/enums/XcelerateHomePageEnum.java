package com.element.fleet.ordering.enums;

public enum XcelerateHomePageEnum {
	
	XCELERATE_LOGIN_USERNAME_TEXTFIELD_ID("username"),
	XCELERATE_LOGIN_PASSWORD_TEXTFIELD_ID("password"),
	XCELERATE_LOGIN_BUTTON_ID("signinBtn"),
	XCELERATE_HOME_PAGE_WIDGETS_SPINNER_CSS("div#mainContent div.loadingSpinnerWrapper"),
	XCELERATE_HOME_PAGE_WIDGETS_CSS("div.homepage.widget-container > div.home-widget"),
	XCELERATE_HOME_PAGE_NAVBAR_CSS("ul.nav.navbar-nav.lhsNavingate"),
	XCELERATE_HOME_PAGE_VEHICLES_ICON_CSS("a.vehicleIcon"),
	XCELERATE_HOME_PAGE_VEHICLES_SIDE_MENU_CSS("a.vehicleIcon"),
	XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_CSS("div#headingSeven > a[data-parent='#accordionVehicle']"),
	XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_FO_LINK_CSS("a[href='/web/common/externalLogin?refererUrl=https://ordering.qa.elementfleet.com']"),
	XCELERATE_HOME_PAGE_ORDING_VEHICLES_SIDE_MENU_OPTION_BO_LINK_CSS("a[href='javascript:statusCheckOrderVehicle();']"),
	XCELERATE_HOME_PAGE_SETTINGS_BUTTON_CSS("a.btnPopover.iconClickSlider.settingsIcon"),
	XCELERATE_HOME_PAGE_SIGNOUT_BUTTON_ID("header_logout"),
	XCELERATE_HOME_PAGE_TAKE_A_TOUR_CLOSE_ICON_XPATH("//a[@class='take-a-tour-close']"),
	;
	
	private String value;

	private XcelerateHomePageEnum(String value) {
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
