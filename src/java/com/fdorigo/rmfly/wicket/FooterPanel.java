/*
 * FooterPanel.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
 
package com.fdorigo.rmfly.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author fdorigo
 * @version 
 */

public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
