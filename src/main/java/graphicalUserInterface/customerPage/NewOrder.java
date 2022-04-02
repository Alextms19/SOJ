package graphicalUserInterface.customerPage;

import dataStructures.Client;
import dataStructures.Order;
import parser.Parser;


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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewOrder {
    private Client client;
    private static JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private String locatie;
    private String destinatie;
    private List<Order> orders = new ArrayList<>();

    private boolean b3=false;
    private JButton btnContinuare;
    private JButton btnInapoi;

    public NewOrder(Client c) {
        client = c;
        initialize();
    }

    public static void afiseaza(){
        frame.setVisible(true);
        CustomerGUI.ascunde();
    }
    public static void ascunde(){
        frame.setVisible(false);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JTextField getTextField_1() {
        return textField_1;
    }

    public JButton getBtnContinuare() {
        return btnContinuare;
    }

    public JButton getBtnInapoi() {
        return btnInapoi;
    }

    public boolean isB3() {
        return b3;
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 140, 0));
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        JLabel lblComandaNoua = new JLabel("Comanda Noua");
        lblComandaNoua.setForeground(new Color(255,255,255));
        lblComandaNoua.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        lblComandaNoua.setBounds(235, 10, 233, 29);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 128));
        panel.setBounds(0, 0, 686, 50);
        frame.getContentPane().add(panel);
        panel.add(lblComandaNoua);
        panel.setLayout(null);


        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(300, 60, 362, 250);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 0, 128));
        panel_2.setBounds(10, 10, 342, 230);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblLocatieCurenta = new JLabel("Locatie curenta");
        lblLocatieCurenta.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblLocatieCurenta.setForeground(new Color(255, 255, 255));
        lblLocatieCurenta.setBounds(23, 21, 146, 29);
        panel_2.add(lblLocatieCurenta);

        JLabel lblDestinatie = new JLabel("Destinatie");
        lblDestinatie.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblDestinatie.setForeground(new Color(255, 255, 255));
        lblDestinatie.setBounds(26, 81, 122, 20);
        panel_2.add(lblDestinatie);

        textField = new JTextField();
        textField.setBounds(177, 28, 136, 19);
        panel_2.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(177, 84, 136, 19);
        panel_2.add(textField_1);
        textField_1.setColumns(10);


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/drive.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(250, 250,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(40, 60, 250, 250);
        frame.getContentPane().add(panel_3);
        panel_3.setLayout(null);
        JLabel aux=new JLabel();
        aux.setBounds(10, 10, 230, 230);
        panel_3.add(aux);
        aux.setIcon(imageIcon);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Leave" +
                        " ?","Confirmare iesire :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        btnContinuare = new JButton("Continuare");
        btnContinuare.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnContinuare.setBounds(181, 157, 132, 21);
        btnInapoi = new JButton("Inapoi");
        btnInapoi.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnInapoi.setBounds(181, 199, 132, 21);

        btnInapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b3=true;
                NewOrder.ascunde();
                CustomerGUI.afiseaza();
                textField.setText("");
                textField_1.setText("");

            }
        });

        btnContinuare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                locatie = textField.getText();
                destinatie = textField_1.getText();
                Order order = new Order(now, client, locatie, destinatie);
                orders.add(order);
                Parser.createOrdersXML(order, "src/main/resources/data.xml");
                frame.setVisible(false);
                new CommandGUI(client);
                textField.setText("");
                textField_1.setText("");
            }
        });

        panel_2.add(btnInapoi);
        panel_2.add(btnContinuare);
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
