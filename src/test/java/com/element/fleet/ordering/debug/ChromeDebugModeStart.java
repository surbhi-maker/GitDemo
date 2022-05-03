package com.element.fleet.ordering.debug;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ChromeDebugModeStart {
	
	@BeforeClass
	@Parameters({ "profileFolderLocation" })
	public void createProfileFolder(String profileFolderLocation) throws Exception {
		Files.createDirectories(Paths.get(profileFolderLocation + "//chrome-debugger-profile"));
		FileUtils.cleanDirectory(new File(profileFolderLocation + "//chrome-debugger-profile"));
	}

	@Test(alwaysRun = true)
	@Parameters({ "profileFolderLocation", "chromeBrowserLoc", "port" })
	public void startChromeDebug(String profileFolderLocation, String chromeBrowserLoc, String port) throws Exception {
		String cmd = "\""+chromeBrowserLoc+"\""+" --remote-debugging-port="+port+" --user-data-dir="+"\""+profileFolderLocation+"//chrome-debugger-profile"+"\"";
		Runtime.getRuntime().exec(cmd);
	}
	
	@Test(dependsOnMethods = "startChromeDebug")
	@Parameters({ "port" })
	public void browserStarted(String port) throws Exception {
		System.out.println("Chrome browser started on "+port+" port");
	}

}