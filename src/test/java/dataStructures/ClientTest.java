package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class ClientTest {

    private Client client;
    private Client copy;
    private String string;
    private final String user = "ion";
    private final String parola = "parole";
    private final String CNP = "123";
    private final String adresa = "adresa";
    private final String altaAdresa = "altaAdresa";
    private final String telefon = "0123";
    private final String altTelefon = "01235";

    @Before
    public void initializeClient() throws Exception {
        client = new Client(user,parola);
        copy = new Client(user,parola);
        string = "Client { " + user + " } ";
        client.setCNP(CNP);
        client.setAddress(adresa);
        client.setPhoneNumber(telefon);
    }

    @Test
    public void getUsername() throws Exception {
        assertEquals(client.getUsername(),user);
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals(client.getPassword(),parola);
    }

    @Test
    public void getCNP() throws Exception {
        assertEquals(client.getCNP(),CNP);
    }

    @Test
    public void setTelefon() throws Exception {
        client.setPhoneNumber(altTelefon);
        assertNotEquals(client.getPhoneNumber(), telefon);
    }

    @Test
    public void setAdresa() throws Exception {
        client.setAddress(altaAdresa);
        assertNotEquals(client.getPhoneNumber(),adresa);
    }

    @Test
    public void getAdresa() throws Exception {
        assertEquals(client.getAddress(),adresa);
    }

    @Test
    public void getTelefon() throws Exception {
        assertEquals(client.getPhoneNumber(),telefon);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(client.toString(),string);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(client,copy);
        assertNotEquals(client,null);
    }

}