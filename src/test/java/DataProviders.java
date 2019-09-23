import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name="PostRequest")
    public static Object[][] postData() {
        Object[][] data = new Object[1][3];
        data[0][0]="sebas@test.com";
        data[0][1]="Sebastian";
        data[0][2]="Leal";
        return data;
    }

    @DataProvider(name="PutRequest")
    public static Object[][] putData() {
        Object[][] data = new Object[1][4];
        data[0][0]= 21;
        data[0][1]="juan@test.com";
        data[0][2]="Juan";
        data[0][3]="Leal";
        return data;
    }
}
