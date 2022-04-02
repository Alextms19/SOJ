package graphicalUserInterface.driverPage;

import dataStructures.CompletedOrder;
import dataStructures.Driver;

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

public class ViewOrders {

    private JFrame frame;
    private JTable table_1;
    private Driver driver;
    private boolean b=false;
    private JButton btnBack;

    public JButton getBtnBack() {
        return btnBack;
    }

    public boolean isB() {
        return b;
    }

    public ViewOrders(Driver driver) {
        this.driver=driver;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(32, 178, 170));
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblViewOrders = new JLabel("View Orders");
        lblViewOrders.setForeground(new Color(0, 0, 128));
        lblViewOrders.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
        lblViewOrders.setBounds(195, 10, 301, 51);
        frame.getContentPane().add(lblViewOrders);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(54, 83, 581, 171);
        frame.getContentPane().add(scrollPane_1);

        double price=0;
        Object data [][]=new Object [100][5];
        int contor=0;
        List<CompletedOrder> completedOrders=parser.Parser.getCompletedOrders(
                "src/main/resources/completedOrders.xml");
        for (CompletedOrder com: completedOrders) {
            if(com.getDriver().getUsername().equals(driver.getUsername())){
                data[contor][0]=com.getOrder().getClient().getUsername();
                data[contor][1]=com.getOrder().getLocationTo();
                data[contor][2]=com.getPriceInRON();
                price+=(double) data[contor][2];
                data[contor++][3]=com.getReview();
            }
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Leave" +
                        " ?","Confirm exit :", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        table_1 = new JTable();
        scrollPane_1.setViewportView(table_1);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        table_1.setModel(new DefaultTableModel(
                data,
                new String[] {
                        "Client", "Destination", "Price in RON", "Review"
                }
        ));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 128));
        panel.setForeground(new Color(0, 0, 128));
        panel.setBounds(54, 264, 581, 34);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblMoneyAmount = new JLabel("The money amount :");
        lblMoneyAmount.setForeground(new Color(255, 255, 255));
        lblMoneyAmount.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblMoneyAmount.setBounds(57, 0, 382, 34);
        panel.add(lblMoneyAmount);

        JLabel lblPrice = new JLabel(NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format(price));
        lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblPrice.setForeground(new Color(255, 255, 255));
        lblPrice.setBounds(436, 5, 63, 24);
        panel.add(lblPrice);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setForeground(new Color(0, 0, 128));
        btnBack.setBounds(251, 318, 173, 21);
        frame.getContentPane().add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b=true;
                frame.dispose();
                DriverPage.afiseaza();
            }
        });
    }
}