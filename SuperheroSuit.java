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

    public void repair() {
        if (durability < 100) {
            durability = Math.min(100, durability + 25);
        }
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

    public static void generateSuits(String filename, int totalSamples, int minPerType) {
        String[] suitTypes = { "Powerful", "Stealth", "Cloak" };
        List<SuperheroSuit> suits = new ArrayList<>();
        Map<String, Integer> typeCount = new HashMap<>();

        for (String type : suitTypes) {
            typeCount.put(type, 0);
        }

        while (suits.size() < totalSamples) {
            String suitId = generateSuitId();
            String type = suitTypes[new Random().nextInt(suitTypes.length)];
            int durability = generateDurability(type);

            if (typeCount.get(type) < minPerType || suits.size() < totalSamples - (suitTypes.length - 1)) {
                suits.add(new SuperheroSuit(suitId, type, durability));
                typeCount.put(type, typeCount.get(type) + 1);
            }
        }

        try {
            saveToCSV(suits, filename);
            System.out.println("✅ Generated " + suits.size() + " superhero suits in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateSuitId() {
        Random rand = new Random();
        int firstDigit = rand.nextInt(9) + 1;
        int otherDigits = rand.nextInt(100000);
        return firstDigit + String.format("%05d", otherDigits);
    }

    private static int generateDurability(String type) {
        Random rand = new Random();
        int durability;

        do {
            durability = rand.nextInt(101);
        } while (!isValidDurability(type, durability));

        return durability;
    }

    private static boolean isValidDurability(String type, int durability) {
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
}
