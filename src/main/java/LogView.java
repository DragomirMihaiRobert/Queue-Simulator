import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class LogView extends JFrame {

    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public LogView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1300, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 1270, 645);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        scrollPane.setViewportView(textArea);
        textArea.setEditable(false);
    }

    public void showLogView() {
        this.setVisible(true);
    }

    public void setTextArea(String message) {
        this.textArea.setText(message);
    }

    public String getTextArea() {
        return textArea.getText();
    }
}
