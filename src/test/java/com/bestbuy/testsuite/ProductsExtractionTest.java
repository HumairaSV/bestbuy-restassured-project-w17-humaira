package com.bestbuy.testsuite;

import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    //21. Extract the limit
    @Test
    public void test021(){
        int limit = response.extract().path("limit");
        System.out.println("_________________________________");
        System.out.println("The limit is : " + limit);
        System.out.println("_________________________________");
    }

    //22. Extract the total
    @Test
    public void test022(){
        int total = response.extract().path("total");
        System.out.println("_________________________________");
        System.out.println("The total is : " + total);
        System.out.println("_________________________________");
    }

    //23. Extract the name of 5th product
    @Test
    public void test023(){
        String prodName = response.extract().path("data[4].name");
        System.out.println("_________________________________");
        System.out.println("The 5th product name is : " + prodName);
        System.out.println("_________________________________");
    }

    //24. Extract the names of all the products
    @Test
    public void test024(){
        ArrayList<String>prodNames = response.extract().path("data.name");
        System.out.println("_________________________________");
        System.out.println("The names of all the products are : " + prodNames);
        System.out.println("_________________________________");
    }

    //25. Extract the productId of all the products
    @Test
    public void test025(){
        ArrayList<Integer>prodIds = response.extract().path("data.id");
        System.out.println("_________________________________");
        System.out.println("The product ID of all products are : " + prodIds);
        System.out.println("_________________________________");
    }

    //26. Print the size of the data list
    @Test
    public void test026(){
        List<?> dataList = response.extract().path("data");
        System.out.println("_________________________________");
        System.out.println("The total is : " + dataList.size());
        System.out.println("_________________________________");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027(){
        List<String> valueOfProd = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("_________________________________");
        System.out.println("All values for the product 'Energizer - MAX Batteries AA (4-Pack)' are : " + valueOfProd);
        System.out.println("_________________________________");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028(){
        List<String> modelOfProd = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("_________________________________");
        System.out.println("The model for the product Energizer - N Cell E90 Batteries (2-Pack) is : " + modelOfProd);
        System.out.println("_________________________________");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029(){
        List<String> categories = response.extract().path("data[7].categories");
        System.out.println("_________________________________");
        System.out.println("All the categories for the 8th product are : " + categories );
        System.out.println("_________________________________");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030(){
        List<String> categoriesOf = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("_________________________________");
        System.out.println("All the categories for the product id 150115 are : " + categoriesOf );
        System.out.println("_________________________________");
    }


    //31. Get all the descriptions of all the products
    @Test
    public void test031(){
        List<String> descriptionAll = response.extract().path("data.description");
        System.out.println("_________________________________");
        System.out.println("The total is : " + descriptionAll);
        System.out.println("_________________________________");
    }


    //32. Get id of all the all categories of all the products
    @Test
    public void test032(){
        List<String> idAll = response.extract().path("data.categories.id");
        System.out.println("_________________________________");
        System.out.println("The id of all categories of all the products : " + idAll );
        System.out.println("_________________________________");
    }


    //33. Find the product names Where type = HardGood
    @Test
    public void test033(){
        List<String> prodNames = response.extract().path("data.findAll{it.type == 'HardGood' }.name");
        System.out.println("_________________________________");
        System.out.println("The  product names where type is HardGood : " + prodNames );
        System.out.println("_________________________________");
    }


    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034(){
        List<?> totalCat = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("_________________________________");
        System.out.println("The Total number of categories for the product - Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + totalCat.size());
        System.out.println("_________________________________");
    }


    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035(){
        List<?> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("_________________________________");
        System.out.println("The createdAt for all products whose prices is <5.49 : " + createdAt);
        System.out.println("_________________________________");
    }


    //36. Find the name of all categories where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036(){
        List<String>nameCat = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("_________________________________");
        System.out.println("The name of all categories where product name is Energizer - MAX Batteries AA (4-Pack) are : " + nameCat );
        System.out.println("_________________________________");
    }


    //37. Find the manufacturer of all the products
    @Test
    public void test037(){
        List<String>manufacturer = response.extract().path("data.manufacturer");
        System.out.println("_________________________________");
        System.out.println("The manufacturer for all the products : " + manufacturer);
        System.out.println("_________________________________");
    }


    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test038(){
        List<String>image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("_________________________________");
        System.out.println("The image for products where manufacturer is Energizer : " + image );
        System.out.println("_________________________________");
    }


    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039(){
        List<?> createdAt = response.extract().path("data.findAll{it.price > 5.49}.categories.createdAt");
        System.out.println("_________________________________");
        System.out.println("The createdAt for all products whose prices is > 5.49 : " + createdAt);
        System.out.println("_________________________________");
    }


    //40. Find the url of all the products
    @Test
    public void test040(){
        List<String> url = response.extract().path("data.url");
        System.out.println("_________________________________");
        System.out.println("The url for all products is : " + url );
        System.out.println("_________________________________");
    }


}
