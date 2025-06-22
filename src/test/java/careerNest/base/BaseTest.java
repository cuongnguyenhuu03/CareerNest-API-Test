package careerNest.base;

import careerNest.helper.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    public static RequestSpecification authSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = PropertyReader.get("base.url");
        RestAssured.basePath = PropertyReader.get("base.path");

        // Authenticate before call API
//        String loginPayload = String.format("{\"username\":\"%s\", \"password\":\"%s\"}",
//                PropertyReader.get("auth.username"),
//                PropertyReader.get("auth.password"));
//
//        // Send auth request
//        Response loginResponse = RestAssured.given()
//                .contentType("application/json")
//                .body(loginPayload)
//                .post("/auth/login/");
//
//        String token = loginResponse.jsonPath().getString("accessToken");
//
//        // Save reusable request spec
//        authSpec = RestAssured.given()
//                .header("Authorization", "Bearer " + token)
//                .contentType("application/json");
    }
}
