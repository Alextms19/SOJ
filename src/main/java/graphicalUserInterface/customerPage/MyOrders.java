package graphicalUserInterface.customerPage;

import parser.Parser;
import dataStructures.Client;
import dataStructures.CompletedOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MyOrders {
    private Client client;
    private Object[][] campuriComanda;
    private static List<CompletedOrder> comandaDeAfisat;
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private boolean flag,flag0,flag1;
    private JButton btnInapoi;

    public JButton getBtnInapoi(){
        return btnInapoi;
    }

    public boolean isFlag(){
        return flag;
    }

    public boolean isFlag0(){
        return flag0;
    }

    public boolean isFlag1(){
        return flag1;
    }

    public MyOrders(Client client) {
        this.client = client;
        flag = true;
        comandaDeAfisat = Parser.getEfectuate("src/main/resources/completedOrders.xml");
        if (comandaDeAfisat != null) {
            int i = 0,size = 0;
            flag0 = true;
            for (CompletedOrder tmp : comandaDeAfisat) {
                if (tmp.getClient().equals(client)) {
                    ++size;
                }
            }
            campuriComanda = new Object[size][5];
            for (CompletedOrder tmp : comandaDeAfisat) {
                if (tmp.getClient().equals(client)) {
                    campuriComanda[i][0] = tmp.getOrder().getLocationTo();
                    campuriComanda[i][1] = String.format("%.2f", tmp.getDistanceInKm());
                    campuriComanda[i][2] = NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format(tmp.getPriceInRON());
                    campuriComanda[i][3] = tmp.getDriver().getUsername();
                    campuriComanda[i++][4] = tmp.getReview();
                }
            }

            frame = new JFrame("Comenzile mele");
            frame.getContentPane().setBackground(SystemColor.activeCaption);
            frame.setBounds(100, 100, 700, 400);
            frame.getContentPane().setLayout(null);

            JLabel lblComenzileMele = new JLabel("Comenzile Mele");
            lblComenzileMele.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
            lblComenzileMele.setBounds(264, 16, 158, 45);
            frame.getContentPane().add(lblComenzileMele);


            btnInapoi = new JButton("Inapoi");
            btnInapoi.setBounds(275, 265, 115, 29);
            btnInapoi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    flag1 = true;
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
                            "Destinatie", "Distanta", "Pret", "Sofer","Recenzie"
                    }
            ));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(frame, "Leave" +
                            " ?", "Confirmare iesire :", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION)
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    else
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            });
            frame.setVisible(true);
        }
    }
}