package com.element.fleet.ordering.page;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.element.fleet.ordering.commonutility.CommonPage;
import com.element.fleet.ordering.commonutility.DbConnector;
import com.element.fleet.ordering.enums.OrderingBOMainframeBridgingQueuePageEnum;
import com.element.fleet.ordering.enums.OrderingBOOrderTransmissionQueuePageEnum;
import com.element.fleet.ordering.exceptions.InvalidSwitchCaseException;
import com.element.fleet.ordering.exceptions.OrderingErrorOccured;
import com.ge.capital.rainbow.browser.BrowserAction;
import com.ge.capital.rainbow.browser.BrowserVerify;
import com.ge.capital.rainbow.browser.BrowserWait;
import com.ge.capital.rainbow.common.utils.SimpleStringCipher;
import com.ge.capital.rainbow.webdriver.WebDriverAction;
import com.ge.capital.rainbow.webdriver.WebDriverVerify;
import com.ge.capital.rainbow.webdriver.WebDriverWaits;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class OrderingBOOrderTransmissionQueuePage {
	
	private static String fileName;
	
	/**
	 * This method will verify log number as per passed parameter
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLogNumberPresent(String visibility) throws Exception {
		switch(visibility) {
			case "Visible":
				OrderingCommonPage.checkGlobalSpinnerPopUp();
				OrderingCommonPage.checkAlertPopUp();
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				BrowserVerify.verifyElementIsPresent(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				BrowserVerify.verifyElementEnabled(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				List<WebElement> logNumbers = BrowserAction.getElements(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
				for(WebElement ele: logNumbers) {
					if(!(ele.getText().trim().equals(CommonPage.getElementOrderObject().getLogNumber())))
						throw new OrderingErrorOccured("Order is not present in Order transmission queue");
					else
						System.out.println("Log number present");
					}
				break;
			case "Invisible":
				try {
					OrderingCommonPage.checkGlobalSpinnerPopUp();
					OrderingCommonPage.checkAlertPopUp();
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					BrowserWait.waitUntilElementIsDisplayed(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					BrowserVerify.verifyElementIsPresent(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					BrowserVerify.verifyElementEnabled(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					List<WebElement> logNumber = BrowserAction.getElements(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_LOG_NUMBER_LIST_XPATH);
					for(WebElement ele: logNumber) {
						if(ele.getText().trim().equals(CommonPage.getElementOrderObject().getLogNumber()))
							throw new OrderingErrorOccured("Order is present in Order transmission queue");
						else
							System.out.println("Log number not present");
						}
				}catch(Exception e) {
					System.out.println("Log Number not present");
				}
				break;
			default: throw new InvalidSwitchCaseException(visibility + " is a inavlid option");
		}
	}
	
	/**
	 * This method will search log number as per passed parameter
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void searchBy(String searchBy, String searchText) throws Exception {
		String searchField = OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_SEARCH_FIELD_XPATH.getValue();
		searchField = searchField.replace("$Log#", searchBy);
		WebDriverWaits.waitUntilElementIsDisplayed(By.xpath(searchField));
		WebDriverVerify.verifyElementIsPresent(By.xpath(searchField));
		WebDriverVerify.verifyElementEnabled(By.xpath(searchField));
		System.out.println("Searching log number using parameter: "+searchBy+" with value:- "+searchText);
		WebDriverAction.enterFieldValue(By.xpath(searchField), searchText);
		BrowserAction.click(OrderingBOMainframeBridgingQueuePageEnum.ORDERING_MAINFRAME_BRIDGING_SEARCH_BUTTON_XPATH);
	}
	
	/**
	 * This method will verify log number in file generated with WIN Scp from linux server
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLogNumber(String host, String user, String password, String manufacturer) throws Exception {
		fileName = getFile(host,user,password, manufacturer);
		String command = null;
		switch(manufacturer) {
			case "Ford": command = "cd /opt/oem/Cleo/ordering/qa/outbound/ford/stage\ncat "+fileName+"\nwq\ndf -h\nexit\n";
				break;
			case "Chrysler": command = "cd /opt/oem/Cleo/ordering/qa/outbound/chrysler\ncat "+fileName+"\nwq\ndf -h\nexit\n";
				break;
			case "GMC": command = "cd /opt/oem/Cleo/ordering/qa/outbound/\"general motors\"\ncat "+fileName+"\nwq\ndf -h\nexit\n";
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user,host, 22);
			session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SimpleStringCipher.decrypt(password));
			session.connect();
			Channel channel = session.openChannel("shell");
			System.out.println("Command: "+command);
			channel.setInputStream(new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8)));
			channel.setOutputStream(System.out);
			InputStream in = channel.getInputStream();
			StringBuilder outBuff = new StringBuilder();
			int exitStatus = -1;
			channel.connect();
			while (true) {                
				for (int c; ((c = in.read()) >= 0);) {
					outBuff.append((char) c);
				}
				if (channel.isClosed()) {
					if (in.available() > 0) 
						continue;
					exitStatus = channel.getExitStatus();
					break;
				}
			}
			channel.disconnect();
			session.disconnect();
			boolean flag = false;
			List<String> userProvidedColName = Arrays.asList(outBuff.toString().split("\n"));
			for(String ele: userProvidedColName) {
				if(ele.trim().contains(CommonPage.getElementOrderObject().getLogNumber())) {
					System.out.println("LogNumber matched: "+ele.trim());
					flag = true;
				}
			}
			if(flag == false)
				throw new OrderingErrorOccured("Log number not found in out bound file for manufacturer: "+manufacturer);
			if ( exitStatus == 0 ) {
				System.out.print (" (OK)\n");
			} else {
				System.out.print (" (NOK)\n");
			}
		} catch (IOException | JSchException ioEx) {
			ioEx.printStackTrace();
			throw new OrderingErrorOccured("Log number not found in out bound file for manufacturer: "+manufacturer);
		}
	}
	
	/**
	 * This method will return latest file generated in win scp on linux server
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static String getFile(String host, String user, String password, String manufacturer) throws Exception {
		String command = null;
		switch(manufacturer) {
			case "Ford": command = "cd /opt/oem/Cleo/ordering/qa/outbound/ford/stage\nls -t1 |  head -n 1\ndf -h\nexit\n";
				break;
			case "Chrysler": command = "cd /opt/oem/Cleo/ordering/qa/outbound/chrysler\nls -t1 |  head -n 1\ndf -h\nexit\n";
				break;
			case "GMC": command = "cd /opt/oem/Cleo/ordering/qa/outbound/\"general motors\"\nls -t1 |  head -n 1\ndf -h\nexit\n";
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user,host, 22);
			session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SimpleStringCipher.decrypt(password));
			session.connect();
			Channel channel = session.openChannel("shell");
			System.out.println("Commmand: "+command);
			channel.setInputStream(new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8)));
			channel.setOutputStream(System.out);
			InputStream in = channel.getInputStream();
			StringBuilder outBuff = new StringBuilder();
			int exitStatus = -1;
			channel.connect();
			while (true) {                
				for (int c; ((c = in.read()) >= 0);) {
					outBuff.append((char) c);
				}
				if (channel.isClosed()) {
					if (in.available() > 0) 
						continue;
					exitStatus = channel.getExitStatus();
					break;
				}
			}
			channel.disconnect();
			session.disconnect();
			List<String> userProvidedColName = Arrays.asList(outBuff.toString().split("\n"));
			for(String ele: userProvidedColName) {
				switch(manufacturer) {
					case "Ford" :
						if(ele.trim().contains("FORD-Order-")) {
							fileName = ele;
						}
						break;
					case "Chrysler" :
						if(ele.trim().contains("CHRYSLER-Order-")) {
							fileName = ele;
						}
						break;
					case "GMC" :
						if(ele.trim().contains("GM-Order-")) {
							fileName = ele;
						}
						break;
					default: throw new OrderingErrorOccured("Invalid manufacturer");
				}
			}
			if ( exitStatus == 0 ) {
				System.out.print (" (OK)\n");
			} else {
				System.out.print (" (NOK)\n");
			}
		} catch (IOException | JSchException ioEx) {
			ioEx.printStackTrace();
		}
		System.out.println("File Name:-"+fileName);
		return fileName;
	}
	
	/**
	 * This method will go to order transmission queue and search log number as per passed parameter
	 * @throws Throwable 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void goToOrderTransmissionQueueAndSearch() throws Exception {
		OrderingBOQueuePage.gotoQueuesPage();
		OrderingBOQueuePage.queuePageLoaded();
		OrderingBOQueuePage.gotoQueue("Order Transmission");
		System.out.println("Log Number: "+CommonPage.getElementOrderObject().getLogNumber());
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_QUEUE_XPATH), "Queues", "Queue label is not found");
		CommonPage.assertLabelHighlight(BrowserAction.getElement(OrderingBOOrderTransmissionQueuePageEnum.ORDERING_ORDER_TRANSMISSION_TITLE_XPATH), "- Order Transmission", "Order transmission queue label is not found");
		OrderingBOOrderTransmissionQueuePage.searchBy("Log", CommonPage.getElementOrderObject().getLogNumber());
	}
	
	/**
	 * This method will verify log number in WIN Scp linux server
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyLogNumberInWinScpServer() throws Exception {
		OrderingBOOrderTransmissionQueuePage.verifyLogNumber(CommonPage.getTestData("HostNameOEM"), CommonPage.getTestData("UserNameOEM"), CommonPage.getCredetialsData(CommonPage.getTestData("UserNameOEM")), CommonPage.getTestData("Manufacturer"));
	}
	
	/**
	 * This method will replace log number in file to respective manufacturer
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void createCopyOfACKFile(String manufacturer) throws Exception {
		switch(manufacturer) {
			case "Ford":
				System.out.println("Command Executed: "+"cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FORDACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FORDACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				break;
			case "Chrysler":
				System.out.println("Command Executed: "+"cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CHRYSLERACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CHRYSLERACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				break;
			case "GMC":
				System.out.println("Command Executed: "+"cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\GMACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start copy "+System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\GMACK.txt "+System.getProperty("user.dir")+"\\target\\ & taskkill /f /im cmd.exe");
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
	}
	
	/**
	 * This method will replace log number in file to respective manufacturer in ACK file
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void replaceLogNumberInACKFile(String manufacturer) throws Exception {
		// Need this thread because we have scheduler for wait.
		Thread.sleep(6_000); 
		Path path = null;
		switch(manufacturer) {
			case "Ford": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FORDACK.txt");
				break;
			case "Chrysler": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CHRYSLERACK.txt");
				break;
			case "GMC": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\GMACK.txt");
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
        Stream <String> lines = Files.lines(path);
        List <String> replaced = lines.map(line -> line.replaceAll("LogNumber", CommonPage.getElementOrderObject().getLogNumber())).collect(Collectors.toList());
        Files.write(path, replaced);
        lines.close(); 
	}
	
	/**
	 * This method will replace log number in file to respective manufacturer in ACK file
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void replaceLogNumberTextInACKFile(String manufacturer) throws Exception {
		Path path = null;
		switch(manufacturer) {
			case "Ford": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FORDACK.txt");
				break;
			case "Chrysler": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CHRYSLERACK.txt");
				break;
			case "GMC": path = Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\GMACK.txt");
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
        Stream <String> lines = Files.lines(path);
        List <String> replaced = lines.map(line -> line.replaceAll(CommonPage.getElementOrderObject().getLogNumber(), "LogNumber")).collect(Collectors.toList());
        Files.write(path, replaced);
        lines.close(); 
	}
	
	/**
	 * This method will copy file from local machine to win scp server on linux
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void copyACKFile(String hostName, String userName, String password, String manufacturer) throws Exception {
		JSch jsch = new JSch();
		Session session = jsch.getSession(userName, hostName, 22);
		session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(SimpleStringCipher.decrypt(password));
		session.connect();
		Channel channel = session.openChannel("sftp");
		channel.connect();
		File file = null;
		String fileAbsolutePath = null;
		switch(manufacturer) {
			case "Ford": file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FORDACK.txt");
				fileAbsolutePath = "/opt/oem/Cleo/ordering/qa/inbound/ford/FORDACK.txt";
				break;
			case "Chrysler": file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CHRYSLERACK.txt");
				fileAbsolutePath = "/opt/oem/Cleo/ordering/qa/inbound/chrysler/CHRYSLERACK.txt";
				break;
			case "GMC": file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\GMACK.txt");
				fileAbsolutePath = "/opt/oem/Cleo/ordering/qa/inbound/general motors/GMACK.txt";
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
		ChannelSftp csftp = (ChannelSftp) channel;
		csftp.put(new FileInputStream(file), fileAbsolutePath);
		channel.disconnect();
		session.disconnect();
	}
	
	/**
	 * This method will delete copy created file for ACK
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void deleteCopyOfACKFileFromLocalMachine(String manufacturer) throws Exception {
		switch(manufacturer) {
			case "Ford":
				System.out.println("Command Executed: +"+"cmd /c start del "+System.getProperty("user.dir")+"\\target\\FORDACK.txt & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start del "+System.getProperty("user.dir")+"\\target\\FORDACK.txt & taskkill /f /im cmd.exe");
				break;
			case "Chrysler":
				System.out.println("Command Executed: "+"cmd /c start del "+System.getProperty("user.dir")+"\\target\\CHRYSLERACK.txt & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start del "+System.getProperty("user.dir")+"\\target\\CHRYSLERACK.txt & taskkill /f /im cmd.exe");
				break;
			case "GMC":
				System.out.println("Command Executed: "+"cmd /c start del "+System.getProperty("user.dir")+"\\target\\GMACK.txt & taskkill /f /im cmd.exe");
				Runtime.getRuntime().exec("cmd /c start del "+System.getProperty("user.dir")+"\\target\\GMACK.txt & taskkill /f /im cmd.exe");
				break;
			default: throw new OrderingErrorOccured("Invalid manufacturer");
		}
	}
	
	/**
	 * This method will copy the ACK file to Win Scp server
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void copyACKFileToWinScpServer() throws Exception {
		replaceLogNumberInACKFile(CommonPage.getTestData("Manufacturer"));
		copyACKFile(CommonPage.getTestData("HostNameOEM"), CommonPage.getTestData("UserNameOEM"), CommonPage.getCredetialsData(CommonPage.getTestData("UserNameOEM")), CommonPage.getTestData("Manufacturer"));
	}
	
	/**
	 * This method will verify record status in DB after file process in Win Scp server
	 * @throws Exception 
	 * @lastModifiedBy Akshay Kandkonde
	 */
	public static void verifyRecordStatusInDB() throws Exception {
		List<String> recordStatus = DbConnector.getOrderRecordStatus(CommonPage.getTestData("CustomColumn4"), CommonPage.getTestData("CustomColumn5"), 
									CommonPage.getTestData("CustomColumn6") , CommonPage.getElementOrderObject().getLogNumber());
		for(String status : recordStatus) {
			if(status.equals("SUCCESS"))
				System.out.println("File Processed successfully");
			else
				throw new OrderingErrorOccured("Unable to process file for inbound flow");
		}
	}
}