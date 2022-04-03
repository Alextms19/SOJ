package jsonClasses;

import org.json.simple.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSONCreateTest {
    JSONCreate jc=new JSONCreate();
    JSONFile jf =new JSONFile();

    public JSONCreateTest() throws Exception {
    }

    @Test
    public void testCreateCustomer() {
        JSONCreate.createCustomerFile("src/test/resources/customers.json");
        JSONArray list=JSONFile.readFromFiles("src/test/resources/customers.json");
        assertEquals(5,list.size());
    }

    @Test
    public void testCreateDriver() {
        JSONCreate.createDriverFile("src/test/resources/drivers.json");
        JSONArray list=JSONFile.readFromFiles("src/test/resources/drivers.json");
        assertEquals(4,list.size());
    }

    @Test
    public void testSecure() {
        JSONCreate.secure();
        JSONArray list=JSONFile.readFromFiles("src/main/resources/customers.json");
        JSONArray list2=JSONFile.readFromFiles("src/main/resources/drivers.json");
        assertEquals(9,list.size()+list2.size());
    }
}