package beans;

import org.apache.camel.Message;

/**
 * @author humayun
 */
public class MyBean {
    public String hello(String name) {
        System.out.println("we are in MyBean.hello() method");
        return "Hello " + name;
    }
}
