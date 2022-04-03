package graphicalUserInterface.driverPage;

import dataStructures.CompletedOrder;
import dataStructures.Order;
import graphicalUserInterface.customerPage.CommandGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class OrdersList {

    private JFrame frame;

    public JTextPane getTextPane() {
        return textPane;
    }

    private JTextPane textPane;
    private List<Order> orders;
    private JButton btnInapoi;
    private JButton lblNewLabel;
    private boolean flag1, flag2;

    public JButton getLblNewLabel() {
        return lblNewLabel;
    }

    public JButton getBtnInapoi() {
        return btnInapoi;
    }

    /**
     * Create the application.
     */
    public OrdersList(List<Order> orders) {
        this.orders = orders;
        initialize(orders);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(List<Order> cn) {
        frame = new JFrame("Take Order");
        frame.getContentPane().setBackground(new Color(0, 123, 123));
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setBounds(100, 100, 502, 337);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Leave" +
                        " ?", "Confirm exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(5, 5, 673, 213);
        frame.getContentPane().add(scrollPane);

        DefaultListModel<String> l1 = new DefaultListModel<>();
        int i = 0;
        for (Order tmp : cn) {
            if (CommandGUI.checkTime(tmp))
                l1.addElement("Take Order " + " " + (++i) + " " + tmp.toString());
        }
        JList list = new JList(l1);
        list.setBackground(new Color(0, 153, 153));
        scrollPane.setViewportView(list);

        textPane = new JTextPane();
        textPane.setBounds(391, 247, 30, 26);
        frame.getContentPane().add(textPane);

        lblNewLabel = new JButton("Take Order");
        lblNewLabel.setBounds(245, 239, 141, 34);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                flag2 = true;
                int number = Integer.parseInt(textPane.getText());
                try {
                    Order order = orders.get(number - 1);
                    double distanceInKm = Math.random() * 5;
                    CompletedOrder completedOrder = new CompletedOrder(order,  distanceInKm*4, distanceInKm,DriverPage.getDriver());
                    new InfoClient(completedOrder);
                    frame.setVisible(false);
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(frame, "please choose existing number of the order");
                }
            }
        });

        btnInapoi = new JButton("Back");
        btnInapoi.setBounds(255, 289, 115, 29);
        frame.getContentPane().add(btnInapoi);

        btnInapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag1 = true;
                frame.setVisible(false);
                DriverPage.afiseaza();
            }
        });

        frame.setSize(700, 400);
        frame.setVisible(true);
    }

    public boolean isFlag2() {
        return flag2;
    }

    public boolean isFlag1() {
        return flag1;
    }
}
