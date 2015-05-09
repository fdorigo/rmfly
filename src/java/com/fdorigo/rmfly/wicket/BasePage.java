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
        add(new HeaderPanel("headerpanel", "Welcome To Wicket")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket and the NetBeans Wicket Plugin"));
    } 

}
