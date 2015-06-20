/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.session.RecordFacade;
import com.fdorigo.rmfly.wicket.components.AirplaneType;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;

/**
 *
 * @author fdorigo
 */
public final class RecordPage extends BasePage {

    private static final Logger LOG = Logger.getLogger(RecordPage.class.getName());

    private static final List<Boolean> TRUE_FALSE = Arrays.asList(true, false);

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    private Record record = new Record();

    private AirplaneType selected = null;

    private final Boolean validNnumber;

    public RecordPage() {
        validNnumber = false;
        init();
    }

    public RecordPage(PageParameters params) {

        final String nNumberString = params.get("nNumber").toString();

        if (StringUtils.isEmpty(nNumberString)) {
            validNnumber = false;
        } else {
            record = recordFacade.find(nNumberString);
            if (record != null) {
                validNnumber = true;
                if (record.getCategory() != null) {
                    selected = AirplaneType.fromString(record.getCategory());
                }
            } else {
                validNnumber = false;
                record = new Record();
            }
        }

        init();
    }

    private void init() {
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
        nNumberField.setRequired(true);
        if (validNnumber) {
            nNumberField.add(AttributeModifier.append("readonly", "true"));
        } else {
            nNumberField.add(AttributeModifier.append("placeholder", "Not Found"));
        }

        DropDownChoice<AirplaneType> listCategories = new DropDownChoice<AirplaneType>(
                "category", new PropertyModel<>(this, "selected"), Arrays.asList(AirplaneType.values()));
        listCategories.setRequired(true);

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
        emailAddressField.add(EmailAddressValidator.getInstance());
        final TextField<String> airplaneMakeField = new TextField<>("airplaneMake");
        final TextField<String> airplaneModelField = new TextField<>("airplaneModel");
        final TextField<Integer> manufactureYearField = new TextField<>("manufactureYear");

        RadioGroup<String> group = new RadioGroup<>("needJudging");
        group.setRequired(true);
        add(group);
        ListView<Boolean> radios = new ListView<Boolean>("radios", TRUE_FALSE) {
            @Override
            protected void populateItem(ListItem<Boolean> item) {
                Radio<Boolean> radio = new Radio<>("radio", item.getModel());
                radio.setLabel(new Model(item.getModelObject()));
                item.add(radio);
                item.add(new SimpleFormComponentLabel("boolval", radio));
            }
        }.setReuseItems(true);
        group.add(radios);

        Model<Record> recordModel = new Model<>(record);
        Form<Record> recordForm = new Form<>("recordForm", new CompoundPropertyModel<>(recordModel));

        recordForm.add(new Button("save") {
            @Override
            public void onSubmit() {
                record.setCategory(selected.toString());
                recordFacade.edit(record);
                setResponsePage(HomePage.class);
            }
        });
        
        Button deleteRecordButton = new Button("delete") {
            @Override
            public void onSubmit() {
                recordFacade.remove(record);
                setResponsePage(HomePage.class);
            }
        };
        
        deleteRecordButton.setDefaultFormProcessing(false);
        recordForm.add(deleteRecordButton);
        
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

        /* Mandatory Fields */
        recordForm.add(dateTextField);
        recordForm.add(primaryPhoneField);
        recordForm.add(group);
        recordForm.add(listCategories);
//        recordForm.add(new FormComponentFeedbackBorder("arrivalDateBorder").add(dateTextField));
//        recordForm.add(new FormComponentFeedbackBorder("primaryPhoneBorder").add(primaryPhoneField));
//        recordForm.add(new FormComponentFeedbackBorder("needJudgingBorder").add(group));
//        recordForm.add(new FormComponentFeedbackBorder("categoryBorder").add(listCategories));
    }

}
