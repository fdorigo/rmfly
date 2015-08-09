/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author fdorigo
 */
public class NavigationPanel extends Panel {
    public NavigationPanel(String id) {
        super(id);

        Link homePageLink = new Link("home") {
            @Override
            public void onClick() {
                this.setResponsePage(new HomePage());
            }
        };
        this.add(homePageLink);

        Link competingPageLink = new Link("competing") {
            @Override
            public void onClick() {
                this.setResponsePage(new CompetingPage());
            }
        };
        this.add(competingPageLink);

        Link movieListPageLink = new Link("search_nnumber") {
            @Override
            public void onClick() {
                this.setResponsePage(new NnumberPage());
            }
        };
        this.add(movieListPageLink);

        Link addJudge = new Link("add_judge") {
            @Override
            public void onClick() {
                this.setResponsePage(new JudgePage());
            }
        };
        this.add(addJudge);
        
        Link resultPage = new Link("result_page") {
            @Override
            public void onClick() {
                this.setResponsePage(new CompetitionResultsPage());
            }
        };
        this.add(resultPage);
    }
}