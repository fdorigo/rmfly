/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.entities.Score;
import com.fdorigo.rmfly.jpa.session.JudgeFacade;
import com.fdorigo.rmfly.jpa.session.RecordFacade;
import com.fdorigo.rmfly.jpa.session.ScoreFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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

    @EJB(name = "JudgeFacade")
    private JudgeFacade judgeFacade;

    private Record record = new Record();
    private Score score = new Score();

    public ScorePage() {
        init();
    }

    public ScorePage(PageParameters params) {

        final String nNumberString = params.get("nNumber").toString();

        if (!StringUtils.isEmpty(nNumberString)) {
            record = recordFacade.find(nNumberString);
            if (record != null) {
                score.setNnumber(record);
                score.setOwnerName(record.getFirstName() + " " + record.getLastName());
                score.setCategory(record.getCategory());
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

        final DropDownChoice<String> ddc = new DropDownChoice<>("judgeName", new PropertyModel<String>(score, "judgeName"), judgeFacade.getAllNames());

        final TextField<String> category = new TextField<>("category");
        // Scoring Fields 
        final TextField<Integer> fuselage = new TextField<>("scoreFuselage", Integer.class);
        fuselage.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> lifts = new TextField<>("scoreLifts", Integer.class);
        lifts.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> pitch = new TextField<>("scorePitch", Integer.class);
        pitch.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> landing = new TextField<>("scoreLanding", Integer.class);
        landing.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> cockpit = new TextField<>("scoreCockpit", Integer.class);
        cockpit.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> power = new TextField<>("scorePower", Integer.class);
        power.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> finish = new TextField<>("scoreFinish", Integer.class);
        finish.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> innovation = new TextField<>("scoreInnovation", Integer.class);
        innovation.setRequired(true).add(new RangeValidator<>(1, 10));
        final TextField<Integer> overall = new TextField<>("scoreOverall", Integer.class);
        overall.setRequired(true).add(new RangeValidator<>(1, 10));

        Model<Score> scoreModel = new Model<>(score);
        Form<Score> searchForm = new Form<Score>("scoreForm", new CompoundPropertyModel<>(scoreModel)) {
            @Override
            protected void onSubmit() {
                Integer existingScore = scoreFacade.exists(ddc.getModelObject(), score.getNnumber().getNnumber());
                if (existingScore != null) {
                    score = scoreFacade.find(existingScore);
                    Score tempScore = scoreModel.getObject();
                    score.setDate(tempScore.getDate());
                    score.setScoreCockpit(tempScore.getScoreCockpit());
                    score.setScoreFinish(tempScore.getScoreFinish());
                    score.setScoreFuselage(tempScore.getScoreFuselage());
                    score.setScoreInnovation(tempScore.getScoreInnovation());
                    score.setScoreLanding(tempScore.getScoreLanding());
                    score.setScoreLifts(tempScore.getScoreLifts());
                    score.setScoreOverall(tempScore.getScoreOverall());
                    score.setScorePitch(tempScore.getScorePitch());
                    score.setScorePower(tempScore.getScorePower());
                }

                scoreFacade.edit(score);
                setResponsePage(CompetingPage.class);
            }
        };

        add(searchForm);
        searchForm.add(dateTextField);
        searchForm.add(ownerName);
        searchForm.add(ddc.setRequired(true));
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
