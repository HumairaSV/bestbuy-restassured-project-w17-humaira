package com.bestbuy.testbase;

import com.bestbuy.utils.PropertyReader;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class TestBase extends TestUtils {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {


        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;

    }
}


