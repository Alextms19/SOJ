package jsonClasses;

import dataStructures.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONClient {
    public static List<Client> getClient(){
        JSONArray list=JSONFile.readFromFiles("src/main/resources/customers.json");
        List<Client> clients = new ArrayList<>();
        Iterator<JSONObject> it = list.iterator();
        while(it.hasNext()) {
            JSONObject obj = it.next();
            JSONObject objInt = (JSONObject) obj.get("customer :");
            Client c = new Client((String)objInt.get("username"),(String)objInt.get("password"));
            c.setAddress((String)objInt.get("Adresa"));

                c.setCNP((String)objInt.get("CNP"));
            c.setPhoneNumber((String)objInt.get("Telefon"));
            clients.add(c);
        }
        return clients;
    }
    public static Client findClient(String userClient){
        for(Client tmp:getClient())
            if(tmp.getUsername().equals(userClient))
                return tmp;
        return null;
    }
}
