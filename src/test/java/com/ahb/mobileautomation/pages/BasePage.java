package com.ahb.mobileautomation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;

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
