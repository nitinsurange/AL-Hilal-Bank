package com.ahb.mobileautomation.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.ahb.mobileautomation.configs.Hooks;
import com.ahb.mobileautomation.utils.ExtentReportGenerator;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.ahb.mobileautomation"},
        tags = {"@regression"},
        dryRun = false,
        plugin = {
                "html:target/cucumber-reports/cucumber-pretty", 
                "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/extent-report/report.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "pretty",
                "rerun:target/cucumber-reports/rerun.txt"
        })
public class RunCukesTest {

    private TestNGCucumberRunner testNGCucumberRunner;


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
    	System.setProperty("platform", "iOS");
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();

    }


	
    @AfterSuite
    public void generateExtentReport()
    {
        Reporter.loadXMLConfig(ExtentReportGenerator.getReportConfigPath());
    }

}
