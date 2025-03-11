package billingservicepublisher;


public interface BillingService {
    void generateBill(String customerName, String productName, int quantity, double price);
}

