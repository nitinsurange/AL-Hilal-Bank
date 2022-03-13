package com.ahb.mobileautomation.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahb.mobileautomation.utils.CapsJsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BasePage {
	
	 
		public static RequestSpecification _REQUEST_SPEC;
		public static Response _RESP;
	
    
    
    /**
     * Method used to input text in to text fields
     * @param inputText
     * @param mobileElement
     * @param driver
     */
	public void inputText(String inputText, MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		mobileElement.clear();
		mobileElement.sendKeys(inputText);
		if (driver.getKeyboard() != null) {
			closeKeyboard(driver);
		}
	}
	/**
	 * Method used to click button elements
	 * @param mobileElement
	 * @param driver
	 */
	public void clickButton(MobileElement mobileElement, @SuppressWarnings("rawtypes") AppiumDriver driver) {
		waitForVisibilityOf(mobileElement, driver);
		mobileElement.click();
	}

	/**
	 * Method used to get text
	 * @param mobileElement
	 * @return
	 */
	public String getText(MobileElement mobileElement) {
		return mobileElement.getText();
	}
	/**
	 * Method used to wait for visibility of element
	 * @param mobileElement
	 * @param driver
	 */
	protected void waitForVisibilityOf(MobileElement mobileElement, @SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 90);
		webDriverWait.until(ExpectedConditions.visibilityOf(mobileElement));
	}
	/**
	 * Method used to wait for availability of element
	 * @param mobileElement
	 * @param driver
	 */
	protected void waitForAvailabilityOf(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 90);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
	}
	protected void waitForVisibilityOfShort(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOf(mobileElement));
	}
	

	/**
	 * Method used to close keyboard or hide keyboard
	 * @param driver
	 */
	protected void closeKeyboard(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		if (driver.getKeyboard() != null) {
			if (System.getProperty("platform").equalsIgnoreCase("ios")) {
				try {
					MobileElement doneToolBarButton = (MobileElement) driver.findElement(By.xpath("//*[@label='Done']"));
					if (doneToolBarButton.isDisplayed()) {
						doneToolBarButton.click();
					}
				} catch (NoSuchElementException | TimeoutException e) {

					System.err.println("---------- Skipping clicking Done Button as its not present ----------- ");
				}
			} else {
				driver.hideKeyboard();
			}
		}
	}
	/**
	 * Method used to swipe right gesture
	 * @param driver
	 */
	public void swipeRight(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.9);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			new AndroidTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		} else {
			new IOSTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		}
	}
	public void inputText_enablekeyboard(String inputText, MobileElement mobileElement,@SuppressWarnings("rawtypes") AppiumDriver driver) {

        //waitForVisibilityOf(mobileElement,driver);
        mobileElement.click();
		mobileElement.setValue(inputText);

       // driver.getKeyboard().sendKeys(inputText);
    	
    	if(driver.getKeyboard()!=null)
        {
        closeKeyboard(driver);
        }
    }
	/**
	 * Method used to swipe ios
	 * @param driver
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 */
	public void swipeiOS(@SuppressWarnings("rawtypes") AppiumDriver driver, int startx, int starty, int endx,
			int endy) {
		new IOSTouchAction(driver).press(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, endy))
				.release().perform();
	}
	/**
	 * Method used to swipe android
	 * @param driver
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 */
	public void swipeAndroid(@SuppressWarnings("rawtypes") AppiumDriver driver, int startx, int starty, int endx,
			int endy) {
		new AndroidTouchAction(driver).press(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).moveTo(PointOption.point(endx, endy))
				.release().perform();
	}
	/**
	 * Method used to swipeup
	 * @param driver
	 */
	public void swipeUp(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.9);
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			swipeAndroid(driver, startx, starty, startx, endy);
		} else {
			swipeiOS(driver, startx, starty, startx, endy);
		}
	}
	/**
	 * Method used to swipe by screen layout
	 * @param driver
	 * @param mobileElement
	 */
	public void swipeUpByLayout(@SuppressWarnings("rawtypes") AppiumDriver driver, MobileElement mobileElement) {
		Dimension size = mobileElement.getSize();
		int starty = (int) (size.height * 0.9);
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			swipeAndroid(driver, startx, starty, startx, endy);
		} else {
			swipeiOS(driver, startx, starty, startx, endy);
		}
	}
	/**
	 * Method used to swipe down using screen layout
	 * @param driver
	 * @param mobileElement
	 */
	public void swipeDownByLayout(@SuppressWarnings("rawtypes") AppiumDriver driver, MobileElement mobileElement) {
		Dimension size = mobileElement.getSize();
		int starty = (int) (size.height * 0.20);
		int endy = (int) (size.height * 0.9);
		int startx = size.width / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			swipeAndroid(driver, startx, starty, startx, endy);
		} else {
			swipeiOS(driver, startx, starty, startx, endy);
		}
	}
	/**
	 * Method used to swipe right using layout
	 * @param driver
	 * @param swiperLayout
	 */
	public void swipeRightByLayout(@SuppressWarnings("rawtypes") AppiumDriver driver, MobileElement swiperLayout) {
		Dimension size = swiperLayout.getSize();
		int startx = (int) (size.width * 0.9);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			new AndroidTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		} else {
			new IOSTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		}
	}
	/**
	 * Method used to swipe left by screen layout
	 * @param driver
	 * @param swiperLayout
	 */
	public void swipeLeftByLayout(@SuppressWarnings("rawtypes") AppiumDriver driver, MobileElement swiperLayout) {
		Dimension size = swiperLayout.getSize();
		int endx = (int) (size.width * 0.9);
		int startx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			new AndroidTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		} else {
			new IOSTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release().perform();
		}
	}
	/**
	 * Method used to swipe left
	 * @param driver
	 */
	public void swipeLeft(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int endx = (int) (size.width * 0.9);
		int startx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			new AndroidTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release();
		} else {
			new IOSTouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, starty))
					.release();
		}
	}
	/**
	 * Method used to swipe right until element present in layout
	 * @param mobileElement
	 * @param swiperLayout
	 * @param driver
	 * @return
	 */
	public boolean swipeRightUntilElementPresentInLayout(MobileElement mobileElement, MobileElement swiperLayout,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		for (int i = 0; i <4; i++) {
			if (!isElementPresentFully(mobileElement, driver)) {
				swipeRightByLayout(driver, swiperLayout);
				System.err.println("Swipe Right: " + i);
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * Method used to swipe left until element present in layout
	 * @param mobileElement
	 * @param swiperLayout
	 * @param driver
	 * @return
	 */
	public boolean swipeLeftUntilElementPresentInLayout(MobileElement mobileElement, MobileElement swiperLayout,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		for (int i = 0; i < 4; i++) {
			if (!isElementPresentFully(mobileElement, driver)) {
				swipeLeftByLayout(driver, swiperLayout);
				System.err.println("Swipe Left: " + i);
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * Method used to swipe left until the element present
	 * @param mobileElement
	 * @param driver
	 */
	public void swipeLeftUntilElementPresent(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		do {
			swipeLeft(driver);
		} while (!isElementPresent(mobileElement));
	}
	/**
	 * Method used to swipe right until element present
	 * @param mobileElement
	 * @param driver
	 */
	public void swipeRightUntilElementPresent(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {

		do {
			swipeRight(driver);
		} while (!isElementPresent(mobileElement));
	}
	/**
	 * Method used to verify element present
	 * @param mobileElement
	 * @return true
	 */
	public boolean isElementPresent(MobileElement mobileElement) {
		try {
			if (mobileElement.isDisplayed())
				return true;
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
		return false;
	}
	/**
	 * Method used to verify element present fully
	 * @param mobileElement
	 * @param driver
	 * @return
	 */
	public boolean isElementPresentFully(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		if (isElementPresent(mobileElement)) {
			Dimension screenDimension = driver.manage().window().getSize();
			Point elementPoint = mobileElement.getLocation();
			if (screenDimension.getWidth() - elementPoint.getX() > 200) {
				System.err.println("Screen Point X : " + screenDimension.getWidth());
				System.err.println("Mobile Element Point X : " + elementPoint.getX());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	/**
	 * Method used to click button by swipeup
	 * @param mobileElement
	 * @param driver
	 */
	public void clickButtonBySwipeUp(MobileElement mobileElement, @SuppressWarnings("rawtypes") AppiumDriver driver) {
		if (isElementPresent(mobileElement)) {
			clickButton(mobileElement, driver);
		} else {
			swipeUp(driver);
			clickButton(mobileElement, driver);
		}
	}
	/**
	 * Method used to click button swipe up on layout
	 * @param mobileElement
	 * @param driver
	 * @param layout
	 */
	public void clickButtonBySwipeUpOnLayout(MobileElement mobileElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver, MobileElement layout) {
		swipeUpByLayout(driver, layout);
		clickButton(mobileElement, driver);
	}
	
	public boolean Method_isCurrentOrientationPortrait(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		String ort = driver.getOrientation().name();
		ScreenOrientation orientation = ScreenOrientation.valueOf(ort);

		if (orientation == ScreenOrientation.PORTRAIT) {
			System.out.println("Current screen orientation is portrait");
			return true;

		} else {
			System.out.println("Current screen orientation is Landscape");

			return false;
		}
}
	
	public boolean Method_isCurrentOrientationlandscape(@SuppressWarnings("rawtypes") AppiumDriver driver) {
		String ort = driver.getOrientation().name();
		ScreenOrientation orientation = ScreenOrientation.valueOf(ort);

		if (orientation == ScreenOrientation.LANDSCAPE) {
			System.out.println("Current screen orientation is Landscape");
			return true;

		} else {
			System.out.println("Current screen orientation is Portrait");

			return false;
		}
}
	 public static boolean isValidTransactionDateFormat(String value) {
	        Date date = null;
	        String format ="dd MMM yyyy HH:mma";
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(format);
	            date = sdf.parse(value);
	            if (!value.equals(sdf.format(date))) {
	                date = null;
	            }
	        } catch (ParseException ex) {
	            ex.printStackTrace();
	        }
	        return date != null;
	    }
	 
	 
	public void  selectNegAmountAccount(List<MobileElement>  elements ) {
	    	System.out.println("negative balance account check started::"+elements.size());
	    	for(int i=0;i<elements.size();i++) {
	    		System.out.println("amount:::"+elements.get(i).getText());
	    		if(elements.get(i).getText().contains("-") | elements.get(i).getText().equalsIgnoreCase("0.00") ) {
	    			elements.get(i).click();
	    			break;
	    		}
	    		
	    	}
	    }
	 
	 public void  selectPositiveAmountAccount(List<MobileElement>  elements ) {
	    	
	    	for(int i=0;i<elements.size();i++) {
	    		System.out.println("amount:::"+elements.get(i).getText());
	    		String amountText= elements.get(i).getText();
	    		String splitamount[]=amountText.split("\\.");
	    		String finalSplitAmount=splitamount[0].replaceAll(",", "");
	    		int amount = Integer.parseInt(finalSplitAmount);
	    		if(amount>100) {
	    			elements.get(i).click();
	    		}
	    	}
	    }
	 
	 @SuppressWarnings("rawtypes")
	public void swipDownByLayoutSize(AppiumDriver driver) {
		 
		 Dimension size = driver.manage().window().getSize();
			int startx=size.width/2;
			int starty=(int)(size.height*0.9);
			int endy=(int)(size.height*0.2);
				if (System.getProperty("platform").equalsIgnoreCase("android")) {
					swipeAndroid(driver, startx, starty, startx, endy);
				} else {
					swipeiOS(driver, startx, starty, startx, endy);
				}
			}
	 /**
	  * 
	  * @param driver
	  * @param number This is this VarArg parameter, it accepts single or multiple values as parameters
	  */
	 
	 
	@SuppressWarnings("rawtypes")
	public void enterAmount(AppiumDriver driver, int... number) {
		WebElement element = null;
		for (int i : number) {
			if(System.getProperty("platform").equalsIgnoreCase("Android")) {
			 element = driver.findElement(By.xpath("//*[@text='"+i+"']"));
			}
			else{
				element = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='"+i+"']"));
			}
			element.click();
		}
	}
	
	/**
	 * This method handles future date selection
	 * @param dateMonth
	 * @param getyear
	 */
	public void scheduleDatePicker(AppiumDriver driver,String dateMonth,String getyear) {
		String[] words = dateMonth.split("\\s+");
		String date;
		String month;
		
		String deviceName = (String) CapsJsonParser.getJSONObjectValue("android").get("deviceName");
		if(!deviceName.contains("emulator")){
			date = words[1].trim();
			month = words[2].trim();
		}
		else{
			month = words[1].trim();
			date = words[2].trim();
		}
		
		int futuredate=Integer.parseInt(date);
		futuredate=futuredate+1;
		
		//Check its an leap year or not
		int year=Integer.parseInt(getyear);
		boolean leapyear = false;
		
		if (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0))
		{
			leapyear=true;
		}
		
		if(futuredate==32) {
			WebElement nextmonth =driver.findElement(By.id("android:id/next"));
			nextmonth.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement selectdate=driver.findElement(By.xpath("//*[@text='1']"));
			selectdate.click();
		}
		else if ((futuredate == 31)
				& (month.equalsIgnoreCase("Jan") || month.equalsIgnoreCase("Mar") || month.equalsIgnoreCase("May"))
				|| month.equalsIgnoreCase("Jul") || month.equalsIgnoreCase("Aug") || month.equalsIgnoreCase("Oct")
				|| month.equalsIgnoreCase("Dec")) 
		{
			WebElement selectdate=driver.findElement(By.xpath("//*[@text='"+futuredate+"']"));
			selectdate.click();
		}
		else if((futuredate==29) & (month.equalsIgnoreCase("Feb")) & (leapyear=true) )
		{
			WebElement nextmonth =driver.findElement(By.id("android:id/next"));
			nextmonth.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement selectdate=driver.findElement(By.xpath("//*[@text='1']"));
			selectdate.click();
		}
		else if((futuredate==28) & (month.equalsIgnoreCase("Feb")) & (leapyear=false))
		{
			WebElement nextmonth =driver.findElement(By.id("android:id/next"));
			nextmonth.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement selectdate=driver.findElement(By.xpath("//*[@text='1']"));
			selectdate.click();
		}
		else
		{
			WebElement selectdate=driver.findElement(By.xpath("//*[@text='"+futuredate+"']"));
			selectdate.click();
		}
	}
	
	public void scrollUntilTextClick(AppiumDriver driver, String elementText) {
		
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ elementText + "\").instance(0))"))
				.click();
		;

	}
	
	public static void swipeDown(AppiumDriver driver){
		Dimension dimension=driver.manage().window().getSize();
		int scrollStart = (int) (dimension.height * 0.75);
		int scrollEnd = (int) (dimension.height * 0.25);
		int scrollWidthPosition = (int) (dimension.width/2);
		new TouchAction(driver).press(PointOption.point(scrollWidthPosition, scrollStart)).
		waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).
		moveTo(PointOption.point(scrollWidthPosition, scrollEnd)).
		release().perform();
		
	}
	
	public void scrollDown(AppiumDriver driver) {
		if(System.getProperty("platform").equalsIgnoreCase("Android"))
		{
			swipeAndroid(driver, 475, 1550, 475, 650);
		}
		else {
			swipeDown(driver);
		}
    }
	
	public  boolean isClickable(AppiumDriver driver, MobileElement ele)      
	{
	    try
	    {
	        WebDriverWait wait = new WebDriverWait(driver, 5);
	        wait.until(ExpectedConditions.elementToBeClickable(ele));
	        System.out.println("element is Clickable");
	        return true;
	    }
	    catch (Exception e)
	    {
	        return false;
	    }
	    
	    
	   
	}
	
	public void responsebody() {
		// Retrieve the body of the Response
		@SuppressWarnings("rawtypes")
		ResponseBody body = _RESP.getBody();
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		System.out.println("body::::"+bodyAsString);
	}
	 
}
