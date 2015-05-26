/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.entities.Score;
import com.fdorigo.rmfly.jps.session.RecordFacade;
import com.fdorigo.rmfly.jps.session.ScoreFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 *
 * @author Francesco Dorigo
 */
public final class ScorePage extends BasePage {

    @EJB(name = "ScoreFacade")
    private ScoreFacade scoreFacade;

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(ScorePage.class.getName());

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    private Record record = new Record();
    private Score score = new Score();

    private final Boolean validNnumber;

    public ScorePage() {
        validNnumber = false;
        init();
    }

    public ScorePage(PageParameters params) {

        final String nNumberString = params.get("nNumber").toString();

        if (StringUtils.isEmpty(nNumberString)) {
            validNnumber = false;
        } else {
            record = recordFacade.find(nNumberString);
            if (record != null) {
                validNnumber = true;
                score.setNnumber(record);
                score.setOwnerName(record.getFirstName() + " " + record.getLastName()); 
                score.setCategory(record.getCategory());
            } else {
                validNnumber = false;
            }
        }
        
        init();
    }

    private void init() {
        add(new FeedbackPanel("feedback"));

        final DateTextField dateTextField = new DateTextField("date");
        dateTextField.setRequired(true);
        final DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        dateTextField.add(datePicker);

        final TextField<String> searchField = new TextField<>("nNumber", Model.of(record.getNnumber()));
        searchField.setRequired(true);
        final TextField<String> ownerName = new TextField<>("ownerName");
        ownerName.setRequired(true);
        final TextField<String> judgeName = new TextField<>("judgeName", Model.of(""));
        judgeName.setRequired(true);
        final TextField<String> category = new TextField<>("category");

        // Scoring Fields 
        final TextField<Integer> overall = new TextField<>("scoreOverall", Integer.class);
        overall.add(new RangeValidator<>(1, 10));
        final TextField<Integer> fuselage = new TextField<>("scoreFuselage", Integer.class);
        fuselage.add(new RangeValidator<>(1, 10));
        final TextField<Integer> lifts = new TextField<>("scoreLifts", Integer.class);
        lifts.add(new RangeValidator<>(1, 10));
        final TextField<Integer> pitch = new TextField<>("scorePitch", Integer.class);
        pitch.add(new RangeValidator<>(1, 10));
        final TextField<Integer> landing = new TextField<>("scoreLanding", Integer.class);
        landing.add(new RangeValidator<>(1, 10));
        final TextField<Integer> cockpit = new TextField<>("scoreCockpit", Integer.class);
        cockpit.add(new RangeValidator<>(1, 10));
        final TextField<Integer> power = new TextField<>("scorePower", Integer.class);
        power.add(new RangeValidator<>(1, 10));
        final TextField<Integer> finish = new TextField<>("scoreFinish", Integer.class);
        finish.add(new RangeValidator<>(1, 10));
        final TextField<Integer> innovation = new TextField<>("scoreInnovation", Integer.class);
        innovation.add(new RangeValidator<>(1, 10));

        Model<Score> scoreModel = new Model<>(score);
        Form<Score> searchForm = new Form<Score>("scoreForm", new CompoundPropertyModel<>(scoreModel)) {

            @Override
            protected void onSubmit() {
                final String scoreString = searchField.getModelObject();
                LOG.info(scoreString);
                scoreFacade.edit(score);
                setResponsePage(HomePage.class);
            }
        };

        add(searchForm);
        searchForm.add(dateTextField);
        searchForm.add(ownerName);
        searchForm.add(judgeName);
        searchForm.add(searchField);
        searchForm.add(category);
        searchForm.add(overall);
        searchForm.add(fuselage);
        searchForm.add(lifts);
        searchForm.add(pitch);
        searchForm.add(landing);
        searchForm.add(cockpit);
        searchForm.add(power);
        searchForm.add(finish);
        searchForm.add(innovation);

    }
}
