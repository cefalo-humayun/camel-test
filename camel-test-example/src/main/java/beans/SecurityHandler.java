package beans;

import org.eclipse.jetty.http.security.Constraint;
import org.eclipse.jetty.security.Authenticator;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
//import org.eclipse.jetty.util.security.Constraint;

/**
 * @author Humayun
 */
public class SecurityHandler extends ConstraintSecurityHandler {
    Authenticator authenticator = new BasicAuthenticator();
    Constraint constraint = new Constraint("BASIC", "");
    //constraint.setAuthenticate();
}
