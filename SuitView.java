import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SuitView extends JFrame {
    private JTextField suitIdField;
    private JButton checkButton, repairButton;
    private JLabel resultLabel;

    public SuitView() {
        // Window settings
        setTitle("Superhero Suit Checker");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // UI Components
        suitIdField = new JTextField();
        checkButton = new JButton("Check Suit");
        repairButton = new JButton("Repair Suit");
        resultLabel = new JLabel(" ", SwingConstants.CENTER);

        // Adding components to layout
        add(new JLabel("Enter Suit ID:", SwingConstants.CENTER));
        add(suitIdField);
        add(checkButton);
        add(resultLabel);
        add(repairButton);

        // Disable repair button initially
        repairButton.setEnabled(false);
    }

    // Get suit ID from input field
    public String getSuitId() {
        return suitIdField.getText();
    }

    // Display result message and enable/disable repair button
    public void setResult(String result, boolean canRepair) {
        resultLabel.setText(result);
        repairButton.setEnabled(canRepair);
    }

    // Add event listeners for buttons
    public void addCheckListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }

    public void addRepairListener(ActionListener listener) {
        repairButton.addActionListener(listener);
    }
}
