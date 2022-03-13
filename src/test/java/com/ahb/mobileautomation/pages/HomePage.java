package com.ahb.mobileautomation.pages;

import org.openqa.selenium.support.PageFactory;

import com.ahb.mobileautomation.configs.Hooks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import junit.framework.Assert;



public class HomePage extends BasePage {

	 @SuppressWarnings("rawtypes")
		public AppiumDriver driver;
	    /*
	     *    Constructor initializes screen elements
	     */
	    @SuppressWarnings("static-access")
		public HomePage()

	    {
	        this.driver = new Hooks().getDriver();
	        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
	    }

    @iOSXCUITFindBy(accessibility = "Marhaba!")
    @AndroidFindBy(id = "ae.ahb.digital:id/tv_title")
	public MobileElement title_homePage;
	
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name=\"Start exploring\"]")
	@AndroidFindBy(id = "ae.ahb.digital:id/bv_create_account")
	public MobileElement start_exploring;
	
    @iOSXCUITFindBy(accessibility = "Already have an account? Log in")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_login")
	public MobileElement login;
	
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Under 18? Scan the QR code!\"]")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_qr")
	public MobileElement scan_qrCode;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"AHB.LandingView\"]/XCUIElementTypeOther[2]")
	@AndroidFindBy(id = "ae.ahb.digital:id/iv_menu")
	public MobileElement menu;
	
	

	
	public void waitforHomePage() throws InterruptedException {
		Thread.sleep(5000);
		waitForVisibilityOf(title_homePage, driver);
		Assert.assertTrue(title_homePage.isDisplayed());
	}
	

	public void verifyHomePageOptions() {
		
		Assert.assertTrue(start_exploring.isDisplayed());
		Assert.assertTrue(login.isDisplayed());
		Assert.assertTrue(scan_qrCode.isDisplayed());
	}
	
	public void clickonMenu() {
		waitForVisibilityOf(menu, driver);
		clickButton(menu, driver);
	}

	
	
}
