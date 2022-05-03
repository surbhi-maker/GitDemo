package com.element.fleet.ordering.page;

import com.element.fleet.ordering.enums.OrderingBOOrderMaintenancePageEnum;
import com.ge.capital.rainbow.browser.BrowserWait;

public class OrderingBOOrderMaintenancePage {
	
	public static void orderMaintenancePageLoaded() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrderMaintenancePageEnum.ORDERING_BO_OM_TAB_CONTAINER_CLASS);
	}
	
}
