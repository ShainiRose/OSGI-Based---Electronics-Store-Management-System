package customersupportservicepublisher;



import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CustomerSupportServiceActivator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        CustomerSupportService customerSupportService = new CustomerSupportServiceImpl();
        serviceRegistration = context.registerService(CustomerSupportService.class.getName(), customerSupportService, null);
        System.out.println("Customer Support Service Publisher Started...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Customer Support Service Stopped...");
    }
}
