package inventoryservicepublisher;

import java.util.HashMap;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    @Override
    public void addProduct(String productName, int quantity, double price) {
        inventory.put(productName, inventory.getOrDefault(productName, 0) + quantity);
        System.out.println("\n==================================================");
        System.out.println(" Product Added: " + productName);
        System.out.println(" Quantity: " + quantity);
        System.out.println(" Price per Unit: $" + price);
        System.out.println("==================================================\n");
    }
}
