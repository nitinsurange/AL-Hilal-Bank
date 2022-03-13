package com.ahb.mobileautomation.pages;

import org.openqa.selenium.support.PageFactory;

import com.ahb.mobileautomation.configs.Hooks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import junit.framework.Assert;



public class IntroPages extends BasePage {

	 @SuppressWarnings("rawtypes")
		public AppiumDriver driver;
	    /*
	     *    Constructor initializes screen elements
	     */
	    @SuppressWarnings("static-access")
		public IntroPages()

	    {
	        this.driver = new Hooks().getDriver();
	        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
	    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Skip\"]")
	@AndroidFindBy(xpath = "//*[@text='Skip']")
	public MobileElement skip;
	
    @iOSXCUITFindBy(accessibility = "Explore. Bank. Connect.")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_title")
	public MobileElement title;
	
    @iOSXCUITFindBy(accessibility = "Defined by your lifestyle & interests")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_title")
	public MobileElement title1;
    
    @iOSXCUITFindBy(accessibility = "A gamified child Savings Account")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_title")
	public MobileElement title2;
    
    @iOSXCUITFindBy(accessibility = "Making every purchase count")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_title")
	public MobileElement title3;
    
    @iOSXCUITFindBy(accessibility = "Introducing a super app that will change the way you bank")
	@AndroidFindBy(id = "ae.ahb.digital:id/tv_subtitle")
	public MobileElement subtitle;
	
    @iOSXCUITFindBy(accessibility = "Explore our one-stop shop for your daily needs and enjoy exclusive benefits")
 	@AndroidFindBy(id = "ae.ahb.digital:id/tv_subtitle")
 	public MobileElement subtitle1;
    
    @iOSXCUITFindBy(accessibility = "Teach your kids good financial habits through pocket money tasks and saving goals")
 	@AndroidFindBy(id = "ae.ahb.digital:id/tv_subtitle")
 	public MobileElement subtitle2;
    
    @iOSXCUITFindBy(accessibility = "Get 10% cashback (up to 300 AED) at a wide range of partner brands when you use your Al Hilal debit card")
 	@AndroidFindBy(id = "ae.ahb.digital:id/tv_subtitle")
 	public MobileElement subtitle3;
    
    @iOSXCUITFindBy(accessibility = "icRightArrow")
	@AndroidFindBy(id = "ae.ahb.digital:id/iv_next")
	public MobileElement nextBtn;
	
	
	



	

	public void clickonSkip() {
		waitForVisibilityOf(skip, driver);
		clickButton(skip, driver);
	}

	public void navigateandverifyContent() {
		
		waitForVisibilityOf(skip, driver);
		
		Assert.assertEquals("Explore. Bank. Connect.", title.getText());
		Assert.assertEquals("Introducing a super app that will change the way you bank", subtitle.getText());
		
		swipeRight(driver);
		waitForVisibilityOf(title1, driver);
		
		Assert.assertEquals("Defined by your lifestyle & interests", title1.getText());
		Assert.assertEquals("Explore our one-stop shop for your daily needs and enjoy exclusive benefits", subtitle1.getText());
		
		swipeRight(driver);
		waitForVisibilityOf(title2, driver);
		
		Assert.assertEquals("A gamified child Savings Account", title2.getText());
		Assert.assertEquals("Teach your kids good financial habits through pocket money tasks and saving goals", subtitle2.getText());
		
		swipeRight(driver);
		waitForVisibilityOf(title3, driver);
		
		Assert.assertEquals("Making every purchase count", title3.getText());
		Assert.assertEquals("Get 10% cashback (up to 300 AED) at a wide range of partner brands when you use your Al Hilal debit card", subtitle3.getText());
		
		
	}
	
	public void clickonNextBtn() {
		waitForVisibilityOf(nextBtn, driver);
		clickButton(nextBtn, driver);
	}
	
}
