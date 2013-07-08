package beans;

import org.eclipse.jetty.security.ConstraintMapping;

/**
 * @author Humayun
 */
public class MyConstraintMapping extends ConstraintMapping {
    MyConstraint myConstraint = new MyConstraint();

    public MyConstraintMapping() {
        //super.setConstraint(myConstraint);
    }
}
