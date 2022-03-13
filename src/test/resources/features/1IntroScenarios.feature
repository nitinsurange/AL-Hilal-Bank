@regression
Feature: Intro Screen features

 @introscenario1
Scenario:  User Perfoms Intro Screen Validations 1
 
	Then User Tap on Skip button
	Then Check if the screen lands on the Home screen
  And Verify the options Start Exploring, Login, Scan QR code
	
   @introscenario2
Scenario:  User Perfoms Intro Screen Validations 2
 
	When Navigate and verify the content on each intro screens
	Then Tap on Next button from the last intro screen
  Then Check if the screen lands on the Home screen