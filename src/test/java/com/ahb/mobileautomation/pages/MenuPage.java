package com.ahb.mobileautomation.pages;

import org.openqa.selenium.support.PageFactory;

import com.ahb.mobileautomation.configs.Hooks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import junit.framework.Assert;



public class MenuPage extends BasePage {

	 @SuppressWarnings("rawtypes")
		public AppiumDriver driver;
	    /*
	     *    Constructor initializes screen elements
	     */
	    @SuppressWarnings("static-access")
		public MenuPage()

	    {
	        this.driver = new Hooks().getDriver();
	        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
	    }

	@iOSXCUITFindBy(accessibility = "Let's chat live!")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_live_chat")
	public MobileElement live_chat;
	
    @iOSXCUITFindBy(accessibility = "Call us")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_call_us")
	public MobileElement call_us;
	
    @iOSXCUITFindBy(accessibility = "closeRound")
	@AndroidFindBy(id = "ae.ahb.digital:id/menu_item_chat_close")
	public MobileElement close;
	
    @iOSXCUITFindBy(accessibility = "Chat")
	@AndroidFindBy(id = "")
	public MobileElement chat;
    
    @iOSXCUITFindBy(accessibility = "Ok")
	@AndroidFindBy(id = "android:id/button1")
	public MobileElement ok_Btn;
	
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Call 6005-22229\"])[2]")
	@AndroidFindBy(id = "com.android.contacts:id/normal_input")
	public MobileElement dailer_input;
	
	
	public void selectLiveChat() {
		waitForVisibilityOf(live_chat, driver);
		clickButton(live_chat, driver);
	}


	public void waitforLiveChatScreen() {
		waitForVisibilityOf(close, driver);
		
	}


	public void verifyOptiontoType() {
//		This verification will not work as DOM is missing for type a message field so cannot find element to locate element
		waitForVisibilityOf(ok_Btn, driver);
		Assert.assertTrue(chat.isDisplayed());
	}
	


	public void closeLiveChat() {
		clickButton(close, driver);
		waitForVisibilityOf(ok_Btn, driver);
		clickButton(ok_Btn, driver);
		
	}


	public void verifyMenuScreenisDisplayed() {
		waitForVisibilityOf(call_us, driver);
		Assert.assertTrue(call_us.isDisplayed());
		
	}


	public void SelectCallus() {
		waitForVisibilityOf(call_us, driver);
		clickButton(call_us, driver);
		
	}


	public void verifyAutoPopulatedNumber(int expnumber) {
		
			String actualNo = getText(dailer_input);

		actualNo = actualNo.replaceAll("\\s+", "");
		int actNo = Integer.valueOf(actualNo);
		System.out.println("actualNo::"+actNo);
		System.out.println("expected No::"+expnumber);
		Assert.assertEquals("Asserting Call Us contact Number", expnumber, actNo);
		
	}
	
	public void iOS_verifyAutoPopulatedNumber(String expectediosnumber) {
		
			String actualNoios = getText(dailer_input);
			actualNoios = actualNoios.replaceAll("Call ", "");
			System.out.println("actualNo::"+actualNoios);
			System.out.println("expected No::"+expectediosnumber);
			Assert.assertEquals("Asserting Call Us contact Number", expectediosnumber, actualNoios);
			
	}
	
	
	
}
