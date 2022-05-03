package com.element.fleet.ordering.rest;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;

public class OrderingRestAPI extends OrderingRestUtility {
	
	/**
	 * This gets the Application Cookie 
	 * @return Cookie
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static String getCookieFromOrderingFO(String username, String password) {
		Response loginRes = given()
				.baseUri(CommonPage.getTestData("ApplicationURL").replace("/login", ""))
				.contentType(ContentType.URLENC)
				.body("username="+ username +"&password="+ password)
				.when()
				.post(OrderingRestObjects.RESOURCE_COOKIE)
				.then().assertThat().statusCode(HttpStatus.SC_MOVED_TEMPORARILY)
				.extract().response();
		Assert.assertEquals(loginRes.getHeader("Location"), CommonPage.getTestData("ApplicationURL").replace("login", "").replaceAll("https", "http"), "Redirect URL not matched");
		List<String> cookieList = loginRes.getHeaders().getValues("Set-Cookie");
		System.out.println(cookieList);
		appCookie = cookieList.get(1).split(";")[0]+";"+cookieList.get(2).split(";")[0]+";"+cookieList.get(3).split(";")[0];
		System.out.println("Cookie: " + appCookie);
		return appCookie;
	}
	
	/**
	 * This Method gets the Authentication Token 
	 * @return authToken
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static String getOauthTokenFromOrderingFO(String appCookie) {
		getWebUserRes = given()
				.baseUri(CommonPage.getTestData("ApplicationURL").replace("/login", ""))
				.header("Cookie", appCookie)
				.when()
				.get(OrderingRestObjects.RESOURCE_AUTH_TOKEN).then()
				.spec(getResponseSpecBuilder())
				.extract().response();
		authToken = getWebUserRes.jsonPath().getString("oauthToken");
		System.out.println("OAuth token from API: " + authToken.trim());
		return authToken.trim();
	}
	
	public static Response getDealerFromOrderingFO(boolean activeDealer, boolean useState, String corpCode, String driverStateAbbrevation, String make) throws Exception {
		String country = (corpCode.equals("FA"))?"US":"CA";
		String makeCode = DbConnector.getMakeCode(make);
		//As in the API it always finds filters dealer with active status this value is hard coded.
		String dealerStatus = activeDealer?"A":"A";
		String useStateValue = useState?(",\"st_prov_abbr\":\""+ driverStateAbbrevation +"\""):("");
		String jsonBody = "{"
				+ "\"query\":\"*\","
				+ "\"rows\":100,"
				+ "\"filter\":{\"iso_cntry_cd\":\""+ country +"\","
				+ "\"make_cd\":"+ makeCode +","
				+ "\"prod_class_cd\":\"LT\","
				+ "\"profile_stat_cd\":\""+ dealerStatus +"\""+ useStateValue +"}"
				+ "}";
		System.out.println("POST: "+ APPLICATION_BASE_URL + OrderingRestObjects.RESOURCE_SEARCH_DEALER);
		System.out.println("Header: Content Type : "+ ContentType.JSON);
		System.out.println("Header: Authorization: " + authToken);
		System.out.println("Body: "+ jsonBody);
		Response dealerName = given()
				.spec(getRequestSpecBuilder())
				.body(jsonBody)
				.post(OrderingRestObjects.RESOURCE_SEARCH_DEALER).then()
				.spec(getResponseSpecBuilder())
				.extract().response();
		System.out.println("Dealer Name from API: " + dealerName.path("response.docs[0].supplier_legal_nm"));
		return dealerName;
	}
	
	public static void deleteFeatureToggle(String featureId) throws Exception {	
		String jsonBody = "{\"featureId\": "+ featureId +"}";
		System.out.println("POST: "+ APPLICATION_BASE_URL);
		System.out.println("Header: Content Type : "+ ContentType.JSON);
		System.out.println("Header: Authorization, " + authToken);
		System.out.println("Body: "+ jsonBody);		
		given()
			.spec(getRequestSpecBuilder())
			.body("{\"featureId\": " + featureId + "}").when()
			.post(OrderingRestObjects.RESOURCE_DELETE_FEATURE_TOGGLE).then()
			.spec(getResponseSpecBuilder());
		System.out.println("Feautre toggle id:" + featureId + " deleted");
	}
	
	public static String getLogNumberForEditOrder(String oauthToken) {
		String lognumber = null;
		Response getUnitRes = given()
				.spec(getRequestSpecBuilder())
				.body("{\"query\":\"**\",\"sort\":\"t_log_no asc\",\"rows\":1000,\"startPage\":0,\"filter\":{}}")
				.when()
				.post(OrderingRestObjects.RESOURCE_GET_LOG_NUMBER).then()
				.spec(getResponseSpecBuilder())
				.extract().response();	
		lognumber = getUnitRes.path("response.docs[30].t_log_no");
		return lognumber.trim();
	}

	/**
	 * This Method gets user role 
	 * @return userRole
	 * @lastModifiedBy djawale
	 * @throws Exception
	 */
	public static String getUserRoleFromOrderingFO(String username) throws Exception {
		String userRole = null;
		init();
		try {
			getWebUserRes = given()
					.baseUri(APPLICATION_BASE_URL)
					.header("Cookie", appCookie)
					.get(OrderingRestObjects.RESOURCE_AUTH_TOKEN)
					.then().assertThat()
					.statusCode(HttpStatus.SC_OK)
					.extract().response();
			userRole = getWebUserRes.jsonPath().getString("roles[0]");
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderingErrorOccured("Exception in 'https://ordering.qa.elementfleet.com/getWebUser' api.");
		}
		return userRole;
	}
	
	/**
	 * This method is used to make rest call and create a dio.
	 * @lastModifiedBy shisingh
	 * @param optionCode
	 * @param optionShortDesc
	 * @param equipCtgyId
	 * @param postProductionInd
	 * @param maxPriceAmount
	 * @param supplierToShipIndicator
	 * @param effDate
	 * @param endDate
	 * @throws Exception
	 */
	public static void createDIO(String optionCode, String optionShortDesc, String equipCtgyId, String postProductionInd, String maxPriceAmount, String supplierToShipIndicator, String effDate, String endDate) throws Exception {
		System.out.println("POST: "+ APPLICATION_BASE_URL + OrderingRestObjects.RESOURCE_CREATE_DIO);
		System.out.println("Header: Content Type : "+ ContentType.JSON);
		System.out.println("Header: Authorization, " + authToken);
		String dioBodyJson = "{\"optionCode\":\""+optionCode+"\","
				+ "\"supplierName\":\"\","
				+ "\"optionShortDesc\":\""+optionShortDesc+"\","
				+ "\"extendedDescription\":\"\","
				+ "\"equipCtgyId\":\""+equipCtgyId+"\","
				+ "\"postProductionInd\":"+postProductionInd+","
				+ "\"maxPriceAmount\":\""+maxPriceAmount+"\","
				+ "\"supplierToShipIndicator\":"+supplierToShipIndicator+","
				+ "\"effDate\":\""+effDate+"\","
				+ "\"endDate\":\""+endDate+"\"}";
		System.out.println("Json Body: "+dioBodyJson);
		String dioId = given()
				.spec(getRequestSpecBuilder())
				.body(dioBodyJson)
				.post(OrderingRestObjects.RESOURCE_CREATE_DIO).then()
				.spec(getResponseSpecBuilder())
				.extract()
				.path("dioId");
		System.out.println("API Created dio id: " + dioId);
	}
	
	/**
	 * This method is used to make rest call and create a dio with specific contents.
	 * These contents are used generally for create order test cases.
	 * Note: This method is used for creating dio with MAT as dio code.
	 * @lastModifiedBy shisingh
	 * @param optionCode
	 * @throws Exception
	 */
	public static void createDIO(String optionCode) throws Exception {
		System.out.println("POST: "+ APPLICATION_BASE_URL + OrderingRestObjects.RESOURCE_CREATE_DIO);
		System.out.println("Header: Content Type : "+ ContentType.JSON);
		System.out.println("Header: Authorization, " + authToken);
		String dioBodyJson = "{\"optionCode\":\""+optionCode+"\","
				+ "\"supplierName\":\"\","
				+ "\"optionShortDesc\":\"Automation DIO Test\","
				+ "\"extendedDescription\":\"\","
				+ "\"equipCtgyId\":\"943\","
				+ "\"postProductionInd\":false,"
				+ "\"maxPriceAmount\":\"12.00\","
				+ "\"supplierToShipIndicator\":false,"
				+ "\"effDate\":\""+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"\","
				+ "\"endDate\":\""+new SimpleDateFormat("yyyy-MM-dd").format(Date.from(new Date().toInstant().plus(7, ChronoUnit.DAYS)))+"\"}";
		System.out.println("Json Body: "+dioBodyJson);
		String dioId = given()
				.spec(getRequestSpecBuilder())
				.body(dioBodyJson)
				.post(OrderingRestObjects.RESOURCE_CREATE_DIO).then()
				.spec(getResponseSpecBuilder())
				.extract()
				.path("dioId")
				.toString();
		System.out.println("API Created dio id: " + dioId);
	}
	
	/**
	 * This method is used to delete dio using dio id.
	 * @lastModifiedBy shisingh
	 * @param dioId
	 * @throws Exception
	 */
	public static void deleteDIO(String dioId) throws Exception {
		System.out.println("POST: "+ APPLICATION_BASE_URL + OrderingRestUtility.RESOURCE_DELETE_DIO);
		System.out.println("Header: Content Type : "+ ContentType.JSON);
		System.out.println("Header: Authorization, " + authToken);
		System.out.println("Option code: "+dioId);
		given()
		.spec(getRequestSpecBuilder())
		.body("dioId="+dioId)
		.post(OrderingRestUtility.RESOURCE_DELETE_DIO).then()
		.spec(getResponseSpecBuilder());
		System.out.println("DIO id:" + dioId + " deleted");
	}
	
	/**
	 * This Method Selects the Order Type and initiates Order creation from REST API 
	 * @return StartHerePage Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response startHerePageFromAPI() throws Exception {
		startHerePageResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getStartHerePagePayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_START_HERE_PAGE).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return startHerePageResponse;
	}
	
	/**
	 * This Method gets the Complete Driver information
	 * @return Driver Information Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response getDriverInformationFromAPI() throws Exception {
		driverEmployeeID = DbConnector.getActiveDriverWithActiveBreakdownOfSpecificState("NY", "FA", CommonPage.getTestData("ClientNumber"));
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("clientnumber", CommonPage.getTestData("ClientNumber"));
		queryParams.put("employeeid", driverEmployeeID);
		queryParams.put("corpcode", "FA");
		driverInfoResponse = given()
				.queryParams(queryParams)
				.spec(getRequestSpecBuilder())
				.when()
				.get(OrderingRestObjects.RESOURCE_DRIVER_INFORMATION).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return driverInfoResponse;
	}
	
	/**
	 * This Method gets the Complete Driver information
	 * @return Driver Information Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response getSearchDriverFromAPI() throws Exception {
		searchedDriverResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getSearchDriverPayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_DRIVER_SEARCH).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return searchedDriverResponse;
	}
	
	/**
	 * This Method Selects the Driver from REST API 
	 * @return DriverPage Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response selectDriverFromAPI() throws Exception {
		getDriverInformationFromAPI();
		getSearchDriverFromAPI();
		driverPageResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getDriverPagePayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_DRIVER_PAGE).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return driverPageResponse;
	}
	
	/**
	 * This Method selects the Vehicle through REST API 
	 * @return VehiclePage Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response selectVehicleFromAPI() throws Exception {
		vehiclePageResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getVehiclePagePayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_VEHICLE_PAGE).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return vehiclePageResponse;
	}
	
	/**
	 * This Method selects the Billing and Registration through REST API  
	 * @return BillingPage Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response selectBillingAndRegstrationFromAPI() throws Exception {
		billingPageResonse = given()
				.spec(getRequestSpecBuilder())
				.body(getBillingPagePayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_BILLING_PAGE).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return billingPageResonse;
	}
	
	/**
	 * This Method Selects Dealer From REST API 
	 * @return DealerPage Response
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response selectDealerFromAPI() throws Exception {
		dealerInfoResponse = getDealerFromOrderingFO(true, true, "FA", "NY", "Ford");
		dealerPageResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getDealerPagePayloadBody())
				.when()
				.post(OrderingRestObjects.RESOURCE_DEALER_PAGE).then()
				.spec(getResponseSpecBuilder()).extract().response();
		return dealerPageResponse;
	}
	
	/**
	 * This Method Submit the Order and returns the Log Number 
	 * @return Log Number
	 * @lastModifiedBy Pratik Dhole
	 * @throws Exception
	 */
	public static Response submitOrder() throws Exception {
		String submitOrderResource = OrderingRestObjects.RESOURCE_SUBMIT_FOR_APPROVAL_ORDER;
		if("Stock".equalsIgnoreCase(CommonPage.getTestData("OrderType"))) {
			submitOrderResource = OrderingRestObjects.RESOURCE_SUBMIT_ORDER;
		}
		submitOrderResponse = given()
				.spec(getRequestSpecBuilder())
				.body(getSubmitOrderPayloadBody())
				.when()
				.post(submitOrderResource).then()
				.spec(getResponseSpecBuilder()).extract().response();
		saveLogNumber();
		return submitOrderResponse;
	}
}
