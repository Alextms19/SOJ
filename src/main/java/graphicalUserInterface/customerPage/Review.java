package graphicalUserInterface.customerPage;

import dataStructures.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Review {

    private JFrame frame;
    private JTextArea textField;
    private JTextArea textField_1;
    private JButton btnInapoi;
    private JButton btnNewButton;
    private Client client;
    private boolean flag1;

    public JButton getBtnInapoi() {
        return btnInapoi;
    }

    public JButton getBtnNewButton() {
        return btnInapoi;
    }

    public boolean isFlag1() {
        return flag1;
    }

    public Review(Client client) {
        this.client = client;
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("Reviews");
        frame.getContentPane().setBackground(new Color(0, 155, 255));
        frame.getContentPane().setLayout(null);

        JLabel lblRecenzii = new JLabel("Reviews");
        lblRecenzii.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 30));
        lblRecenzii.setBounds(259, 42, 159, 34);
        frame.getContentPane().add(lblRecenzii);

        btnNewButton = new JButton("Save\r\n");
        btnNewButton.setBounds(274, 254, 115, 29);

        frame.getContentPane().add(btnNewButton);

        btnInapoi = new JButton("Back");
        btnInapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag1 = true;
                frame.setVisible(false);
                CustomerGUI.afiseaza();
            }
        });
        btnInapoi.setBounds(274, 299, 115, 29);
        frame.getContentPane().add(btnInapoi);

        JLabel label = new JLabel("Data:");
        label.setFont(new Font("Stencil", Font.PLAIN, 16));
        label.setBounds(86, 107, 69, 20);
        frame.getContentPane().add(label);

        JLabel lblNewLabel_1 = new JLabel("Review:");
        lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(82, 165, 87, 41);
        frame.getContentPane().add(lblNewLabel_1);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(200, 92, 300, 42);
        frame.getContentPane().add(scrollPane_1);

        textField_1 = new JTextArea();
        scrollPane_1.setViewportView(textField_1);
        textField_1.setFont(new Font("Sylfaen", Font.PLAIN, 18));
        textField_1.setBackground(new Color(255, 160, 122));
        textField_1.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(200, 150, 300, 88);
        frame.getContentPane().add(scrollPane);

        textField = new JTextArea();
        textField.setFont(new Font("Sylfaen", Font.PLAIN, 22));
        scrollPane.setViewportView(textField);
        textField.setBackground(new Color(240, 128, 128));
        textField.setColumns(10);
        frame.setBounds(100, 100, 700, 400);
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
    }
}

