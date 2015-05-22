/*
 * HomePage.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jps.session.AbstractFacade;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import com.fdorigo.rmfly.wicket.dataproviders.RecordDataProvider;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends BasePage {

    private static final Logger LOG = Logger.getLogger(HomePage.class.getName());

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    public HomePage() {
        init();
    }

    private void init() {
//        LoadableDetachableModel<Record> newRecordDetachableModel = new LoadableDetachableModel<Record>() {
//            @Override
//            public Record load() {
//                return new Record();
//            }
//        };
//
//        add(new FeedbackPanel("feedback"));
//
//        Form<Record> recordForm = new Form<Record>("record_form", new CompoundPropertyModel<>(newRecordDetachableModel)) {
//
//            @Override
//            protected void onSubmit() {
//                Record record = getModelObject();
//                recordFacade.create(record);
//                setModelObject(new Record());
//                record = new Record();
//            }
//        };
//        add(recordForm);
//
//        recordForm
//                .add(new FormComponentFeedbackBorder("firstNameBorder")
//                        .add(new NameTextField("firstName")));
//        recordForm
//                .add(new FormComponentFeedbackBorder("lastNameBorder")
//                        .add(new NameTextField("lastName")));
        
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
                PageParameters parameters = new PageParameters();
                parameters.set("nNumber",
                        record.getNnumber());
                BookmarkablePageLink<Void> pageLink = new BookmarkablePageLink<Void>("pageLinkWithArgs", RecordPage.class, parameters);
                
                item.add(pageLink.add(new Label("nnumber", record.getNnumber())));  
                item.add(new Label("firstName", record.getFirstName()));
                item.add(new Label("lastName", record.getLastName()));
            }

        };

        add(recordListView);

    }

}
