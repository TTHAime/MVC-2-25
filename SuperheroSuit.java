import java.io.*;
import java.util.*;

public class SuperheroSuit {
    private String suitId;
    private String type;
    private int durability;

    public SuperheroSuit(String suitId, String type, int durability) {
        this.suitId = suitId;
        this.type = type;
        this.durability = durability;

    }

    public String getSuitId() {
        return suitId;
    }

    public String getType() {
        return type;
    }

    public int getDurability() {
        return durability;
    }

    // Ensure max durability does not exceed 100
    public void repair() {
        durability = Math.min(100, durability + 25);
    }

    public boolean isValid() {
        switch (type) {
            case "Powerful":
                return durability >= 70;
            case "Stealth":
                return durability >= 50;
            case "Cloak":
                return !(String.valueOf(durability).endsWith("3") || String.valueOf(durability).endsWith("7"));
            default:
                return false;
        }
    }

    public static List<SuperheroSuit> loadFromCSV(String filename) throws IOException {
        List<SuperheroSuit> suits = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            suits.add(new SuperheroSuit(parts[0], parts[1], Integer.parseInt(parts[2])));
        }
        br.close();
        return suits;
    }

    public static void saveToCSV(List<SuperheroSuit> suits, String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (SuperheroSuit suit : suits) {
            bw.write(suit.getSuitId() + "," + suit.getType() + "," + suit.getDurability());
            bw.newLine();
        }
        bw.close();
    }
}
