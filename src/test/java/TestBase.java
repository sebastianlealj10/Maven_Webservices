import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.util.logging.Logger;


public class TestBase {

        @BeforeClass
        public static void setUp() {
                String my_port = System.getProperty("server.port");
                int new_port = 0;
                if (my_port == null) {
                        new_port = 8080;
                }
                port = new_port;
                String my_base_Path = System.getProperty("server.base");
                if (my_base_Path == null) {
                        my_base_Path = "/api/users";
                }
                basePath = my_base_Path;
                String my_base_Host = System.getProperty("server.host");
                if (my_base_Host == null) {
                        my_base_Host = "http://localhost";
                }
                baseURI = my_base_Host;
        }
}




