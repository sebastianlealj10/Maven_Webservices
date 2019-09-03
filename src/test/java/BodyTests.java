import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class BodyTests extends TestBase {

    @Test
    public void test_getUserBody() {
        given().
                when().contentType(ContentType.JSON).
                get("12").
                then().
                assertThat().
                body("email", equalTo("sebas@gmail.com"));
    }


}
