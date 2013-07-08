package beans;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.Message;

import java.util.Map;

/**
 * @author humayun
 */
public class MyBean {
    public void hello(@Headers Map<String, String> headers, @Header("ETag") String eTag, @Body String name) {
        System.out.println("we are in MyBean.hello() method - " + name + "\n" + headers + "\n" + eTag);
        //return "Hello " + name;
    }

    /*public void hello(*//*@Headers*//* Map<String, String> headers, *//*@Header("ETag")*//* String eTag, *//*@Body*//* String name) {
        System.out.println("we are in MyBean.hello() method - " + name + "\n" + headers + "\n" + eTag);
        //return "Hello " + name;
    }*/
}
