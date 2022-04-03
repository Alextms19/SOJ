package jsonClasses;

import dataStructures.Driver;
import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JSONEditProfileTest {
    private JSONEditProfile e=new JSONEditProfile();

    @Test
    public void testWriteDriver() {
        ArrayList<Driver> soferi=new ArrayList<Driver>();
        soferi.add(new Driver("catalin","botean"));
        soferi.add(new Driver("cosmin","marsavina"));
        JSONEditProfile.writeDriver("src/test/resources/drivers.json",soferi);
        JSONArray list=JSONFile.readFromFiles("src/test/resources/drivers.json");
        assertEquals(2,list.size());
    }

    ExpectedException rule=ExpectedException.none();
    @Test
    public void testWriteIOException() {
        ArrayList <Driver> soferi=new ArrayList<Driver>();
        rule.expect(IOException.class);
        JSONEditProfile.writeDriver("src/test/resources/files.json",soferi);
    }

    @Test
    public void testGetDriver() {
        ArrayList <Driver> soferi=JSONEditProfile.getDriver();
        assertEquals(4,soferi.size());
    }

    @Test
    public void testGetSofer(){
        Driver s=JSONEditProfile.getSofer("ana");
        assertEquals(true,s.equals(new Driver("ana","maxim")));
    }

    ExpectedException ruleNull=ExpectedException.none();
    @Test
    public void testGetNull(){
        ruleNull.expect(NullPointerException.class);
        Driver s=JSONEditProfile.getSofer("catalin");
    }
}