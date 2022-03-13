 package com.ahb.mobileautomation.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.ahb.mobileautomation.pages.HomePage;
import com.ahb.mobileautomation.pages.IntroPages;
import com.ahb.mobileautomation.pages.MenuPage;
import com.ahb.mobileautomation.utils.CapsJsonParser;
import com.cucumber.listener.Reporter;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;

public class MainSteps {

    private IntroPages introPages;
    private HomePage homePage;
    private MenuPage menuPage;

	@SuppressWarnings("rawtypes")
	public AppiumDriver driver;

	
	@Then("^User Tap on Skip button$")
	public void user_Tap_on_Skip_button() throws Throwable {
		 introPages = new IntroPages();
		introPages.clickonSkip();
	}
	
	
	@Then("^Check if the screen lands on the Home screen$")
	public void check_if_the_screen_lands_on_the_Home_screen() throws Throwable {
		homePage = new HomePage();
		homePage.waitforHomePage();
	}
	
	@Then("^Verify the options Start Exploring, Login, Scan QR code$")
	public void verify_the_options_Start_Exploring_Login_Scan_QR_code() throws Throwable {
		homePage.verifyHomePageOptions();
	}
	
	@When("^Navigate and verify the content on each intro screens$")
	public void navigate_and_verify_the_content_on_each_intro_screens() throws Throwable {
		 introPages = new IntroPages();
	   introPages.navigateandverifyContent();
	}

	@Then("^Tap on Next button from the last intro screen$")
	public void tap_on_Next_button_from_the_last_intro_screen() throws Throwable {
		introPages.clickonNextBtn();
	}
	
	
	@Then("^Tap on Menu option from top right side of the screen$")
	public void tap_on_Menu_option_from_top_right_side_of_the_screen() throws Throwable {
		homePage = new HomePage();
		homePage.clickonMenu();
	}
	
	@Then("^Select Live chat$")
	public void select_Live_chat() throws Throwable {
		menuPage = new MenuPage();
		menuPage.selectLiveChat();
	}
	
	@Then("^Wait for screen to load$")
	public void wait_for_screen_to_load() throws Throwable {
		menuPage.waitforLiveChatScreen();
	}
	
	@Then("^Check if the user has option to type in the message$")
	public void check_if_the_user_has_option_to_type_in_the_message() throws Throwable {
		menuPage.verifyOptiontoType();
	}
	
	@Then("^Close Live chat by tapping on X$")
	public void close_Live_chat_by_tapping_on_X() throws Throwable {
		menuPage.closeLiveChat();
	}
	
	@Then("^Check if user lands on Menu screen$")
	public void check_if_user_lands_on_Menu_screen() throws Throwable {
		menuPage.verifyMenuScreenisDisplayed();
	}
	
	
	@Then("^Select Call us$")
	public void select_Call_us() throws Throwable {
		menuPage = new MenuPage();
		menuPage.SelectCallus();
	}
	
	@Then("^Check if ‘(\\d+)’ is auto-populated in the dialer screen or user will get option to call the same number$")
	public void check_if_is_auto_populated_in_the_dialer_screen_or_user_will_get_option_to_call_the_same_number(int arg1) throws Throwable {
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			menuPage.verifyAutoPopulatedNumber(arg1);

		}else {
			String expectedioscallusnumber="6005-22229";
			menuPage.iOS_verifyAutoPopulatedNumber(expectedioscallusnumber);

		}
	}
	
	@Then("^Verify if ‘(\\d+)’ is auto-populated in the dialer screen or user will get option to call the same number$")
	public void verify_if_is_auto_populated_in_the_dialer_screen_or_user_will_get_option_to_call_the_same_number(int arg1) throws Throwable {
		if (System.getProperty("platform").equalsIgnoreCase("android")) {
			menuPage.verifyAutoPopulatedNumber(arg1);

		}else {
			String expectedioscallusnumber="6005-22228";
			menuPage.iOS_verifyAutoPopulatedNumber(expectedioscallusnumber);

		}
		
	}


	
}
