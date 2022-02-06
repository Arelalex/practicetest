package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TestApi {

    @BeforeAll
    static void open() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    void checkCodeTest() {
        given()
        .when()
                .get("https://reqres.in/")
        .then()
                .statusCode(200);
    }

    @Test
    void userListTest() {
        given()
        .when()
                .get("/api/users?page=2")
        .then()
                .log().body()
                .body("support.text",
                         is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void successfulLoginTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
        .when()
                .post("/api/login")
        .then()
                .statusCode(200)
                .log().body()
                .body("token", is(notNullValue()));
    }

    @Test
    void patchUserTest() {
       Response response = given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"zion resident\" }")
        .when()
                .patch("/api/users/2")
        .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        System.out.println(response);

    }

    @Test
    void deleteUserTest() {
        given()
        .when()
                .delete("/api/users/2")
        .then()
                .statusCode(204);
    }
}





