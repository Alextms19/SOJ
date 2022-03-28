package jsonClasses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static jsonClasses.JSONFile.encodePassword;

public class JSONCreate {
    private static void createCustomerFile() {
        JSONObject obj1 = new JSONObject();
        obj1.put("username", "maria");
        obj1.put("password", encodePassword("maria","popescu"));
        obj1.put("CNP","1234567891012");
        obj1.put("Adresa","alba");
        obj1.put("Telefon","0712345678");
        JSONObject obj6 = new JSONObject();
        obj6.put("customer :",obj1);

        JSONObject obj2 = new JSONObject();
        obj2.put("username", "paul");
        obj2.put("password", encodePassword("paul","ionescu"));
        obj2.put("CNP","1244567891012");
        obj2.put("Adresa","Timisoara");
        obj2.put("Telefon","0722345678");
        JSONObject obj7 = new JSONObject();
        obj7.put("customer :",obj2);

        JSONObject obj3 = new JSONObject();
        obj3.put("username", "lidia");
        obj3.put("password", encodePassword("lidia","toma"));
        obj3.put("CNP","1235567891012");
        obj3.put("Adresa","Lugoj");
        obj3.put("Telefon","0713345678");
        JSONObject obj8 = new JSONObject();
        obj8.put("customer :",obj3);

        JSONObject obj4 = new JSONObject();
        obj4.put("username", "cosmin");
        obj4.put("password", encodePassword("cosmin","marius"));
        obj4.put("CNP","1236567891012");
        obj4.put("Adresa","Timisoara");
        obj4.put("Telefon","0715345678");
        JSONObject obj9 = new JSONObject();
        obj9.put("customer :",obj4);

        JSONObject obj5 = new JSONObject();
        obj5.put("username", "diana");
        obj5.put("password", encodePassword("diana","ionescu"));
        obj5.put("CNP","1334567891012");
        obj5.put("Adresa","Cugir");
        obj5.put("Telefon","0792345678");
        JSONObject obj10 = new JSONObject();
        obj10.put("customer :",obj5);

        JSONArray list = new JSONArray();
        list.add(obj6);
        list.add(obj7);
        list.add(obj8);
        list.add(obj9);
        list.add(obj10);
        try {
            FileWriter file = new FileWriter("src/customers.json");
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void createDriverFile() {
        JSONObject obj1 = new JSONObject();
        obj1.put("username", "alex");
        obj1.put("password", encodePassword("alex","pop"));
        obj1.put("CNP","1234577891012");
        obj1.put("NumarInmatriculare","TM10ABC");
        obj1.put("Masina", "Audi");
        JSONObject obj6 = new JSONObject();
        obj6.put("customer :",obj1);

        JSONObject obj2 = new JSONObject();
        obj2.put("username", "andreea");
        obj2.put("password", encodePassword("andreea","mihaela"));
        obj2.put("CNP","1234567881012");
        obj2.put("NumarInmatriculare","TM11ABC");
        obj2.put("Masina", "BMW");
        JSONObject obj7 = new JSONObject();
        obj7.put("customer :",obj2);

        JSONObject obj3 = new JSONObject();
        obj3.put("username", "ion");
        obj3.put("password", encodePassword("ion","popescu"));
        obj3.put("CNP","1234567999012");
        obj3.put("NumarInmatriculare","TM12ABC");
        obj3.put("Masina", "Opel");
        JSONObject obj8 = new JSONObject();
        obj8.put("customer :",obj3);

        JSONObject obj4 = new JSONObject();
        obj4.put("username", "ana");
        obj4.put("password", encodePassword("ana","maxim"));
        obj4.put("CNP","1234567791012");
        obj4.put("NumarInmatriculare","TM13ABC");
        obj4.put("Masina", "Mercedes");
        JSONObject obj9 = new JSONObject();
        obj9.put("customer :",obj4);

        JSONArray list = new JSONArray();
        list.add(obj6);
        list.add(obj7);
        list.add(obj8);
        list.add(obj9);
        try {
            FileWriter file = new FileWriter("src/drivers.json");
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
