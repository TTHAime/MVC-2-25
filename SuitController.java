import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class SuitController {
    private SuitView view;
    private List<SuperheroSuit> suits;
    private final String CSV_FILE = "suits.csv";

    public SuitController(SuitView view) {
        this.view = view;
        try {
            // Load suit data from CSV file
            suits = SuperheroSuit.loadFromCSV(CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add event listeners
        view.addCheckListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkSuit();
            }
        });

        view.addRepairListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repairSuit();
            }
        });
    }

    // Check suit validity
    private void checkSuit() {
        String suitId = view.getSuitId();
        SuperheroSuit suit = findSuitById(suitId);

        if (suit == null) {
            view.setResult("❌ Suit not found!", false);
            return;
        }

        if (suit.isValid()) {
            view.setResult("✅ Suit " + suit.getType() + " is valid!", false);
        } else {
            view.setResult("⚠️ Suit " + suit.getType() + " needs repair!", true);
        }
    }

    // Repair suit and save updates to CSV file
    private void repairSuit() {
        String suitId = view.getSuitId();
        SuperheroSuit suit = findSuitById(suitId);

        if (suit != null) {
            suit.repair();
            try {
                SuperheroSuit.saveToCSV(suits, CSV_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            view.setResult("🔧 Suit repaired! New durability: " + suit.getDurability(), false);
        }
    }

    // Find suit by ID from the list
    private SuperheroSuit findSuitById(String suitId) {
        for (SuperheroSuit suit : suits) {
            if (suit.getSuitId().equals(suitId))
                return suit;
        }
        return null;
    }
}
