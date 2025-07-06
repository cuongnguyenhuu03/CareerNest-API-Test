package careerNest.api;

import careerNest.base.ApiEndpoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthenticationAPI {

    private static final Log log = LogFactory.getLog(AuthenticationAPI.class);

    public static Response login(String body) {
        return RestAssured.given()
                .accept("application/json") // đảm bảo backend trả JSON
                .contentType("application/json")
                .body(body)
                .when()
                    .post(ApiEndpoint.LOGIN);
    }
    public static Response register() {
        return RestAssured.given()
                .contentType("application/json")
                .when()
                .post("/auth/register");
    }

    public static Response refreshToken() {
        return RestAssured.given()
                .contentType("application/json")
                .when()
                .post("/auth/refresh");
    }

    public static Response logout() {
        return RestAssured.given()
                .contentType("application/json")
                .when()
                .post("/auth/logout");
    }

    public static Response changePassword() {
        return RestAssured.given()
                .contentType("application/json")
                .when()
                .post("/auth/logout");
    }
}
//
