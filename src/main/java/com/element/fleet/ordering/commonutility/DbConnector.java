package com.element.fleet.ordering.commonutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.element.fleet.ordering.rest.OrderingRestAPI;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;

public class DbConnector {

	/**
	 * This method creates a db connection, executes given querry in the db nd return the resul set.
	 * @lastmodified by shisingh
	 * @param url
	 * @param username
	 * @param password
	 * @param querry
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private static ResultSet getResultFromPostgreSQLDb(String url, String username, String password, String querry) throws SQLException {
		Connection connection = null; 
		Statement statement = null;
		connection = DriverManager.getConnection(url, username, password); 
		statement = connection.createStatement(); 
		ResultSet result = statement.executeQuery(querry); 
		connection.close(); 
		return result;
	}
	
	/**
	 * This method used to get active used unit from the db.
	 * @lastModifiedBy shisingh
	 * @param countryCode
	 * @param clientCode
	 * @return
	 * @throws Exception
	 */
	public static String getActiveUsedUnit(String countryCode, String clientCode) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("tcvd1mfdcDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("tcvd1mfdcDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("tcvd1mfdcDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("tcvd1mfdcDbHostNameQA");
		String username = CommonPage.getCredetialsData("tcvd1mfdcDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("tcvd1mfdcDbPasswordQA"));
		String usedUnitNo = null;
		String query = "select unit_no "
					+ "from sysusr.mast_unit "
					+ "where corp_cd = '"+countryCode+"' "
					+ "and invy_sta_ind = '4'  "
					+ "and client_no = '"+clientCode+"' "
					+ "order by random() "
					+ "limit 1";
		System.out.println("Db query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			usedUnitNo = result.getString("unit_no");			
		}
		if(Objects.isNull(usedUnitNo)) {
			throw new OrderingErrorOccured("usedUnitNo value is null");
		}
		return usedUnitNo.trim();
	}
	
	/**
	 * This method used to get active driver with active breakdown from the db.
	 * @lastModifiedBy shisingh
	 * @param url
	 * @param countryCode
	 * @param clientCode
	 * @param usedUnitClientCode
	 * @return
	 * @throws Exception
	 */
	public static String getActiveDriverWithActiveBreakdown(String countryCode, String clientCode) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String driverEmployeeID = null;
		String query = "select * from (" + 
				"select distinct(psn.empl_id) " + 
				"from enterprise.person psn " + 
				"left outer join " + 
				"enterprise.person_asset psnAst " + 
				"on psn.spin_psn_id = psnAst.spin_psn_id " + 
				"where psn.psn_del_from_src_ind = 'N' " + 
				"and psn.empl_stat_cd=1 " + 
				"and psn.cli_no='"+ clientCode +"' " + 
				"and psn.corp_cd='"+ countryCode +"' " + 
				"and bkdn in (" + 
				"select bkdn " + 
				"from common.client_breakdown " + 
				"where corp_cd='"+ countryCode +"' " + 
				"and cli_no='"+ clientCode +"' " + 
				"and is_active='Y' "+ 
				"and bkdn<>' '" + 
				") and psn.home_addr_line1 <> ' ' and psn.empl_id ~ '^[0-9a-zA-Z]*$') as activedriverbreakdownlist " + 
				"order by random() " + 
				"limit 1;";
		System.out.println("Db query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			driverEmployeeID = result.getString("empl_id");			
		}
		if(Objects.isNull(driverEmployeeID)) {
			throw new OrderingErrorOccured("driverEmployeeID value is null");
		}
		return driverEmployeeID.trim();
	}
	
	/**
	 * This method used to get active driver with active breakdown of specific state from the db.
	 * @lastModifiedBy shisingh
	 * @param url
	 * @param countryCode
	 * @param clientCode
	 * @return
	 * @throws Exception
	 */
	public static String getActiveDriverWithActiveBreakdownOfSpecificState(String stateCode, String countryCode, String clientCode) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String driverEmployeeID = null;
		String query = "select * from (" + 
				"select distinct(psn.empl_id) " + 
				"from enterprise.person psn " + 
				"left outer join " + 
				"enterprise.person_asset psnAst " + 
				"on psn.spin_psn_id = psnAst.spin_psn_id " + 
				"where psn.psn_del_from_src_ind = 'N' " + 
				"and psn.empl_stat_cd=1 " + 
				"and psn.cli_no='"+ clientCode +"' " + 
				"and psn.corp_cd='"+ countryCode +"' " + 
				"and psn.home_st_prov_abbr_cd='" + stateCode + "'" +
				"and bkdn in (" + 
				"select bkdn " + 
				"from common.client_breakdown " + 
				"where corp_cd='"+ countryCode +"' " + 
				"and cli_no='"+ clientCode +"' " + 
				"and is_active='Y' "+ 
				"and bkdn<>' '" + 
				") and psn.home_addr_line1 <> ' ' and psn.empl_id ~ '^[0-9a-zA-Z]*$') as activedriverbreakdownlist " + 
				"order by random() " + 
				"limit 1;";
		System.out.println("Db query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			driverEmployeeID = result.getString("empl_id");			
		}
		if(Objects.isNull(driverEmployeeID)) {
			throw new OrderingErrorOccured("driverEmployeeID value is null");
		}
		return driverEmployeeID.trim();
	}
	
	/**
	 * This method is used to get supplier make code.
	 * @param make
	 * @return
	 * @throws Exception
	 */
	public static String getMakeCode(String make) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String makeCode = null;
		String query = "select cv.code_value from common.code_value cv where cv.code_value_name = '"+ make +"' and cv.code_type_cd = 'SUPP';";
		System.out.println("Db query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {			
			makeCode = result.getString("code_value");			
		}
		if(Objects.isNull(makeCode)) {
			throw new OrderingErrorOccured("makeCode value is null");
		}
		return makeCode.trim();
	}
	
	/**
	 * This method used to get PO delegate email address
	 * @lastModifiedBy djawale
	 * @param PO data Id
	 * @return ArrayList of delegate email addresses
	 * @throws Exception
	 */
	public static ArrayList<String> getDelegateEmailAddress(String dataId) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		ArrayList<String> emailList = new ArrayList<>();
		String query = "SELECT cntct_email_nm " +
				"FROM ordering.order_po_contact_delegate "+
				"where ord_po_cntct_id in ("+
				"SELECT ord_po_cntct_id " +
				"FROM ordering.order_po_contact "+
				"where ord_po_id ="+dataId+");";
		System.out.println("Query: "+query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			emailList.add(result.getString(1));		
		}
		if(Objects.isNull(emailList)) {
			throw new OrderingErrorOccured("emailList value is null");
		}
		System.out.println("Delegate Email List: "+emailList);
		return emailList;
	}
	
	/**
	 * This method used to delete the feature toggle entry
	 * @lastModifiedBy pdhole
	 * @param featureName
	 * @throws Exception
	 */
	public static void deleteFeatureToggleByName(String featureName) throws Exception {
		String id = null;
		String query = "select * from common.feature as f where f.feature_nm='"+ featureName +"'";
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		System.out.println("Query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			id = result.getString("feature_id");
		}
		if(Objects.isNull(id)) {
			throw new OrderingErrorOccured("emailList value is null");
		}
		System.out.println("Feature Id: " + id);
		OrderingRestAPI.deleteFeatureToggle(id);
	}
	

	/**
	 * This method returns Bridging table data for a specified order.
	 * @lastModifiedBy mkaricharla
	 * @param orderID
	 * @return Map of bridge_action, target, status
	 * @throws Exception
	 */
	public static Map<String, ArrayList<String>> getBridgingOutput(String logNumber) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		Map<String, ArrayList<String>> output = new HashMap<>();
		String query = "SELECT bridge_action,target,status FROM bridging.bridge_record where log_no="+logNumber+" order by target asc;";
		System.out.println("Query: "+query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		int columnCount = result.getMetaData().getColumnCount();
		for(int i =1 ; i<=columnCount;i++) {
			output.put(result.getMetaData().getColumnName(i), new ArrayList<String>());
		}
		System.out.println(output.entrySet());
		while(result.next()) {
			int j= 1;
			for(Map.Entry<String, ArrayList<String>> e : output.entrySet()) {
				e.getValue().add(result.getString(j));
				j++;
			}
		}
		output.entrySet().stream().forEach(e->System.out.println(e.getKey() + e.getValue()));
		return output;
	}
	
	/**	
	 * This method will return status for record for respective order
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static ArrayList<String> getOrderRecordStatus(String url, String user, String password, String logNumber) throws Exception {
		password = SimpleStringCipher.decrypt(password);
		ArrayList<String> orderRecordStatus=new ArrayList<>();
        String query = "SELECT status FROM bridging.bridge_record where log_no='"+logNumber+"' and destination in('ORDERPO', 'ORDERACKNOWLEDGEMENT') order by audit_insert_date desc;";
        System.out.println("Query: "+query);
        ResultSet result = DbConnector.getResultFromPostgreSQLDb(url, user, password, query);
        while (result.next()) {
        	orderRecordStatus.add(result.getString(1));
        }
        System.out.println("Order Record Status : "+orderRecordStatus);
        return orderRecordStatus;
	}
	
	/**
	 * This method returns dio id of the given dio option code.
	 * @lastModifiedBy shishingh
	 * @param dioOptionCode
	 * @return
	 * @throws Exception
	 */
	public static String getDesieredDIOId(String dioOptionCode) throws Exception {
		System.out.println("Db host name: " + CommonPage.getCredetialsData("orderingDbHostNameQA"));
		System.out.println("Db username: " + CommonPage.getCredetialsData("orderingDbUsernameQA"));
		System.out.println("Db password: " + CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dbUrl = CommonPage.getCredetialsData("orderingDbHostNameQA");
		String username = CommonPage.getCredetialsData("orderingDbUsernameQA");
		String password = SimpleStringCipher.decrypt(CommonPage.getCredetialsData("orderingDbPasswordQA"));
		String dioId = null;
		String query = "select * from \"ordering\".dealer_install_option where option_cd ='"+dioOptionCode+"';";
		System.out.println("Db query: " + query);
		ResultSet result = DbConnector.getResultFromPostgreSQLDb(dbUrl, username, password, query);
		while (result.next()) {
			dioId = result.getString("dio_id");			
		}
		return dioId;
	}
	
}