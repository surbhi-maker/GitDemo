package com.element.fleet.ordering.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.Assert;
import com.element.fleet.f3270.BaseMainframeDriver;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;

public class OrderingMFStatusValidation extends BaseMainframeDriver {

	/**
	 * This method verifies the target, bridge_action, status column data of bridgingTable of Ordering DB for add order..
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void addBridgingTableDataValidation() throws Exception {
		Map<String, ArrayList<String>> output = DbConnector.getBridgingOutput(CommonPage.getElementOrderObject().getLogNumber());
		List<String> bridgeActionExpectedColumnData = Arrays.asList(CommonPage.getTestData("Bridge_action").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> targetExpectedColumnData = Arrays.asList(CommonPage.getTestData("Target").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> statusExpectedColumnData = Arrays.asList(CommonPage.getTestData("Status").split("\\|")).stream().sorted().collect(Collectors.toList());
		Assert.assertEquals(output.get("target").stream().sorted().collect(Collectors.toList()),targetExpectedColumnData,"target column data dosen't match with the expected target column data");
		Assert.assertEquals(output.get("bridge_action").stream().sorted().collect(Collectors.toList()), bridgeActionExpectedColumnData, "bridge_action column data dosen't match with the expected bridge_action column data");
		Assert.assertEquals(output.get("status").stream().sorted().collect(Collectors.toList()),statusExpectedColumnData, "status column data dosen't match with the expected status column data");
	}
	
	/**
	 * This method verifies the target, bridge_action, status column data of bridgingTable of Ordering DB for add order..
	 * @lastModifiedBy mkaricharla
	 * @throws Exception
	 */
	public static void cancelOrderBridgingTableDataValidation() throws Exception {
		Map<String, ArrayList<String>> output = DbConnector.getBridgingOutput(CommonPage.getTestData("LogNumber"));
		List<String> bridgeActionExpectedColumnData = Arrays.asList(CommonPage.getTestData("Bridge_action Cancel").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> targetExpectedColumnData = Arrays.asList(CommonPage.getTestData("Target Cancel").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> statusExpectedColumnData = Arrays.asList(CommonPage.getTestData("Status Cancel").split("\\|")).stream().sorted().collect(Collectors.toList());
		Assert.assertEquals(output.get("target").stream().sorted().collect(Collectors.toList()), targetExpectedColumnData,"target column data dosen't match with the expected target column data");
		Assert.assertEquals(output.get("bridge_action").stream().sorted().collect(Collectors.toList()), bridgeActionExpectedColumnData, "bridge_action column data dosen't match with the expected bridge_action column data");
		Assert.assertEquals(output.get("status").stream().sorted().collect(Collectors.toList()), statusExpectedColumnData, "status column data dosen't match with the expected status column data");
	}

	/**
	 * This method verifies the target, bridge_action, status column data of bridgingTable of Ordering DB f or remove Used Unit
	 * @lastModifiedBy sbhosale
	 * @throws Exception
	 */
	public static void removeUsedUnitOrderBridgingTableDataValidation() throws Exception {
		Map<String, ArrayList<String>> output = DbConnector.getBridgingOutput(CommonPage.getTestData("LogNumber"));
		List<String> bridgeActionExpectedColumnData = Arrays.asList(CommonPage.getTestData("Bridge_action Remove").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> targetExpectedColumnData = Arrays.asList(CommonPage.getTestData("Target Remove").split("\\|")).stream().sorted().collect(Collectors.toList());
		List<String> statusExpectedColumnData = Arrays.asList(CommonPage.getTestData("Status Remove").split("\\|")).stream().sorted().collect(Collectors.toList());
		Assert.assertEquals(output.get("target").stream().sorted().collect(Collectors.toList()), targetExpectedColumnData,"target column data dosen't match with the expected target column data");
		Assert.assertEquals(output.get("bridge_action").stream().sorted().collect(Collectors.toList()), bridgeActionExpectedColumnData, "bridge_action column data dosen't match with the expected bridge_action column data");
		Assert.assertEquals(output.get("status").stream().sorted().collect(Collectors.toList()), statusExpectedColumnData, "status column data dosen't match with the expected status column data");
	}
	
}
