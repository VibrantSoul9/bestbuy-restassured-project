package com.bestbuy.testsuite;/**
 * @author VibrantSoul
 */

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 *@author VibrantSoul
 */
public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public static void storeTest() {

        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
//        1. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("Limit Value = " + limit);
//        2. Extract the total
        int total = response.extract().path("total");
        System.out.println("Limit Value = " + total);
//        3. Extract the name of 5th store
        String storeName = response.extract().path("data[4].name");
        System.out.println("Name of the 5th store = " + storeName);
//        4. Extract the names of all the store
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("All store names are = " + allStoreName);
//        5. Extract the storeId of all the store
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("All store IDs are = " + allStoreId);
//        6. Print the size of the data list
        List<String> allData= response.extract().path("data");
        System.out.println("Size of the data list = " + allData.size());
//        7. Get all the value of the store where store name = St Cloud
        List<HashMap<String, Object>> storeInfo = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("Store St Cloud information = " + storeInfo);
//        8. Get the address of the store where store name = Rochester
        List<String> addressOfStore = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Address of store Rochester: " + addressOfStore.get(0));
//        9. Get all the services of 8th store
        List<List<HashMap<String, Object>>> services = response.extract().path("data[7].services");
        System.out.println("Services of 8th Store = " + services);
//        10. Get storeservices of the store where service name = Windows Store
        System.out.println("Store services = " + response.extract().path("data.services.find{it.name == 'Windows Store'}.storeservices"));
//        11. Get all the storeId of all the store
//        12. Get id of all the store
//        13. Find the store names Where state = ND
        List<String> listOfStoreName= response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Store name whose state is ND: " + listOfStoreName);
//        14. Find the Total number of services for the store where store name = Rochester
        List<List<HashMap<String, Object>>> numberOfServices = response.extract().path("data.find{it.name == 'Rochester'}.services");
        System.out.println("Size of services = " + numberOfServices.size());
//        15. Find the createdAt for all services whose name = “Windows Store”
        //System.out.println("Data" + response.extract().path("data.findAll{it}.services.findAll{it.name=='Windows Store'}"));
        // System.out.println("Data1:" + response.extract().path("data.services.findAll{it.name.contains('Windows Store')}.id"));
        //System.out.println("Data1:" + response.extract().path("data.services.findAll{it.name.contains('Windows Store')}.findAll{it.id}"));
        //response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}");
// Extract createdAt values
        // def createdAtValues = windowsStoreServices.collect { it.createdAt }

// Print the result
        //println "createdAt values for services named 'Windows Store': $createdAtValues"
        System.out.println("ABC: " + response.extract().path("data.collectMany{store->store.services.findAll{it.name == 'Windows Store'}.collect{ it.createdAt }}"));
//        16. Find the name of all services Where store name = “Fargo”
//        17. Find the zip of all the store
//        18. Find the zip of store name = Roseville
//        19. Find the storeservices details of the service name = Magnolia Home Theater
//        20. Find the lat of all the s
      //  15. Find the createdAt for all services whose name = “Windows Store”
        List<String> createdAtForAllServices = response.extract().path("data.find{it.services}.services.findAll{it.name =='Windows Store'}.storeservices.createdAt");
        System.out.println("Created At For all services : " + createdAtForAllServices);

//        16. Find the name of all services Where store name = “Fargo”
        List<String> nameOfAllServices =  response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("Name of all services : " + nameOfAllServices);


//        17. Find the zip of all the store
        List<String> zipOfAllStores = response.extract().path("data.zip");
        System.out.println("Zip of all stores : " +zipOfAllStores);

//        18. Find the zip of store name = Roseville
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("Zip of the store Roseville is :" + zip);

//        19. Find the storeservices details of the service name = Magnolia Home Theater
        List<String> storeServicesDetails = response.extract().path("data.find{it.services}.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("Store Services details : " + storeServicesDetails);


//        20. Find the lat of all the stores
        List<Double> latOfAllStores = response.extract().path("data.lat");
        System.out.println("Lat of all stores : " +latOfAllStores);
    }
}
