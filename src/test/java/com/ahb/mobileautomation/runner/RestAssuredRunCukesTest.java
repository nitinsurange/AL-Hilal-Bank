package com.ahb.mobileautomation.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.ahb.mobileautomation.utils.ExtentReportGenerator;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.ahb.apiautomation.stepdefinations"},
        tags = {"@apiRegression"},
        dryRun = false,
        plugin = {
                "html:target/cucumber-reports/cucumber-pretty", 
                "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/extent-report/report.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "pretty",
                "rerun:target/cucumber-reports/rerun.txt"
        })
public class RestAssuredRunCukesTest {

    private TestNGCucumberRunner testNGCucumberRunner;


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
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
