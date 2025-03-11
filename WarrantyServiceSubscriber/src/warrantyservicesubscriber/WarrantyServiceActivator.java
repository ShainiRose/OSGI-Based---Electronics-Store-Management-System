package warrantyservicesubscriber;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import warrantyservicepublisher.WarrantyService;

public class WarrantyServiceActivator implements BundleActivator {
    private ServiceReference<?> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
    	System.out.println("Warrenty Service Subscriber Started ....");
        System.out.println("\n================== Welcome to Electronics Store ==================");
        System.out.println(" Please Sign In to Access Warranty Management.");
        System.out.println("=================================================================");

        Scanner scanner = new Scanner(System.in);

        //  login credentials
        String correctUsername = "admin";
        String correctPassword = "admin123";

        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!username.equals(correctUsername) || !password.equals(correctPassword)) {
            System.out.println("\nInvalid credentials! Exiting system.");
            System.out.println("=================================================================\n");
            scanner.close();
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.print("Enter Your Name:");
        String adminname = scanner.nextLine();
        System.out.println("================== Welcome, " + adminname + " ==================\n");

        serviceReference = context.getServiceReference(WarrantyService.class.getName());
        WarrantyService warrantyService = (WarrantyService) context.getService(serviceReference);

        while (true) {
            System.out.println("1 - Register Product Warranty");
            System.out.println("2 - Check Warranty Status");
            System.out.println("3 - Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter Product Name: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter Serial Number: ");
                    String serialNumber = scanner.nextLine();

                    System.out.print("Enter Warranty Years: ");
                    int warrantyYears = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    warrantyService.registerWarranty(customerName, productName, serialNumber, warrantyYears);
                    break;

                case 2:
                    System.out.print("\nEnter Serial Number to Check Warranty: ");
                    String serial = scanner.nextLine();
                    warrantyService.checkWarrantyStatus(serial);
                    break;

                case 3:
                	System.out.println("\nExiting...");
                    System.out.println("=================================================================\n");
                    
                    // Properly unget service before exiting
                    if (serviceReference != null) {
                        context.ungetService(serviceReference);
                    }

                    System.out.println("Warranty Service Client Stopped...");
                    scanner.close();
                    System.exit(0); // Ensures the program exits properly
                    break;
                    
                    

                default:
                    System.out.println("\nInvalid option, try again.");
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Warranty Service  Stopped...");
        context.ungetService(serviceReference);
    }
}
