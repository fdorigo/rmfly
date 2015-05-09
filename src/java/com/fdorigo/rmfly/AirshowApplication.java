/*
 * AirshowApplication.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
package com.fdorigo.rmfly;

import com.fdorigo.rmfly.wicket.HomePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;


/**
 *
 * @author fdorigo
 * @version
 */

public class AirshowApplication extends WebApplication {

    public AirshowApplication() {
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new JavaEEComponentInjector(this));
    }

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }

}
