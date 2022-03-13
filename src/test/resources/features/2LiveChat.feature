@regression
Feature: Live Chat features

 @liveChat
Scenario:  User Perfoms Live Chat functionality
 
	Then User Tap on Skip button
	Then Tap on Menu option from top right side of the screen
	Then Select Live chat
	Then Wait for screen to load
	Then Close Live chat by tapping on X
	Then Check if user lands on Menu screen
	
@liveChat
Scenario:  User Perfoms Live Chat functionality
 
	Then User Tap on Skip button
	Then Tap on Menu option from top right side of the screen
	Then Select Live chat
	Then Wait for screen to load
	Then Check if the user has option to type in the message
	Then Close Live chat by tapping on X
	Then Check if user lands on Menu screen
	