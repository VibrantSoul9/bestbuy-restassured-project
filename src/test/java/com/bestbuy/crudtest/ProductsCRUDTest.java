package com.bestbuy.crudtest;/**
 * @author VibrantSoul
 */

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 *@author VibrantSoul
 */
public class ProductsCRUDTest {
    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
    static String name = "Duracell - AAA Batteries (8-Pack)";
    static String type = "HardGood";
    static double price = 5.49;
    static String upc = "041333424019";
    static int shipping = 0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static long id = 43900;
    @Test
    public void createPost()
    {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setId(id);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .body(productPojo)
                        .post();
        response.prettyPrint();
        response.then().statusCode(400);
    }
    @Test
    public void readGetSingleId(){
        Response response = given()
                .when()
                .get("/"+id);
        response.then().statusCode(200);
    }
    @Test
    public void getProducts(){
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
    }
    @Test
    public void patchUpdatedId(){
        Response response = given()
                .when()
                .patch("/"+ id);
        response.then().statusCode(200);
    }
    @Test
    public void deleteId(){
        given()
                .when()
                .delete("/"+id)
                .then()
                .statusCode(404);
        given()
                .when()
                .get("/"+ id)
                .then()
                .statusCode(404);
    }

}
