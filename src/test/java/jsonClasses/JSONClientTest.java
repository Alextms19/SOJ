package jsonClasses;

import dataStructures.Client;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JSONClientTest {

    @Test
    public void getClient() throws Exception {
        assertTrue(JSONClient.getClient() instanceof ArrayList);
    }

    @Test
    public void findClient() throws Exception {
        Client tmp = new Client("da","nu");//testare exceptie
        assertEquals(null,JSONClient.findClient(tmp.toString()));
        if(JSONClient.getClient().get(0) != null) {
            Client cl = JSONClient.getClient().get(0);
            assertEquals(JSONClient.findClient(cl.getUsername()),cl);
        }
    }

    @Test
    public void testConstructor() throws Exception {
        JSONClient jc = new JSONClient();
        assertTrue(jc instanceof  JSONClient);
    }
}