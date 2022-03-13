package com.ahb.mobileautomation.configs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ahb.mobileautomation.utils.CapsJsonParser;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

@SuppressWarnings("rawtypes")
public class Hooks {

	private static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
	
	public static AppiumDriver getDriver() 
	{
		driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver.get();
	}

	public AppiumDriverLocalService getService() 
	{
		return service.get();
	}

	/**
	 * Start Appium Server Programmatically before each scenario
	 */

	@Before
	public void startServer() {
		AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
				.withAppiumJS(new File("/Applications/Appium Server GUI.app/Contents/Resources/app/node_modules/es6-mapify/build/lib/main.js"))
				.withIPAddress("127.0.0.1");
		service.set(appiumServiceBuilder.build());
		service.get().start();

		if (service == null || !service.get().isRunning()) {
			throw new AppiumServerHasNotBeenStartedLocallyException("AHB-: An Appium server node is not started!");
		}

		if (driver.get() == null) {
			setDriver();
		}
		else if (!new UsernamePage().checkForUsernamePage()) {
			//setDriver();
		}
	}

	public void setDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (System.getProperty("platform").equalsIgnoreCase("ios")) {
		       capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
	             capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, (String) CapsJsonParser.getJSONObjectValue("ios").get("platformVersion"));
	             capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, (String) CapsJsonParser.getJSONObjectValue("ios").get("deviceName"));
	             capabilities.setCapability(MobileCapabilityType.UDID, (String) CapsJsonParser.getJSONObjectValue("ios").get("udid"));
	             capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, (String) CapsJsonParser.getJSONObjectValue("ios").get("automationName"));
	             capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, (String) CapsJsonParser.getJSONObjectValue("ios").get("xcodeSigningId"));
	             capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, (String) CapsJsonParser.getJSONObjectValue("ios").get("xcodeOrgId"));
	             capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, (String) CapsJsonParser.getJSONObjectValue("ios").get("bundleId"));  
	             driver.set(new IOSDriver<IOSElement>(service.get().getUrl(), capabilities));
	             System.out.println("iOS device connected.");
		} else {
	        if(System.getProperty("platform").equalsIgnoreCase("android")) {
	        	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, (String) CapsJsonParser.getJSONObjectValue("android").get("platformVersion"));
	            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, (String) CapsJsonParser.getJSONObjectValue("android").get("deviceName"));
	            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, (String) CapsJsonParser.getJSONObjectValue("android").get("automationName"));
	            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, (String) CapsJsonParser.getJSONObjectValue("android").get("appPackage"));
	            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, (String) CapsJsonParser.getJSONObjectValue("android").get("appActivity"));
	            driver.set(new AndroidDriver<AndroidElement>(service.get().getUrl(), capabilities));
	    		System.out.println("Android device connected.");
	    		capabilities.setCapability("fullReset", true);   
	        }
		}
	}

	

	@After(order = 2)
	public void screenShotforFailedScenarios(Scenario scenario) {
		@SuppressWarnings("static-access")
		AppiumDriver driver = new Hooks().getDriver();
		String timeStamp;
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		String screenshotName = (timeStamp + scenario.getName().replaceAll(" ", "_"));

		if (scenario.isFailed()) {
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			File destinationDir = new File("./target/cucumber-reports/extent-report/");
			if (!destinationDir.exists()) {
				destinationDir.mkdir();
			}
			File destinationPath = new File(destinationDir + "/" + screenshotName + ".png");

			try {
				FileUtils.copyFile(sourcePath, new File(destinationPath + "/"));
				Reporter.addScreenCaptureFromPath(screenshotName + ".png");
			} catch (IOException e) {
			}

		}
	}

	@After(order = 1)
	public void stopServer() {

		@SuppressWarnings("static-access")
		AppiumDriver driver = new Hooks().getDriver();

		System.out.println("before restapp");
		driver.resetApp();
		System.out.println("after restapp");
	}
	
}
