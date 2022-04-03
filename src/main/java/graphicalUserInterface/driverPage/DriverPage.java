package graphicalUserInterface.driverPage;

import dataStructures.Driver;
import graphicalUserInterface.AuthentificationGUI;
import jsonClasses.JSONEditProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class DriverPage {

    private static JFrame frame;
    private static Driver sofer;
    private JButton edit;
    private JButton list;
    private JButton add;
    private JButton inapoi;
    private boolean b1,b2,b3;

    public static Driver getDriver(){
        return sofer;
    }

    public DriverPage(Driver s)
    {
        b1=false;
        b2=false;
        b3=false;
        ArrayList<Driver> soferi= JSONEditProfile.getDriver();
        Iterator<Driver> it=soferi.iterator();
        while(it.hasNext()) {
            Driver aux = it.next();
            if (aux.getUsername().equals(s.getUsername())) {
                sofer = aux;
            }
        }
        initialize();
    }

    public static void afiseaza(){
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 650, 500);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Leave" +
                        " ?","Confirm Exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        frame.getContentPane().setBackground(new Color(75, 180, 240));


        edit=new JButton("Edit profile");
        edit.setForeground(new Color(0, 0, 128));
        edit.setFont(new Font("Times New Roman", Font.BOLD, 16));
        edit.setBounds(10, 35, 198, 34);
        list=new JButton("  My Orders");
        list.setForeground(new Color(0, 0, 128));
        list.setFont(new Font("Times New Roman", Font.BOLD, 16));
        list.setBounds(10, 106, 198, 34);
        add=new JButton("Take an Order");
        add.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add.setForeground(new Color(0, 0, 128));
        add.setBounds(10, 180, 198, 34);
        inapoi=new JButton("   Back      ");
        inapoi.setForeground(new Color(0, 0, 128));
        inapoi.setFont(new Font("Times New Roman", Font.BOLD, 16));
        inapoi.setBounds(10, 253, 198, 34);

        inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                b1=true;
                AuthentificationGUI.afiseaza();
            }
        });

        list.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                b2=true;
                new ViewOrders(sofer);
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                b3=true;
                    frame.setVisible(false);
                    parser.Parser.showXML();

            }
        });

        JPanel titlu=new JPanel();
        titlu.setBackground(new Color(0, 0, 128));
        titlu.setBounds(0, 0, 636, 56);
        titlu.setLayout(null);
        JLabel lblNewLabel = new JLabel("DRIVER'S PAGE");
        lblNewLabel.setBounds(209, 10, 217, 33);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlu.add(lblNewLabel);
        frame.getContentPane().add(titlu);

        JPanel jp=new JPanel();
        jp.setBackground(new Color(0, 0, 128));
        jp.setBounds(370, 87, 218, 329);
        jp.setLayout(null);
        jp.add(edit);
        jp.add(edit);
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                b3=true;
                new EditProfileGUI(sofer);
            }
        });
        jp.add(list);
        jp.add(add);
        jp.add(inapoi);

        JPanel west=new JPanel();
        west.setBackground(new Color(255, 255, 255));
        west.setBounds(80, 50, 260, 260);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/poza.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel aux=new JLabel();
        Image dimg = img.getScaledInstance(300, 300,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        aux.setBounds(60, 100, 250, 250);
        aux.setIcon(imageIcon);
        west.add(aux);
        frame.getContentPane().add(jp);
        frame.getContentPane().add(aux);

    }

    public static JFrame getFrame() {
        return frame;
    }

    public JButton getInapoi() {
        return inapoi;
    }

}