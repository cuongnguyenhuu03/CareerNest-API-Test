package careerNest.test.auth;

import careerNest.api.AuthenticationAPI;
import careerNest.helper.LogUtils;
import careerNest.helper.PropertyReader;
import careerNest.listener.TestListener;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Listeners(TestListener.class)
public class LoginTest {

    // TC_001_[Authentication] login with valid credential
    @Test(description = "Verify login success")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User logs in with valid credentials")
    public void testLoginSuccess() {
        LogUtils.info("Login");
        String loginBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}",
                PropertyReader.get("auth.username"),
                PropertyReader.get("auth.password"));

        Response response = AuthenticationAPI.login(loginBody);
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/LoginSuccessSchema.json"));

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("message")).isEqualTo("login successfully");
        assertThat(response.jsonPath().getInt("data.user.id")).isEqualTo(6);
        assertThat(response.jsonPath().getString("data.user.email")).isEqualTo("nguyenhuucuong111103@gmail.com");
        assertThat(response.jsonPath().getString("data.user.role.name")).isEqualTo("USER");
        assertThat(response.jsonPath().getString("data.access_token")).isNotEmpty();
    }

    // TC_002_[Authentication] login with wrong password
    @Test
    public void LoginWithWrongPassword() {
        String loginBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}",
                PropertyReader.get("auth.username"),
                PropertyReader.get("wrongPassword"));

        Response response = AuthenticationAPI.login(loginBody);
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/LoginFailSchema.json"));

        assertThat(response.statusCode()).isEqualTo(401);
        assertThat(response.jsonPath().getString("message")).isEqualTo("call API successfully");
        assertThat(response.jsonPath().getString("error")).isEqualTo("login failed");
    }

    // TC_003_[Authentication] login with invalid email
    @Test
    public void LoginWithInvalidEmail() {
        String loginBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}",
                PropertyReader.get("cuong.com"),
                PropertyReader.get("cuongdeptraiA@03"));

        Response response = AuthenticationAPI.login(loginBody);
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/LoginFailSchema.json"));

        assertThat(response.statusCode()).isEqualTo(401);
        assertThat(response.jsonPath().getString("message")).isEqualTo("call API successfully");
        assertThat(response.jsonPath().getString("error")).isEqualTo("login failed");
    }
}
