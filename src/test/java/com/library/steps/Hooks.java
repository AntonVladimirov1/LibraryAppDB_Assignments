package com.library.steps;

import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setUp(){
        //WebDriver driver = Driver.getDriver(); - can be useful to simplify driver call in different Classes
        System.out.println("browser Driver from BEFORE");
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }
    //@After
    public void tearDown(Scenario scenario){
        System.out.println("close Driver from AFTER");
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    @Before("@db")
    public void setupDB(){
        System.out.println("connecting to DB...");
        DB_Util.createConnection();
    }
    @After("@db")
    public void closeDB(){
        System.out.println("closing DB...");
        DB_Util.destroy();
    }



}
