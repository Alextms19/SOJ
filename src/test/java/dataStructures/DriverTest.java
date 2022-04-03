package dataStructures;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DriverTest {
    private static String name="ana";
    private static String password="maxim";
    private static String numarInmatriculare="TM10ABC";
    private static String masina="BMW";
    private static Driver sofer=new Driver(name, password);

    @BeforeClass
    public static void beforeClass() throws Exception {
        sofer.setCarModel(masina);
        sofer.setLicencePlate(numarInmatriculare);
    }

    @Test
    public void testEgalitate() {
        assertEquals(true,sofer.equals(new Driver("ana","maxim")));
    }

    @Test
    public void testNeegalitate() {
        assertEquals(false,sofer.equals(new String("maria")));
    }

    @Test
    public void testToString() {
        String st="Sofer{" +
                "numarInmatriculare='" + numarInmatriculare + '\'' +
                ", masina='" + masina + '\'' +
                '}';;
        assertEquals(st,sofer.toString());
    }

    @Test
    public void testGetUsername() {
        String nume=sofer.getUsername();
        assertEquals(name,nume);
    }

    @Test
    public void testGetPassword(){
        String parola=sofer.getPassword();
        assertEquals(password, parola);
    }

    @Test
    public void testGetNumarInmatriculare(){
        String nrInmatriculare=sofer.getLicencePlate();
        assertEquals(numarInmatriculare,nrInmatriculare);
    }

    @Test
    public void testGetMasina(){
        String car=sofer.getCarModel();
        assertEquals(masina,car);
    }

    @Test
    public void testChangeCar() {
        String newCar="Mercedes";
        sofer.setCarModel(newCar);
        assertEquals(newCar,sofer.getCarModel());
        masina=newCar;
    }

    @Test
    public void testChangeNumarInmatriculare() {
        String newNr="AR10CBA";
        sofer.setLicencePlate(newNr);
        assertEquals(newNr,sofer.getLicencePlate());
        numarInmatriculare=newNr;
    }

}