import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class StatusTests {

        @Test
        public void test_getStatus() {
                given().
                        when().
                        get("http://localhost:8080/api/users").
                        then().
                        assertThat().
                        statusCode(200);
        }

        @Test
        public void test_postStatus() {

                String bodyString = "{\"email\":\"sebas@gmail.com\",\"firstName\": \"sebastian\",\"lastName\": \"leal\" }";

                given().
                        when().contentType(ContentType.JSON).body(bodyString).
                        post("http://localhost:8080/api/users").
                        then().
                        assertThat().
                        statusCode(201);

        }
        @Test
        public void test_putStatus() {

                String bodyString = "{\"email\":\"sebas@gmail.com\",\"firstName\": \"sebas2\",\"id\":\"10\",\"lastName\": \"leal\" }";

                given().
                        when().contentType(ContentType.JSON).body(bodyString).
                        put("http://localhost:8080/api/users").
                        then().
                        assertThat().
                        statusCode(201);

        }
        @Test
        public void test_getUserStatus() {

                String bodyString = "{\"id\":\"1\"}";

                given().
                        when().contentType(ContentType.JSON).body(bodyString).
                        get("http://localhost:8080/api/users").
                        then().
                        assertThat().
                        statusCode(200);

        }
        @Test
        public void test_getDeleteStatus() {

                int delete = 11;
                given().
                        when().contentType(ContentType.JSON).
                        delete("http://localhost:8080/api/users/"+ delete).
                        then().
                        assertThat().
                        statusCode(200);

        }
        @Test
        public void test_patchStatus() {

                String bodyString = "{\"email\":\"sebas5@gmail.com\",\"firstName\": \"sebas5\",\"id\":\"10\",\"lastName\": \"leal\" }";

                given().
                        when().contentType(ContentType.JSON).body(bodyString).
                        put("http://localhost:8080/api/users").
                        then().
                        assertThat().
                        statusCode(201);

        }
}




