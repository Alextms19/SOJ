package jsonClasses;

import dataStructures.Driver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONEditProfile {
    public static void writeDriver(ArrayList<Driver> soferi){
        JSONArray list = new JSONArray();
        Iterator<Driver> it=soferi.iterator();
        while(it.hasNext()){
            Driver aux=it.next();
            JSONObject obj = new JSONObject();
            obj.put("username",aux.getUsername());
            obj.put("password",aux.getPassword());
            obj.put("CNP",aux.getCNP());
            obj.put("NumarInmatriculare",aux.getNumarInmatriculare());
            obj.put("Masina", aux.getMasina());
            JSONObject obj1 = new JSONObject();
            obj1.put("customer :",obj);
            list.add(obj1);
        }
        try {
            FileWriter file = new FileWriter("src/drivers.json");
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Driver> getDriver(){
        JSONArray list=JSONFile.readFromFiles("src/drivers.json");
        ArrayList<Driver> soferi=new ArrayList<Driver>();
        Iterator<JSONObject> it=list.iterator();
        while(it.hasNext()) {
            JSONObject obj = it.next();
            JSONObject objInt = (JSONObject) obj.get("customer :");
            Driver s=new Driver((String)objInt.get("username"),(String)objInt.get("password"));
            s.setMasina((String)objInt.get("Masina"));
            s.setCNP((String)objInt.get("CNP"));
            s.setNumarInmatriculare((String)objInt.get("NumarInmatriculare"));
            soferi.add(s);
        }
        return soferi;
    }

}
