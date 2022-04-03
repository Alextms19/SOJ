package graphicalUserInterface;

import jsonClasses.JSONFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AuthentificationGUITest {

    private AuthentificationGUI agui;

    @Before
    public void initialize() throws Exception {
        JSONFile jf = new JSONFile();
        agui = new AuthentificationGUI();
    }

    @Test
    public void testFlagB() throws Exception {
        agui.setFlagB(true);
        assertEquals(agui.isFlagB(),true);
    }

    @Test
    public void testFlagVerificaCredentiale1() throws Exception {
        agui.setFlagVerificaCredentiale1(true);
        assertEquals(agui.isFlagVerificaCredentiale1(),true);
    }

    @Test
    public void testFlagVerificaCredentiale2() throws Exception {
        agui.setFlagVerificaCredentiale2(true);
        assertEquals(agui.isFlagVerificaCredentiale2(),true);
    }

    @Test
    public void testFlagFunction() throws Exception {
        agui.setFlagFunction(true);
        assertEquals(agui.isFlagFunction(),false);
    }

    @Test
    public void resetFields() throws Exception {
        agui.resetFields();
        assertEquals(agui.getT1().getText(),"");
        assertEquals(agui.getT2().getText(),"");
    }

    @Test
    public void afiseaza() throws Exception {
        agui.afiseaza();
        assertEquals(agui.isFlagFunction(),true);
    }

    @Test
    public void testAutentificationGUI1() throws Exception {
        agui.getT1().setText("lidia");
        agui.getT2().setText("toma");
        agui.getB().doClick();
        assertEquals(agui.isFlagB(),true);
        assertEquals(agui.isFlagVerificaCredentiale1(),true);
        assertNotEquals(agui.isFlagVerificaCredentiale2(),true);
    }

    @Test
    public void testAutentificationGUI2() throws Exception {
        agui.getT1().setText("ana");
        agui.getT2().setText("maxim");
        agui.getB().doClick();
        assertEquals(agui.isFlagB(),true);
        assertEquals(agui.isFlagVerificaCredentiale1(),false);
        assertNotEquals(agui.isFlagVerificaCredentiale2(),false);
    }

    @Test
    public void testAtutntificationGUI3() throws Exception {
        agui.getT1().setText("");
        agui.getT2().setText("");
        assertEquals(agui.isFlagVerificaCredentiale1(),false);
        assertEquals(agui.isFlagVerificaCredentiale1(),false);
    }
}