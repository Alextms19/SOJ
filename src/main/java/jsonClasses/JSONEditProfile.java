package jsonClasses;

import dataStructures.Driver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONEditProfile {
    public static void writeDriver(String filename, ArrayList<Driver> soferi){
        JSONArray list = new JSONArray();
        Iterator<Driver> it=soferi.iterator();
        while(it.hasNext()){
            Driver aux=it.next();
            JSONObject obj = new JSONObject();
            obj.put("username",aux.getUsername());
            obj.put("password",aux.getPassword());
            obj.put("CNP",aux.getCNP());
            obj.put("NumarInmatriculare",aux.getLicencePlate());
            obj.put("Masina", aux.getCarModel());
            JSONObject obj1 = new JSONObject();
            obj1.put("customer :",obj);
            list.add(obj1);
        }
        try {
            FileWriter file = new FileWriter(filename);
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Driver> getDriver(){
        JSONArray list=JSONFile.readFromFiles("src/main/resources/drivers.json");
        ArrayList<Driver> soferi=new ArrayList<Driver>();
        Iterator<JSONObject> it=list.iterator();
        while(it.hasNext()) {
            JSONObject obj = it.next();
            JSONObject objInt = (JSONObject) obj.get("customer :");
            Driver s=new Driver((String)objInt.get("username"),(String)objInt.get("password"));
            s.setCarModel((String)objInt.get("Masina"));
            s.setCNP((String)objInt.get("CNP"));
            s.setLicencePlate((String)objInt.get("NumarInmatriculare"));
            soferi.add(s);
        }
        return soferi;
    }
    public static Driver getSofer(String user){
        ArrayList<Driver> listaSoferi = getDriver();
        for(Driver tmp: listaSoferi)
            if(tmp.getUsername().equals(user))
                return tmp;
        return null;
    }

}
