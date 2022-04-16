import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel contentPane;
    private JButton startButton;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JTextField timeLimit;
    private JTextField numberOfClients;
    private JTextField numberOfServers;
    private JTextField minArrivalTime;
    private JTextField maxArrivalTime;
    private JTextField minServiceTime;
    private JTextField maxServiceTime;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 248, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        startButton = new JButton("START");
        startButton.setBackground(new Color(50, 205, 50));
        startButton.setForeground(Color.BLACK);
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        startButton.setBounds(258, 79, 127, 115);
        contentPane.add(startButton);

        lblNewLabel = new JLabel("Waiting Queue Simulator");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel.setBounds(71, 22, 273, 38);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Time simulation limit:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(37, 79, 127, 16);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Number of clients:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(37, 94, 127, 16);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Number of queues:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(37, 109, 127, 16);
        contentPane.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("Minimum arrival time:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(37, 124, 140, 16);
        contentPane.add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("Maximum arrival time:");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_5.setBounds(37, 139, 140, 16);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("Minimum service time:");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_6.setBounds(37, 154, 140, 16);
        contentPane.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("Maximum service time:");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_7.setBounds(37, 169, 140, 16);
        contentPane.add(lblNewLabel_7);

        maxServiceTime = new JTextField();
        maxServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        maxServiceTime.setColumns(10);
        maxServiceTime.setBounds(178, 172, 70, 15);
        contentPane.add(maxServiceTime);

        minServiceTime = new JTextField();
        minServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        minServiceTime.setColumns(10);
        minServiceTime.setBounds(178, 157, 70, 15);
        contentPane.add(minServiceTime);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        maxArrivalTime.setColumns(10);
        maxArrivalTime.setBounds(178, 142, 70, 15);
        contentPane.add(maxArrivalTime);

        minArrivalTime = new JTextField();
        minArrivalTime.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        minArrivalTime.setColumns(10);
        minArrivalTime.setBounds(178, 127, 70, 15);
        contentPane.add(minArrivalTime);

        numberOfServers = new JTextField();
        numberOfServers.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        numberOfServers.setColumns(10);
        numberOfServers.setBounds(178, 112, 70, 15);
        contentPane.add(numberOfServers);

        numberOfClients = new JTextField();
        numberOfClients.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        numberOfClients.setColumns(10);
        numberOfClients.setBounds(178, 97, 70, 15);
        contentPane.add(numberOfClients);

        timeLimit = new JTextField();
        timeLimit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        timeLimit.setColumns(10);
        timeLimit.setBounds(178, 82, 70, 15);
        contentPane.add(timeLimit);
    }

    public String getTimeLimit() {
        return timeLimit.getText();
    }

    public String getNumberOfClients() {
        return numberOfClients.getText();
    }

    public String getNumberOfServers() {
        return numberOfServers.getText();
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public void addStartButtonActionListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
