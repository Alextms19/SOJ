package graphicalUserInterface.customerPage;

import dataStructures.Client;
import dataStructures.Order;
import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class CommandGUI {
    private static final int TIME_VISIBLE = 600_000; //10 minutes
    private JFrame jf;
    private Order order;
    public CommandGUI(Client client){

        List<Order> orders = Parser.getOrders("src/main/resources/data.xml");
        for (Order o: orders) {
            if (o.getClient().getUsername().equals(client.getUsername()))
                order = o;
        }

        jf = new JFrame("Pending order");
        jf.getContentPane().setBackground(new Color(127, 255, 212));
        JLabel lblComandaInAstepatre = new JLabel("Pending order");
        lblComandaInAstepatre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
        lblComandaInAstepatre.setBounds(189, 116, 335, 72);
        jf.add(lblComandaInAstepatre);
        JButton btnInapoi = new JButton("Back");
        btnInapoi.setBounds(288, 275, 115, 44);

        new Timer(TIME_VISIBLE, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jf.dispose();
                Parser.deleteOrder(order,"src/main/resources/data.xml");
                if(jf.isActive()||CustomerGUI.activ())
                    NewOrder.afiseaza();
            }
        }).start();

        btnInapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                CustomerGUI.afiseaza();
            }
        });
        jf.getContentPane().add(btnInapoi);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(jf,"Leave" +
                        " ?","Confirm Exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        jf.setSize(700,400);
        jf.setLayout(null);
        jf.setVisible(true);
    }

    public static boolean checkTime(Order c){
        if(willBeDeleted(c)){
            parser.Parser.deleteOrder(c,"src/main/resources/data.xml");
            return false;
        }
        return true;
    }

    private static boolean willBeDeleted(Order c) {
        if(Duration.between(c.getOrderDateTime(), LocalDateTime.now()).getSeconds() < 600){
            return false;
        }
        return true;
    }

}