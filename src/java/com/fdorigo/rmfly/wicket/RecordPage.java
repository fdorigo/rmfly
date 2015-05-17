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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author fdorigo
 */
public final class RecordPage extends WebPage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    private static final Logger LOG = Logger.getLogger(RecordPage.class.getName());
    
    private Record record = new Record();
    
    public RecordPage(PageParameters params) {
        
        LOG.info("forwarded: " + params.get("nNumber"));
        
        record = recordFacade.find(""+params.get("nNumber"));
        
        
        add(new FeedbackPanel("feedback"));

        final TextField<String> nNumberField = new TextField<>("nnumber");
        nNumberField.setRequired(true);
        final TextField<String> firstNameField = new TextField<>("firstName");

        Model<Record> recordModel = new Model<Record>(record);
        Form<Record> recordForm = new Form<Record>("recordForm", new CompoundPropertyModel<>(recordModel)) {

            @Override
            protected void onSubmit() {
                LOG.info("NNum: " + recordModel.getObject().getNnumber());
                LOG.info("Frst: " + recordModel.getObject().getFirstName());
            }
        };

        add(recordForm);
        recordForm.add(nNumberField);
        recordForm.add(firstNameField);
        
//        Label selectedFormLabel = new Label("selectedRecord", recordModel);
//        add(selectedFormLabel);
//        
//        final TextField<String> nNumberField = new TextField<>("nNumberField", Model.of(recordModel.getObject().getNnumber()));
//        nNumberField.setRequired(true);
//        Form<Record> recordForm = new Form<Record>("recordForm", new CompoundPropertyModel<Record>(recordModel)) {
//
//            @Override
//            protected void onSubmit() {
//            }
//        };
//
//        add(recordForm);
//        recordForm.add(nNumberField);
//
//        RecordDataProvider gdp = new RecordDataProvider() {
//            @Override
//            public AbstractFacade<Record> getFacade() {
//                return recordFacade;
//            }
//        };
//
//        final DataView<Record> recordListView = new DataView<Record>("recordList", gdp) {
//            @Override
//            protected void populateItem(Item<Record> item) {
//
//                Record record = item.getModelObject();
//                item.add(new Label("name", record.toString()));
//            }
//        };
//
//        add(recordListView);
    }
}
