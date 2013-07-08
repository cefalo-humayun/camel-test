package beans;

import org.apache.camel.Consume;

/**
 * @author Humayun
 */
public class MyService {
    private String serviceEndpoint;

    public MyService(String uri) {
        this.serviceEndpoint = uri;
    }

    public void setServiceEndpoint(String uri) {
        this.serviceEndpoint = uri;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    @Consume(property = "serviceEndpoint")
    public void onService(String input) {
        System.out.println("We are in onService() method - " + input);
    }
}
