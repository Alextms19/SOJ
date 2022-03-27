package graphicalUserInterface.customerPage;

import dataStructures.Client;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyOrders {
    private Client client;
    private Object[][] campuriComanda;
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton btnInapoi;

    public JButton getBtnInapoi(){
        return btnInapoi;
    }

    public MyOrders(Client client) {
            this.client = client;
            frame = new JFrame("My Orders");
            frame.getContentPane().setBackground(SystemColor.activeCaption);
            frame.setBounds(100, 100, 700, 400);
            frame.getContentPane().setLayout(null);

            JLabel lblComenzileMele = new JLabel("My Orders");
            lblComenzileMele.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
            lblComenzileMele.setBounds(264, 16, 158, 45);
            frame.getContentPane().add(lblComenzileMele);


            btnInapoi = new JButton("Back");
            btnInapoi.setBounds(275, 265, 115, 29);
            btnInapoi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    CustomerGUI.afiseaza();
                }
            });
            frame.getContentPane().add(btnInapoi);

            scrollPane = new JScrollPane();
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(15, 64, 648, 177);
            frame.getContentPane().add(scrollPane);

            table = new JTable();
            table.setBackground(Color.LIGHT_GRAY);
            scrollPane.setViewportView(table);
            table.setModel(new DefaultTableModel(
                    campuriComanda,
                    new String[]{
                            "Destination", "Distance", "Price", "Driver","Review"
                    }
            ));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(frame, "Leave" +
                            " ?", "Confirma Exit :", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION)
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    else
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            });
            frame.setVisible(true);
        }
    }