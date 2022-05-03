package com.element.fleet.ordering.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OrderingRestUtility implements OrderingRestObjects {
	
	public static String userName;
	public static String password;
	public static String appCookie;
	public static String authToken;
	public static String APPLICATION_BASE_URL;
	public static RequestSpecification requestSpecification;
	public static ResponseSpecification responseSpecification;
	public static Response getWebUserRes;
	public static Response clientInfoResponse;
	public static Response startHerePageResponse;
	public static Response driverPageResponse;
	public static Response driverInfoResponse;
	public static Response searchedDriverResponse;
	public static Response vehiclePageResponse;
	public static Response billingPageResonse;
	public static Response dealerPageResponse;
	public static Response dealerInfoResponse;
	public static Response submitOrderResponse;
	public static FileInputStream fileInput;
	public static int orderId;
	public static String driverEmployeeID;
	public static LinkedHashMap<String, Integer> columnMap;
	public static Iterator<Row> rowIterator;
	public static XSSFSheet sheet;
	
	public static void init() throws Exception {
		userName = CommonPage.getTestData("Username");
		password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData(userName));
		APPLICATION_BASE_URL = CommonPage.getTestData("ApplicationURL").replace("/login", "");
		appCookie = OrderingRestAPI.getCookieFromOrderingFO(userName, password);
		authToken = OrderingRestAPI.getOauthTokenFromOrderingFO(appCookie);
		if(columnMap == null) {
			loadRestAssuredData();
		}
		CommonPage.initializeElementOrderObject();
	}
	
	public static RequestSpecification getRequestSpecBuilder() {
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(APPLICATION_BASE_URL)
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", authToken)
				.build();
		return requestSpecification;
	}
	
	public static ResponseSpecification getResponseSpecBuilder() {
		responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(HttpStatus.SC_OK)
				.build();	
		return responseSpecification;
	}
	
	public static void loadRestAssuredData() {
		columnMap = new LinkedHashMap<>();
		try {
			fileInput = new FileInputStream(
						System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
						+ "resources" + File.separator + "TestData" + File.separator + "OrderingRestAssuredData.xlsx");
			XSSFWorkbook excelBook = new XSSFWorkbook(fileInput);
			sheet = excelBook.getSheet("RestAPIData");
			XSSFRow columnRow = sheet.getRow(0);
			Iterator<Cell> cellIterator = columnRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				columnMap.put(cell.getStringCellValue(), cell.getColumnIndex());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getRestData(OrderingRestOperations operationName, String columnName) {
		rowIterator = sheet.rowIterator();
		Cell value = null;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String result = row.getCell(0).getRichStringCellValue().getString().trim();
			if (result.equals(operationName.toString())) {
				value = row.getCell(columnMap.get(columnName));
				break;
			}
		}
		return value.toString();
	}
	
	public static String getStartHerePagePayloadBody() throws JSONException {
		JSONObject responseBody = new JSONObject(getRestData(OrderingRestOperations.START_HERE_PAGE, "Request Payload"));
		JSONObject orderObject = responseBody.getJSONObject(CommonPage.getTestData("OrderType"));
		orderObject.getJSONObject("coreOrder").getJSONObject("client")
		.getJSONObject("corpClient").put("clientName", CommonPage.getTestData("ClientName"));
		return orderObject.toString();
	}
	
	public static String getSearchDriverPayloadBody() throws JSONException {
		JSONObject searchObject = new JSONObject(getRestData(OrderingRestOperations.DRIVER_SEARCH, "Request Payload"));
		System.out.println("Driver Id : " + driverEmployeeID);
		searchObject.put("query", driverEmployeeID);
		return searchObject.toString();
	}
	
	public static String getDriverPagePayloadBody() throws Exception {
		JSONObject driverObject = new JSONObject(getRestData(OrderingRestOperations.DRIVER_PAGE, "Request Payload"));
		orderId = startHerePageResponse.jsonPath().get("orderId");
		System.out.println("Order Id : " + orderId);
		driverObject.put("orderId", orderId);
		JSONObject addressObj = new JSONObject(driverInfoResponse.asString());
		JSONObject addInfo = driverObject.getJSONObject("driver").getJSONObject("additionalInformation");
		addInfo.put("spinOrgId", searchedDriverResponse.path("response.docs[0].psn_spin_org_id").toString());
		JSONObject garageAddress = driverObject.getJSONObject("garagingAddress");
		garageAddress.put("contactAddress", addressObj.getJSONObject("contactAddress"));
		driverObject.put("driverCustomData", new JSONObject("{}"));
		return driverObject.toString();
	}
	
	public static String getVehiclePagePayloadBody() throws Exception {
		JSONObject vehicleObject = new JSONObject(getRestData(OrderingRestOperations.VEHICLE_PAGE, "Request Payload"));
		vehicleObject.put("orderId", orderId);
		return vehicleObject.toString();
	}
	
	public static String getBillingPagePayloadBody() throws Exception {
		JSONObject billingObject = new JSONObject(getRestData(OrderingRestOperations.BILLING_PAGE, "Request Payload"));
		billingObject.put("orderId", orderId);
		return billingObject.toString();
	}
	
	public static String getDealerPagePayloadBody() throws Exception {
		JSONObject dealerObject = new JSONObject(getRestData(OrderingRestOperations.DEALER_PAGE, "Request Payload"));
		dealerObject.put("orderId", orderId);
		JSONObject dealerInfo = dealerObject.getJSONObject("deliveringDealerInfo");
		dealerInfo.put("internalDealerCode", dealerInfoResponse.path("response.docs[0].mfg_dlr_no").toString());
		JSONObject supplierAddress = dealerObject.getJSONObject("deliveringDealerInfo").getJSONObject("supplierAddress");
		supplierAddress.put("addressLine1", dealerInfoResponse.path("response.docs[0].addr_line1").toString());
		supplierAddress.put("county", dealerInfoResponse.path("response.docs[0].cnty_nm").toString());
		supplierAddress.put("city", dealerInfoResponse.path("response.docs[0].city_nm").toString());
		supplierAddress.put("postalCode", dealerInfoResponse.path("response.docs[0].supplier_postcode").toString());
		dealerInfo.put("supplierAddress", supplierAddress);
		dealerInfo.put("supplierName", dealerInfoResponse.path("response.docs[0].supplier_legal_nm").toString());
		dealerObject.put("deliveringDealerInfo", dealerInfo);
		return dealerObject.toString();
	}
	
	public static String getSubmitOrderPayloadBody() throws Exception {
		JSONObject completeOrder = new JSONObject(dealerPageResponse.asString());
		JSONObject submitOrder = completeOrder.getJSONObject("data");
		if("Stock".equalsIgnoreCase(CommonPage.getTestData("OrderType"))) {
			submitOrder.remove("customerApprovedPrice");
			submitOrder.put("event", "SUBMIT_ORDER_EVENT");
		} else {
			submitOrder.put("event", "VALIDATE_MANAGER_APPROVAL_EVENT");
		}
		return submitOrder.toString();
	}
	
	public static void saveLogNumber() throws Exception {
		JSONObject orderResponse = new JSONObject(submitOrderResponse.asString());
		String logNumber = orderResponse.getJSONObject("data").get("logNumber").toString();
		CommonPage.getElementOrderObject().setLogNumber(logNumber);
	}
	
	public static void createOrder() throws Exception {
		init();
		OrderingRestAPI.startHerePageFromAPI();
		OrderingRestAPI.selectDriverFromAPI();
		OrderingRestAPI.selectVehicleFromAPI();
		OrderingRestAPI.selectBillingAndRegstrationFromAPI();
		OrderingRestAPI.selectDealerFromAPI();
		OrderingRestAPI.submitOrder();
	}
}
