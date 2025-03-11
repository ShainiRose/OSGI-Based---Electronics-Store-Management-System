package inventoryservicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class InventoryServiceActivator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        InventoryService inventoryService = new InventoryServiceImpl();
        serviceRegistration = context.registerService(InventoryService.class.getName(), inventoryService, null);
        System.out.println("Inventory Service Publisher Started...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Inventory Service Stopped...");
    }
}
