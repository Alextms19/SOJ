package graphicalUserInterface;

import dataStructures.Client;
import dataStructures.Driver;
import graphicalUserInterface.customerPage.CustomerGUI;
import graphicalUserInterface.driverPage.DriverPage;
import jsonClasses.JSONFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AuthentificationGUI  {
    private static JFrame f;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;//labels
    private JButton b;//button
    private JTextField t1;//camp nume
    private JPasswordField t2;//camp parola
    private static boolean flagFunction;
    private boolean flagVerificaCredentiale1;
    private boolean flagVerificaCredentiale2;

    public static boolean isFlagFunction() {
        return flagFunction;
    }

    public static void setFlagFunction(boolean flagFunction) {
        flagFunction = flagFunction;
    }

    public boolean isFlagVerificaCredentiale1() {
        return flagVerificaCredentiale1;
    }

    public void setFlagVerificaCredentiale1(boolean flagVerificaCredentiale1) {
        this.flagVerificaCredentiale1 = flagVerificaCredentiale1;
    }

    public boolean isFlagVerificaCredentiale2() {
        return flagVerificaCredentiale2;
    }

    public void setFlagVerificaCredentiale2(boolean flagVerificaCredentiale2) {
        this.flagVerificaCredentiale2 = flagVerificaCredentiale2;
    }

    public AuthentificationGUI() {

        f = new JFrame("registration");//frame
        f.getContentPane().setBackground(new Color(75, 180, 240));
        l1 = new JLabel("Name:");//eticheta indicatoare camp pentru nume complet
        l2 = new JLabel("Password:");//eticheta indicatoare camp pentru parola
        l3 = new JLabel("Welcome to Ride-Sharing app! ");//eticheta indicatoare titlu
        l4 = new JLabel(" Please fill in the blanks");//eticheta indrumare
        b = new JButton("Login");//button
        t1 = new JTextField();//camp nume
        t2 = new JPasswordField();//camp parola
        l3.setBounds(175, 0, 300, 50);
        l3.setFont(new Font("Bernard MT Condensed", Font.ITALIC, 25));
        l3.setForeground(Color.blue);
        l1.setBounds(405,60, 160,30);
        l1.setFont(new Font("Trebuchet MS", Font.BOLD,16));
        t1.setBounds(5, 5, 200, 30);
        t1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
        t1.setForeground(Color.blue);
        l2.setBounds(405,160, 100,30);
        l2.setFont(new Font("Trebuchet MS", Font.BOLD,16));
        t2.setBounds(5, 5, 200, 30);
        b.setBounds(450,270,110,30);//x axis, y axis, width, height
        l4.setBounds(100,50,500,30);
        l4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
        l4.setForeground(Color.blue);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = t1.getText();
                String s2 = t2.getText();
                Client c=new Client(s1,s2);
                Driver s=new Driver(s1,s2);
                resetFields();

                if(e.getSource() == b) {
                    if(JSONFile.verificaCredentiale("src/main/resources/customers.json",c)) {
                        f.setVisible(false);
                        flagVerificaCredentiale1 = true;
                        new CustomerGUI(c);
                    }
                    else if(JSONFile.verificaCredentiale("src/main/resources/drivers.json",s)) {
                        f.setVisible(false);
                        flagVerificaCredentiale2 = true;
                        new DriverPage(s);
                    }
                    else
                        JOptionPane.showMessageDialog(new JFrame(),
                                "You are not registered.",
                                "Login failed",
                                JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/uber.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(450, 250,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        l5 = new JLabel(icon);
        l5.setBounds(80, 80, 250, 250);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(138, 43, 226));
        panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(165, 3, 310, 51);
        f.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 10, 300, 35);
        panel_1.add(panel);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBackground(new Color(13, 24, 32));
        panel_2.setBounds(400,100, 210,40);
        f.getContentPane().add(panel_2);
        panel_2.add(t1);

        JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBackground(new Color(13, 24, 32));
        panel_4.setBounds(400,200, 210,40);
        f.getContentPane().add(panel_4);
        panel_4.add(t2);

        panel.add(l3);
        f.add(l1);
        f.add(l2);
        f.add(b);
        f.add(l4);
        f.add(l5);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(f,"Leave" +
                        " ?","Confirm Exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        f.setSize(700,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void resetFields(){
        t1.setText("");
        t2.setText("");
    }

    public static void afiseaza(){
        flagFunction = true;
        f.setVisible(true);
    }

    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AuthentificationGUI();
            }
        });
    }
}