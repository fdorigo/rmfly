/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Francesco Dorigo
 */
public final class NnumberPage extends WebPage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(NnumberPage.class.getName());

    public NnumberPage() {
        add(new FeedbackPanel("feedback"));

        final TextField<String> searchField = new TextField<>("nNumber", Model.of(""));
        searchField.setRequired(true);

        Model<Record> recordModel = new Model<Record>();
        Form<Record> searchForm = new Form<Record>("nNumberForm", new CompoundPropertyModel<>(recordModel)) {

            @Override
            protected void onSubmit() {
                final String recordString = searchField.getModelObject();
                Record record = recordFacade.find(recordString);
                
                if (record == null) {
                    record = recordFacade.buildFromMaster(recordString);
                    recordFacade.create(record);
                }

                setModelObject(record);
                PageParameters pageParameters = new PageParameters();
                pageParameters.add("nNumber", record.getNnumber());
                //pageParameters.add("firstName", record.getFirstName());
                setResponsePage(RecordPage.class, pageParameters);
            }
        };

        add(searchForm);
        searchForm.add(searchField);
        
        Label selectedFormLabel = new Label("selectedRecord", recordModel);
        add(selectedFormLabel);

    }
}
