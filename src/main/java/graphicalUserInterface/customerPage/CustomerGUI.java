package graphicalUserInterface.customerPage;


import dataStructures.Client;

import graphicalUserInterface.AuthentificationGUI;

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

public class CustomerGUI {
    private Client client;
    private static JFrame f;
    private JButton b1,b2,b3,b4;
    private JLabel l1,l2;


    public CustomerGUI(Client c){
        client=c;
        f = new JFrame("Customer's page");
        f.getContentPane().setBackground(new Color(44, 224, 174));
        b1 = new JButton("Comenzile mele");
        b2 = new JButton("Comanda noua");
        b3 = new JButton("Scrieti recenzie");
        b4 = new JButton("Inapoi");
        l1 = new JLabel("Customer's page");
        l1.setBounds(225, 0, 300, 50);
        l1.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 25));
        l1.setForeground(Color.blue);
        b1.setBounds(350,200,210,30);
        b2.setBounds(350,150,210,30);
        b3.setBounds(350,100,210,30);
        b4.setBounds(350,250,210,30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                AuthentificationGUI.afiseaza();
            }
        });
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/drive.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(250, 250,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        l2 = new JLabel(icon);
        l2.setBounds(40, 60, 250, 250);
        f.pack();
        f.add(l1);
        f.add(l2);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(f,"Leave" +
                        " ?","Confirmare iesire :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        f.setSize(700,400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}