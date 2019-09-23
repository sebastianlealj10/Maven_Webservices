import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Responses extends TestBase {

    private static final Logger logger = LogManager.getLogger("Requests");

    @Test
    public void test_getStatusOk() {

        given().
                when().
                get("1").
                then().
                assertThat().
                statusCode(200).
                body("$",hasKey("id")).
                body("$",hasKey("email")).
                body("$",hasKey("firstName")).
                body("$",hasKey("lastName"));
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
    public void test_getStatusUserBadRequest() {

        given().
                when().
                get("/string").
                then().
                assertThat().
                statusCode(400);
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "PostRequest")
    public void test_postStatus(String email,String firstName, String lastName) {

        JsonObject Request = new JsonObject();
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                post().
                then().
                assertThat().
                statusCode(201);
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "PostRequest")
    public void test_postStatusNotModified(String email,String firstName, String lastName) {

        JsonObject Request = new JsonObject();
        Request.addProperty("id",20);
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                post().
                then().
                assertThat().
                statusCode(304);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PostRequest")
    public void test_postStatusNotAllowed(String email,String firstName, String lastName) {

        JsonObject Request = new JsonObject();
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);
        Request.addProperty("address","45 Avenue");

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                post("/100").
                then().
                assertThat().
                statusCode(405);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PutRequest")
    public void test_putStatus(int id, String email, String firstName, String lastName) {

        JsonObject Request = new JsonObject();
        Request.addProperty("id",id);
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                put().
                then().
                assertThat().
                statusCode(201);
        given().
                when().
                get("/"+ id).
                then().
                assertThat().
                statusCode(200).
                body("id",equalTo(id)).
                body("email",equalTo(email)).
                body("firstName",equalTo(firstName)).
                body("lastName",equalTo(lastName));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PutRequest")
    public void test_putStatusNotAllowed(int id, String email, String firstName, String lastName) {
        JsonObject Request = new JsonObject();
        Request.addProperty("id","20");
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                put("/"+ id).
                then().
                assertThat().
                statusCode(405);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PutRequest")
    public void test_putStatusNotModified(int id, String email, String firstName, String lastName) {
        JsonObject Request = new JsonObject();
        Request.addProperty("i","20");
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                put().
                then().
                assertThat().
                statusCode(304);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "PutRequest")
    public void test_putStatusBadRequest(int id, String email, String firstName, String lastName) {

        JsonObject Request = new JsonObject();
        Request.addProperty("id","Hola");
        Request.addProperty("email",email);
        Request.addProperty("firstName",firstName);
        Request.addProperty("lastName",lastName);

        given().
                when().
                contentType(ContentType.JSON).
                body(Request).
                put().
                then().
                assertThat().
                statusCode(400);
    }

    @Test
    public void test_deleteStatusNotFound() {

        given().
                when().
                delete("/20").
                then().
                assertThat().
                statusCode(304);
    }

    @Test
    public void test_deleteStatus() {

        given().
                when().
                delete("/20").
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
