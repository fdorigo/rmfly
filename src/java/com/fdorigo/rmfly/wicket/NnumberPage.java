/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jps.session.AbstractFacade;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import com.fdorigo.rmfly.wicket.dataproviders.RecordDataProvider;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author fdorigo
 */
public final class NnumberPage extends WebPage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    private static final Logger LOG = Logger.getLogger(NnumberPage.class.getName());

    public NnumberPage() {
        add(new FeedbackPanel("feedback"));

        final TextField<String> searchField = new TextField<>("nNumber", Model.of(""));
        searchField.setRequired(true);

        Model<Record> recordModel = new Model<Record>();

        Form<Record> searchForm = new Form<Record>("nNumberForm", new CompoundPropertyModel<Record>(recordModel)) {

            @Override
            protected void onSubmit() {
                final String record = searchField.getModelObject();

                Record rec = recordFacade.find(record);
                if (rec == null) {
                    rec = recordFacade.buildFromMaster(record);
                    //recordFacade.create(rec);
                }
                LOG.info("This is it: " + rec);
                
                setModelObject(rec);
            }
        };

        add(searchForm);
        searchForm.add(searchField);
        
        Label selectedFormLabel = new Label("selectedRecord", recordModel);
        add(selectedFormLabel);

        RecordDataProvider gdp = new RecordDataProvider() {
            @Override
            public AbstractFacade<Record> getFacade() {
                return recordFacade;
            }
        };

        final DataView<Record> recordListView = new DataView<Record>("recordList", gdp) {

            @Override
            protected void populateItem(Item<Record> item) {

                Record record = item.getModelObject();
                LOG.info("Looking for some shit: " + record.getFirstName());
                item.add(new Label("name", record.toString()));
            }

        };

        add(recordListView);

    }

//    private class SearchForm extends Form {
//
//        private TextField searchField;
//
//        public SearchForm(String id) {
//            super(id);
//            searchField = new TextField("nNumber", Model.of(""));
//
//            add(searchField);
//        }
//
//        public final void onSubmit() {
//            String searchText = (String) searchField.getDefaultModelObject();
//
//            LOG.info("Searching for: " + searchText);
//        }
//
//    }
}
