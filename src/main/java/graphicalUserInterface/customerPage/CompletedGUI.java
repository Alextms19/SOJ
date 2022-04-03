package graphicalUserInterface.customerPage;

import dataStructures.CompletedOrder;
import parser.Parser;
import dataStructures.Client;
import dataStructures.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class CompletedGUI {

    private JFrame frame;
    private Client client;
    private String telefon;
    private String numarInmatriculare;
    private boolean flagInainte;
    private boolean flagInapoi;
    private JButton btnInapoi;
    private JButton btnInainte;

    public JButton getBtnInapoi() {
        return btnInapoi;
    }

    public JButton getBtnInainte() {
        return btnInainte;
    }

    public boolean isFlagInainte() {
        return flagInainte;
    }

    public boolean isFlagInapoi() {
        return flagInapoi;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public String getUsernameSofer() {
        return usernameSofer;
    }

    private String usernameSofer;

    public CompletedGUI(Client client) {
        this.client = client;
        List<CompletedOrder> ce = Parser.getEfectuate("src/main/resources/completed.xml");
        for(CompletedOrder tmp: ce)
            if(tmp.getClient().getUsername().equals(client.getUsername())){
                usernameSofer = tmp.getDriver().getUsername();
            }
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("comanda se finalizeaza");
        frame.getContentPane().setFont(new Font("Wide Latin", Font.PLAIN, 22));
        frame.getContentPane().setBackground(new Color(127, 255, 212));
        frame.getContentPane().setLayout(null);

        JLabel lblComandaInAstepatre = new JLabel("Comanda in curs de finalizare");
        lblComandaInAstepatre.setForeground(new Color(65, 105, 225));
        lblComandaInAstepatre.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 24));
        lblComandaInAstepatre.setBounds(15, 28, 637, 75);
        frame.getContentPane().add(lblComandaInAstepatre);

        JLabel lblNumeSofer = new JLabel("Nume sofer:");
        lblNumeSofer.setFont(new Font("Wide Latin", Font.ITALIC, 22));
        lblNumeSofer.setBounds(46, 119, 392, 44);
        frame.getContentPane().add(lblNumeSofer);

        JLabel lblNewLabel = new JLabel("Contact:");
        lblNewLabel.setFont(new Font("Wide Latin", Font.ITALIC, 22));
        lblNewLabel.setBounds(46, 177, 392, 34);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(usernameSofer);
        lblNewLabel_1.setFont(new Font("Wide Latin", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(453, 108, 196, 44);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel label = new JLabel(telefon);
        label.setFont(new Font("Wide Latin", Font.PLAIN, 16));
        label.setBounds(453, 157, 196, 44);
        frame.getContentPane().add(label);


        btnInapoi = new JButton("Inapoi");
        btnInapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flagInapoi = true;
                frame.setVisible(false);
                CustomerGUI.afiseaza();
            }
        });
        btnInapoi.setBounds(175, 287, 115, 44);
        frame.getContentPane().add(btnInapoi);

        btnInainte = new JButton("Inainte");
        btnInainte.setBounds(389, 288, 115, 42);
        btnInainte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flagInainte = true;
                frame.setVisible(false);
                new NewOrder(client);
            }
        });
        frame.getContentPane().add(btnInainte);
        frame.setBounds(100, 100, 700, 400);
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
    }
}

