@regression
Feature: Call us features

 @callus
Scenario:  User Perfoms Call us functionality
 
	Then User Tap on Skip button
	Then Tap on Menu option from top right side of the screen
	Then Select Call us
	Then Check if ‘600522229’ is auto-populated in the dialer screen or user will get option to call the same number
	
	 @callusInvalidnumber
Scenario:  User Perfoms Call us functionality with invalid number
 
	Then User Tap on Skip button
	Then Tap on Menu option from top right side of the screen
	Then Select Call us
	Then Check if ‘600522228’ is auto-populated in the dialer screen or user will get option to call the same number
	
	
	
	
	
