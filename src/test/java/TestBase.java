import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;


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




