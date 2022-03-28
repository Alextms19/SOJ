package graphicalUserInterface.driverPage;
import dataStructures.Driver;
import jsonClasses.JSONEditProfile;
import jsonClasses.JSONFile;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class EditProfileGUI {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextField cnp;
    private JTextField nri;
    private JTextField car;
    private Driver sofer;
    private JButton btnSalveazaModificari;
    private JButton btnInapoi;
    private boolean b1=false;
    private boolean b2=false;
    private JPanel panel;

    public JButton getBtnSalveazaModificari() {
        return btnSalveazaModificari;
    }

    public JButton getBtnInapoi() {
        return btnInapoi;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getCnp() {
        return cnp;
    }

    public JTextField getNri() {
        return nri;
    }

    public JTextField getCar() {
        return car;
    }

    public boolean isB1() {
        return b1;
    }

    public boolean isB2() {
        return b2;
    }

    public EditProfileGUI(Driver s) {
        sofer=s;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setForeground(Color.BLUE);
        frame.setBounds(100, 100, 550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 165, 0));
        frame.setVisible(true);
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
        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 128));
        panel.setBounds(0, 0, 536, 34);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblEditeazaProfilul = new JLabel("Editeaza Profilul");
        lblEditeazaProfilul.setBounds(168, 10, 197, 20);
        panel.add(lblEditeazaProfilul);
        lblEditeazaProfilul.setForeground(new Color(255, 255, 255));
        lblEditeazaProfilul.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));

        btnSalveazaModificari = new JButton("Salveaza modificari");
        btnSalveazaModificari.setForeground(new Color(0, 0, 128));
        btnSalveazaModificari.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnSalveazaModificari.setBounds(290, 307, 176, 32);
        frame.getContentPane().add(btnSalveazaModificari);

        btnInapoi = new JButton("Inapoi");
        btnInapoi.setForeground(new Color(0, 0, 128));
        btnInapoi.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnInapoi.setBounds(65, 307, 176, 32);
        frame.getContentPane().add(btnInapoi);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 0, 128));
        panel_1.setBounds(26, 82, 484, 213);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(10, 22, 90, 15);
        panel_1.add(lblUsername);
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JLabel lblParola = new JLabel("Parola");
        lblParola.setBounds(10, 71, 79, 26);
        panel_1.add(lblParola);
        lblParola.setForeground(new Color(255, 255, 255));
        lblParola.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JLabel lblCnp = new JLabel("CNP");
        lblCnp.setBounds(240, 24, 46, 13);
        panel_1.add(lblCnp);
        lblCnp.setForeground(new Color(255, 255, 255));
        lblCnp.setFont(new Font("Times New Roman", Font.BOLD, 16));


        JLabel lblMasina = new JLabel("Masina");
        lblMasina.setBounds(240, 71, 112, 27);
        panel_1.add(lblMasina);
        lblMasina.setForeground(new Color(255, 255, 255));
        lblMasina.setFont(new Font("Times New Roman", Font.BOLD, 18));


        JLabel lblNumarInmatriculare = new JLabel("Numar Inmatriculare");
        lblNumarInmatriculare.setBounds(10, 133, 196, 27);
        panel_1.add(lblNumarInmatriculare);
        lblNumarInmatriculare.setForeground(new Color(255, 255, 255));
        lblNumarInmatriculare.setFont(new Font("Times New Roman", Font.BOLD, 18));

        textField = new JTextField();
        textField.setBounds(110, 22, 96, 19);
        panel_1.add(textField);
        textField.setColumns(10);
        //textField.setText("anaa");
        textField.setText(sofer.getUsername());

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 77, 96, 19);
        panel_1.add(passwordField);
        //passwordField.setText("maximm");
        passwordField.setText(sofer.getPassword());

        cnp = new JTextField();
        cnp.setBounds(335, 22, 96, 19);
        panel_1.add(cnp);
        cnp.setColumns(10);
       // cnp.setText("299129999");
        cnp.setText(sofer.getCNP());

        car = new JTextField();
        car.setBounds(335, 77, 96, 19);
        panel_1.add(car);
        car.setColumns(10);
       // car.setText("Renault");
        car.setText(sofer.getMasina());

        nri = new JTextField();
        nri.setBounds(216, 139, 96, 19);
        panel_1.add(nri);
        nri.setColumns(10);
       // nri.setText("AB11TBG");
        nri.setText(sofer.getNumarInmatriculare());

        btnSalveazaModificari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(passwordField.getText().equals("")==false) {
                    ArrayList<Driver> soferi = JSONEditProfile.getDriver();
                    soferi.remove(sofer);
                    String nume, parola, CNP, masina, nr;
                    nume = textField.getText();
                    parola = passwordField.getText();
                    CNP = cnp.getText();
                    masina = car.getText();
                    nr = nri.getText();
                    Driver sof = new Driver(nume, JSONFile.encodePassword(nume, parola));
                    sof.setNumarInmatriculare(nr);
                    sof.setMasina(masina);
                    sof.setCNP(CNP);
                    soferi.add(sof);
                    JSONEditProfile.writeDriver(soferi);
                    frame.setVisible(false);
                    new DriverPage(sof);
                }
            }
        });

        btnInapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b1=true;
                frame.dispose();
                DriverPage.afiseaza();
            }
        });
    }
}

