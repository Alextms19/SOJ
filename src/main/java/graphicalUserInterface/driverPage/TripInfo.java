package graphicalUserInterface.driverPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.Locale;

public class TripInfo {
    private JFrame frmTripInfo;
    private double distance;
    private boolean flag;
    private JButton btnBack;

    public JButton getBtnBack(){
        return btnBack;
    }

    public boolean isFlag(){
        return flag;
    }


    public TripInfo(double distance) {
        this.distance = distance+1;
        initialize();
        frmTripInfo.setVisible(true);
    }

    private void initialize() {
        frmTripInfo = new JFrame();
        frmTripInfo.setTitle("Trip Info");
        frmTripInfo.getContentPane().setBackground(new Color(127, 255, 212));
        frmTripInfo.setBounds(100, 100, 450, 300);
        frmTripInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmTripInfo.getContentPane().setLayout(null);
        frmTripInfo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frmTripInfo,"Leave" +
                        " ?","Confirm exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frmTripInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frmTripInfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        btnBack = new JButton("Back");
        btnBack.setBounds(152, 184, 115, 29);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag = true;
                DriverPage.afiseaza();
                frmTripInfo.setVisible(false);
            }
        });
        frmTripInfo.getContentPane().add(btnBack);

        JLabel lblNewLabel = new JLabel("Trip duration:");
        lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 16));
        lblNewLabel.setBounds(82, 48, 136, 26);
        frmTripInfo.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\tTrip price:");
        lblNewLabel_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(82, 132, 136, 26);
        frmTripInfo.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel((((int)distance) * 3) + " minutes" );
        lblNewLabel_2.setBounds(261, 50, 69, 20);
        frmTripInfo.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel(NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format( distance * 4 ));
        lblNewLabel_3.setBounds(261, 134, 69, 20);
        frmTripInfo.getContentPane().add(lblNewLabel_3);
    }
}
