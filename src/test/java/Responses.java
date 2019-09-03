import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Responses extends TestBase {


    @Test
    public void test_getStatusOk() {
        given().
                when().
                get().
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_getStatusNotFound() {
        given().
                when().
                get("/100").
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    public void test_postStatus() {

        String bodyString = "{\"email\":\"sebas@gmail.com\",\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                post().
                then().
                assertThat().
                statusCode(201);
    }

    @Test
    public void test_postStatusBadRequest() {

        String bodyString = "{\"email\":\"sebas@gmail.com\"\"id\":\"2\",\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                post().
                then().
                assertThat().
                statusCode(400);
    }

    @Test
    public void test_postStatusNotAllowed() {

        String bodyString = "{\"email\":\"sebas@gmail.com\",\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                post("/100").
                then().
                assertThat().
                statusCode(405);
    }

    @Test
    public void test_putStatusNotAllowed() {

        String bodyString = "{\"email\":\"sebas@gmail.com\", \"id\":\"4\" ,\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                put("/10").
                then().
                assertThat().
                statusCode(405);
    }

    @Test
    public void test_putStatus() {

        String bodyString = "{\"email\":\"sebas@gmail.com\", \"id\":\"4\" ,\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                put().
                then().
                assertThat().
                statusCode(201);
    }

    @Test
    public void test_putStatusNotModified() {

        String bodyString = "{\"email\":\"sebas@gmail.com\", \"i\":\"4\" ,\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                put().
                then().
                assertThat().
                statusCode(304);
    }

    @Test
    public void test_putStatusBadRequest() {

        String bodyString = "{\"email\":\"sebas@gmail.com\", \"id\":\"hola\" ,\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                put().
                then().
                assertThat().
                statusCode(400);
    }

    @Test
    public void test_getStatusUserBadRequest() {

        given().
                when().
                get("/string").
                then().
                assertThat().
                statusCode(400);
    }

    @Test
    public void test_deleteStatusNotFound() {

        given().
                when().
                delete("/11").
                then().
                assertThat().
                statusCode(304);
    }

    @Test
    public void test_deleteStatus() {

        given().
                when().
                delete("/30").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_deleteStatusNotModified() {

        given().
                when().
                delete("/300").
                then().
                assertThat().
                statusCode(304);
    }

    @Test
    public void test_patchStatus() {
        String bodyString = "{\"email\":\"sebas_patch@gmail.com\"}";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                patch("/10").
                then().
                assertThat().
                statusCode(201);
    }

    @Test
    public void test_patchStatusNotModified() {
        String bodyString = "{\"email\":\"sebas_patch@gmail.com\"}";

        given().
                when().
                contentType(ContentType.JSON).
                body(bodyString).
                patch("/300").
                then().
                assertThat().
                statusCode(304);
    }

}
