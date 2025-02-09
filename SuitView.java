import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SuitView extends JFrame {
    private JTextField suitIdField;
    private JButton checkButton, repairButton;
    private JLabel resultLabel;

    public SuitView() {
        setTitle("Superhero Suit Checker");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        suitIdField = new JTextField();
        checkButton = new JButton("Check Suit");
        repairButton = new JButton("Repair Suit");
        resultLabel = new JLabel(" ", SwingConstants.CENTER);

        add(new JLabel("Enter Suit ID:", SwingConstants.CENTER));
        add(suitIdField);
        add(checkButton);
        add(resultLabel);
        add(repairButton);

        repairButton.setEnabled(false);
    }

    public String getSuitId() {
        return suitIdField.getText();
    }

    public void setResult(String result, boolean canRepair) {
        resultLabel.setText(result);
        repairButton.setEnabled(canRepair);
    }

    public void addCheckListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }

    public void addRepairListener(ActionListener listener) {
        repairButton.addActionListener(listener);
    }
}
