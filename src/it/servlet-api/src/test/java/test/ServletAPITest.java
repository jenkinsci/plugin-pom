package test;

import hudson.security.LegacySecurityRealm;
import jenkins.model.Jenkins;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class ServletAPITest {

    @Rule
    public final JenkinsRule rule = new JenkinsRule();

    /**
     * When having both Servlet APIs 3.1 and 4.0 in classpath, the following error
     * is logged on server side:
     *
     * <pre>
     * WARNING	o.e.jetty.server.HttpChannel#handleException: /jenkins/j_security_check
     * java.lang.AbstractMethodError: Receiver class org.eclipse.jetty.security.authentication.SessionAuthentication does not define or inherit an
     * implementation of the resolved method 'abstract void valueBound(javax.servlet.http.HttpSessionBindingEvent)' of interface
     * javax.servlet.http.HttpSessionBindingListener. at org.eclipse.jetty.server.session.Session.bindValue(Session.java:357)
     * </pre>
     *
     * And then on client side getting "500 Server Error for
     * http://localhost:.../jenkins/j_security_check"
     */
    @Test
    public void involveHttpSessionBindingListener() throws Exception {
        Jenkins.get().setSecurityRealm(new LegacySecurityRealm());
        rule.createWebClient().login("bob");
    }
}
