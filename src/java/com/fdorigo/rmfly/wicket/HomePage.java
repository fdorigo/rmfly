/*
 * HomePage.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jps.session.AbstractFacade;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import com.fdorigo.rmfly.wicket.components.NameTextField;
import com.fdorigo.rmfly.wicket.dataproviders.RecordDataProvider;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class HomePage extends BasePage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    public HomePage() {

        LoadableDetachableModel<Record> newRecordDetachableModel = new LoadableDetachableModel<Record>() {
            @Override
            public Record load() {
                return new Record();
            }
        };

        add(new FeedbackPanel("feedback"));

        Form<Record> recordForm = new Form<Record>("record_form", new CompoundPropertyModel<>(newRecordDetachableModel)) {

            @Override
            protected void onSubmit() {
                Record record = getModelObject();
                recordFacade.create(record);
                setModelObject(new Record());
                record = new Record();
            }
        };
        add(recordForm);

        recordForm
                .add(new FormComponentFeedbackBorder("firstNameBorder")
                        .add(new NameTextField("firstName")));
        recordForm
                .add(new FormComponentFeedbackBorder("lastNameBorder")
                        .add(new NameTextField("lastName")));

        RecordDataProvider gdp = new RecordDataProvider() {

            @Override
            public AbstractFacade<Record> getFacade() {
                return recordFacade;
            }
        };

        DataView<Record> recordListView = new DataView<Record>("recordlist", gdp) {

            @Override
            protected void populateItem(Item<Record> item) {
                Record record = item.getModelObject();
                item.add(new Label("name", record.toString()));
            }

        };

        add(recordListView);

    }

}
