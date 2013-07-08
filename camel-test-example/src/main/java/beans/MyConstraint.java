package beans;

import org.eclipse.jetty.http.security.Constraint;

/**
 * @author Humayun
 */
public class MyConstraint extends Constraint {
    public MyConstraint() {
        super("BASIC", "tracker-users");
        super.setAuthenticate(true);
    }
}
