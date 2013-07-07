package beans;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.Message;

import java.util.Map;

/**
 * @author humayun
 */
public class MyBean {
    public void hello(@Headers Map<String, String> headers, @Body String name) {
        System.out.println("we are in MyBean.hello() method - " + name + "\n" + headers);
        //return "Hello " + name;
    }
}
