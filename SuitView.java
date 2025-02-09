import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SuitView extends JFrame {
    private JTextField suitIdField;
    private JButton checkButton, repairButton, logButton;
    private JLabel resultLabel;

    public SuitView() {
        setTitle("Superhero Suit Checker");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        suitIdField = new JTextField();
        checkButton = new JButton("Check Suit");
        repairButton = new JButton("Repair Suit");
        logButton = new JButton("Show Repair Log");
        resultLabel = new JLabel(" ", SwingConstants.CENTER);

        add(new JLabel("Enter Suit ID:", SwingConstants.CENTER));
        add(suitIdField);
        add(checkButton);
        add(resultLabel);
        add(repairButton);
        add(logButton);

        repairButton.setEnabled(false);
    }

    public String getSuitId() {
        return suitIdField.getText();
    }

    public void setResult(String result, boolean canRepair) {
        resultLabel.setText(result);
        repairButton.setEnabled(canRepair);
    }

    public void showRepairLog(String log) {
        JTextArea textArea = new JTextArea(log, 10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Repair Log", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addCheckListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }

    public void addRepairListener(ActionListener listener) {
        repairButton.addActionListener(listener);
    }

    public void addLogListener(ActionListener listener) {
        logButton.addActionListener(listener);
    }
}
