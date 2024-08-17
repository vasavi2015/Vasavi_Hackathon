import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class EItem {
    private String id;
    private String name;
    private boolean needsReplacement;
    private String replacementDate;

    public EItem(String id, String name, boolean needsReplacement, String replacementDate) {
        this.id = id;
        this.name = name;
        this.needsReplacement = needsReplacement;
        this.replacementDate = replacementDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isNeedsReplacement() {
        return needsReplacement;
    }

    public String getReplacementDate() {
        return replacementDate;
    }

    @Override
    public String toString() {
        return "EItem{id='" + id + "', name='" + name + "', needsReplacement=" + needsReplacement + 
               ", replacementDate='" + replacementDate + "'}";
    }
}

public class EWasteMonitoringSystem {

    private static Map<String, EItem> eWasteMap = new HashMap<>();
    private static Random random = new Random();

    public static String generateUniqueId() {
        String id;
        do {
            id = String.format("%04d", random.nextInt(10000)); // Generate a 4-digit number
        } while (eWasteMap.containsKey(id)); // Ensure the ID is unique
        return id;
    }

    public static void addEItem(String name, boolean needsReplacement, String replacementDate) {
        String id = generateUniqueId();
        EItem item = new EItem(id, name, needsReplacement, replacementDate);
        eWasteMap.put(item.getId(), item); // Store using the unique ID as the key
        System.out.println("Item added successfully with ID: " + item.getId());
    }

    public static void removeEItem(String id) {
        EItem removedItem = eWasteMap.remove(id);
        if (removedItem == null) {
            System.out.println("Item not found.");
        } else {
            System.out.println("Removed: " + removedItem);
        }
    }

    public static void listEWasteItems() {
        if (eWasteMap.isEmpty()) {
            System.out.println("No E-Waste items to display.");
        } else {
            System.out.printf("%-6s %-20s %-18s %-15s%n", "ID", "Name", "Needs Replacement", "Replacement Date");
            System.out.println("---------------------------------------------------------------");
            for (EItem item : eWasteMap.values()) {
                System.out.printf("%-6s %-20s %-18s %-15s%n", item.getId(), item.getName(),
                        item.isNeedsReplacement() ? "Yes" : "No", item.getReplacementDate());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nE-Waste Monitoring System");
            System.out.println("1. Add E-Waste Item");
            System.out.println("2. Remove E-Waste Item by ID");
            System.out.println("3. List E-Waste Items");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Does it need replacement (true/false): ");
                    boolean needsReplacement = scanner.nextBoolean();
                    scanner.nextLine();  // Consume newline
                    String replacementDate = "";
                    if (needsReplacement) {
                        System.out.print("Enter the replacement date (YYYY-MM-DD): ");
                        replacementDate = scanner.nextLine();
                    }
                    addEItem(name, needsReplacement, replacementDate);
                    break;
                case 2:
                    System.out.print("Enter item ID to remove: ");
                    String idToRemove = scanner.nextLine();
                    removeEItem(idToRemove);
                    break;
                case 3:
                    listEWasteItems();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}
