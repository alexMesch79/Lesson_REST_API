import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTestSimple {
    @DisplayName("Проверка запроса GET LIST USERS, а так же атрибута total")
    @Test
    void checkListUserAttributeTotal() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @DisplayName("Проверка запроса GET LIST USERS и атрибута per_page")
    @Test
    void checkListUserAttributePerPage() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("per_page", is(6));
    }

    @DisplayName("Проверка запроса GET LIST <RESOURCE> и атрибута total_pages")
    @Test
    void checkListResourceAttributeTotalPages() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @DisplayName("Проверка запроса GET SINGLE USER NOT FOUND")
    @Test
    void checkSingleUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @DisplayName("Проверка запроса POST CREATE и атрибутов name и job")
    @Test
    void checkPostCreate(){
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .log().status()
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .statusCode(201);
    }

    @DisplayName("Проверка запроса PUT UPDATE и атрибутов name и job")
    @Test
    void checkPutUpdate(){
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .contentType(JSON)
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .log().status()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .statusCode(200);
    }

    @DisplayName("Проверка запроса GET DELAYED RESPONSE и атрибута page")
    @Test
    void checkDelayedResponse() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("page", is(1));
    }
}
