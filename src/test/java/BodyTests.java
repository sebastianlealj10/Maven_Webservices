import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers.*;



public class BodyTests extends TestBase {

        @Test
            public void test_getUserBody() {
            given().
                    when().contentType(ContentType.JSON).
                    get("12").
                    then().
                    assertThat().
                    body("email",equalTo("sebas@gmail.com"));
    }


}
