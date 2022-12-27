package com.bestbuy.testsuite;

import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("_________________________________");
        System.out.println("The value of limit is : " + limit);
        System.out.println("_________________________________");

    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("_________________________________");
        System.out.println("The value of total is : " + total);
        System.out.println("_________________________________");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("_____________________________________________");
        System.out.println("The name of the 5th store is : " + storeName);
        System.out.println("_____________________________________________");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("_____________________________________________");
        System.out.println("The names of all the stores is : " + storeNames);
        System.out.println("_____________________________________________");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        ArrayList<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("_____________________________________________");
        System.out.println("The storeID of all the stores is : " + storeIds);
        System.out.println("_____________________________________________");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataList = response.extract().path("data");
        System.out.println("_____________________________________________");
        System.out.println("The size of data list is : " + dataList.size());
        System.out.println("_____________________________________________");

    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<Object> storeAll = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("_____________________________________________");
        System.out.println("All values for store St Cloud : " + storeAll);
        System.out.println("_____________________________________________");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        String address = response.extract().path("data[8].address");
        System.out.println("_____________________________________________");
        System.out.println("The address of Rochester store : " + address);
        System.out.println("_____________________________________________");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        ArrayList<Object> services = response.extract().path("data[7].services");
        System.out.println("_____________________________________________");
        System.out.println("All services for the 8th store : " + services);
        System.out.println("_____________________________________________");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("_____________________________________________");
        System.out.println("Store services for the Windows Store  : " + services);
        System.out.println("_____________________________________________");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Object> storeIDs = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId");
        System.out.println("_____________________________________________");
        System.out.println("Store ID of all the stores : " + storeIDs);
        System.out.println("_____________________________________________");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> idStore = response.extract().path("data.findAll{it.id}.id");
        System.out.println("_____________________________________________");
        System.out.println("Store ID of all the stores : " + idStore);
        System.out.println("_____________________________________________");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("_____________________________________________");
        System.out.println("Store names where state is ND : " + storeName);
        System.out.println("_____________________________________________");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
      List<?> totalNoOfServices = response.extract().path("data.find{it.name == 'Rochester'}.services.findAll{it.id}");
        System.out.println("_____________________________________________");
        System.out.println("Total no of services for Rochester store : " + totalNoOfServices.size());
        System.out.println("_____________________________________________");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("_____________________________________________");
        System.out.println("Store services for the Windows Store  : " + services);
        System.out.println("_____________________________________________");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
    List<String> fargoServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("_____________________________________________");
        System.out.println("Name of all services for Fargo store  : " + fargoServices);
        System.out.println("_____________________________________________");
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("_____________________________________________");
        System.out.println("Zip of all the stores  : " + zip);
        System.out.println("_____________________________________________");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        ArrayList<Integer> zipRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("_____________________________________________");
        System.out.println("Zip of all the stores  : " + zipRoseville);
        System.out.println("_____________________________________________");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
     List<Object> storeServices = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("_____________________________________________");
        System.out.println("Store services details for the service Magnolia Home Theater  : " + storeServices);
        System.out.println("_____________________________________________");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("_____________________________________________");
        System.out.println("lat of all the stores  : " + lat);
        System.out.println("_____________________________________________");
    }

}
