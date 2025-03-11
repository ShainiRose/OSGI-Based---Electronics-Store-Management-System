package inventoryservicesubscriber;



import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import inventoryservicepublisher.InventoryService;

public class InventoryServiceActivator implements BundleActivator {
    private ServiceReference<?> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Inventory Service Subscriber Started...");

        Scanner scanner = new Scanner(System.in);

        // Login System
        System.out.println("\n================== Welcome to Electronics Store ==================");
        System.out.println(" Please Sign In to Access Inventory Management.");
        System.out.println("=================================================================");

        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!username.equals("admin") || !password.equals("admin123")) {
            System.out.println("\nInvalid credentials! Exiting system.");
            scanner.close();
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.print("Enter Your Name:");
        String adminname = scanner.nextLine();
        System.out.println("================== Welcome, " + adminname + " ==================\n");

        serviceReference = context.getServiceReference(InventoryService.class.getName());
        InventoryService inventoryService = (InventoryService) context.getService(serviceReference);

        while (true) {
            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Enter Price per Unit: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            inventoryService.addProduct(productName, quantity, price);

            System.out.print("Do you want to add another product? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
        scanner.close();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Inventory Service Client Stopped...");
        context.ungetService(serviceReference);
    }
}
