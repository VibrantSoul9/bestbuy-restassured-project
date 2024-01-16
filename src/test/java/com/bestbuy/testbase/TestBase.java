package com.bestbuy.testbase;/**
 * @author VibrantSoul
 */

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 *@author VibrantSoul
 */
public class TestBase {
    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost";
       // RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;
    }
}
