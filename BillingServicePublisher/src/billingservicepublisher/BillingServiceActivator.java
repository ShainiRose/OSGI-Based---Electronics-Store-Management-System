package billingservicepublisher;



import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class BillingServiceActivator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        BillingService billingService = new BillingServiceImpl();
        serviceRegistration = context.registerService(BillingService.class.getName(), billingService, null);
        System.out.println("Billing Service Publisher Started ....");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Billing Service Stopped...");
    }
}

