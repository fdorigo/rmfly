/*
 * WicketExamplePage.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
 
package com.fdorigo.rmfly.wicket;           

import org.apache.wicket.markup.html.WebPage;

/** 
 *
 * @author fdorigo
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "A great event")); 
        add(new NavigationPanel("mainNavigation"));
        add(new FooterPanel("footerpanel", "Designed and maintained by Francesco Dorigo (fdorigo@gmail.com)"));
    } 

}
