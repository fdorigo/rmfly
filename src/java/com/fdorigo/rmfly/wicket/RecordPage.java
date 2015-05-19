/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author fdorigo
 */
public final class RecordPage extends BasePage {

    static final List<Boolean> TRUE_FALSE = Arrays.asList(true, false);

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    private static final Logger LOG = Logger.getLogger(RecordPage.class.getName());

    private Record record = new Record();

    public RecordPage(PageParameters params) {

        final String nNumberString = params.get("nNumber").toString();
        record = recordFacade.find(nNumberString);

        add(new FeedbackPanel("feedback"));

        final DateTextField dateTextField = new DateTextField("arrivalDate");
        dateTextField.setRequired(true);

        DatePicker datePicker = new DatePicker() {
            @Override
            protected String getAdditionalJavaScript() {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        dateTextField.add(datePicker);
        
        final TextField<String> nNumberField = new TextField<>("nnumber");
        nNumberField.add(AttributeModifier.append("readonly", "true"));

        final TextField<String> firstNameField = new TextField<>("firstName");
        
        final TextField<String> lastNameField = new TextField<>("lastName");
        
        
        final TextField<String> primaryPhoneField = new TextField<>("primaryPhone");
        primaryPhoneField.setRequired(true);
        final TextField<String> secondaryPhoneField = new TextField<>("secondaryPhone");
        final TextField<String> addressOneField = new TextField<>("addressOne");
        final TextField<String> addressStateField = new TextField<>("addressState");
        final TextField<String> addressCityField = new TextField<>("addressCity");
        final TextField<String> addressZipField = new TextField<>("addressZip");
        final TextField<String> emailAddressField = new TextField<>("emailAddress");
        final TextField<String> airplaneMakeField = new TextField<>("airplaneMake");
        final TextField<String> airplaneModelField = new TextField<>("airplaneModel");
        final TextField<Integer> manufactureYearField = new TextField<>("manufactureYear");
        final TextField<String> homebaseField = new TextField<>("homebase");

//        final RadioChoice<Boolean> rc = new RadioChoice<>("needJudging", TRUE_FALSE).setSuffix("");
//        rc.set
//        rc.setRequired(true);
//        rc.add(new AttributeAppender("class", new Model<String>("pure-radio"), " "));
        RadioGroup<String> group = new RadioGroup<String>("needJudging");
        add(group);
        ListView<Boolean> radios = new ListView<Boolean>("radios", TRUE_FALSE) {
            @Override
            protected void populateItem(ListItem<Boolean> item) {
                Radio<Boolean> radio = new Radio<Boolean>("radio", item.getModel());
                radio.setLabel(new Model(item.getModelObject()));
                item.add(radio);
                item.add(new SimpleFormComponentLabel("boolval", radio));
            }
        }.setReuseItems(true);
        group.add(radios);

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
        recordForm.add(lastNameField);
        recordForm.add(secondaryPhoneField);
        recordForm.add(addressOneField);
        recordForm.add(addressStateField);
        recordForm.add(addressCityField);
        recordForm.add(addressZipField);
        recordForm.add(emailAddressField);
        recordForm.add(airplaneMakeField);
        recordForm.add(airplaneModelField);
        recordForm.add(manufactureYearField);
        recordForm.add(homebaseField);

        /* Mandatory Fields */
        recordForm.add(new FormComponentFeedbackBorder("arrivalDateBorder").add(dateTextField));
        recordForm.add(new FormComponentFeedbackBorder("primaryPhoneBorder").add(primaryPhoneField));
        recordForm.add(new FormComponentFeedbackBorder("needJudgingBorder").add(group));

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
