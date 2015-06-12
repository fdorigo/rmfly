/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.entities.Score;
import com.fdorigo.rmfly.jpa.session.AbstractFacade;
import com.fdorigo.rmfly.jpa.session.RecordFacade;
import com.fdorigo.rmfly.jpa.session.ScoreFacade;
import com.fdorigo.rmfly.utils.ScoreResults;
import com.fdorigo.rmfly.wicket.dataproviders.ScoreDataProvider;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Francesco Dorigo
 */
public final class ViewScorePage extends BasePage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    @EJB(name = "ScoreFacade")
    private ScoreFacade scoreFacade;

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(ViewScorePage.class.getName());

    private Record record = new Record();

    private final Boolean validNnumber;

    public ViewScorePage() {
        validNnumber = false;
        init();
    }

    public ViewScorePage(PageParameters params) {

        final String nNumberString = params.get("nNumber").toString();

        if (StringUtils.isEmpty(nNumberString)) {
            validNnumber = false;
        } else {
            record = recordFacade.find(nNumberString);
            if (record != null) {
                validNnumber = true;
            } else {
                validNnumber = false;
            }
        }

        init();
    }

    private void init() {
        recordFacade.lazyRefresh();

        List<Score> scoreList = scoreFacade.getScoresByNnumber(record);

        add(new Label("totalScored", Model.of(scoreList.size())));
        add(new Label("category", record.getCategory()));

        /**
         * TODO, this function needs to be modified to provide the correct range
         * after the dataset is filtered.
         */
        ScoreDataProvider gdp = new ScoreDataProvider() {

            @Override
            public Iterator<? extends Score> iterator(long first, long count) {
                int[] range = {(int) first, (int) count};
                List<Score> scoreList = scoreFacade.getScoresByNnumber(record);
                return scoreList.iterator();
            }

            @Override
            public AbstractFacade<Score> getFacade() {
                return scoreFacade;
            }
        };

        DataView<Score> recordListView = new DataView<Score>("scorelist", gdp) {

            @Override
            protected void populateItem(Item<Score> item) {

                Score score = item.getModelObject();

                item.add(new Label("overall", Model.of(score.getScoreOverall())));
                item.add(new Label("fuselage", Model.of(score.getScoreFuselage())));
                item.add(new Label("lift", Model.of(score.getScoreLifts())));
                item.add(new Label("pitch", Model.of(score.getScorePitch())));
                item.add(new Label("landing", Model.of(score.getScoreLanding())));
                item.add(new Label("cockpit", Model.of(score.getScoreCockpit())));
                item.add(new Label("power", Model.of(score.getScorePower())));
                item.add(new Label("finish", Model.of(score.getScoreFinish())));
                item.add(new Label("innovation", Model.of(score.getScoreInnovation())));
                item.add(new Label("judge", Model.of(score.getJudgeName())));
                item.add(new Label("date", Model.of(score.getDate())));
            }
        };

        add(recordListView);
        
        ScoreResults results = new ScoreResults(scoreList);
        
        add(new Label("avgoverall", Model.of(results.getScoreOverall())));
        add(new Label("avgfuselage", Model.of(results.getScoreFuselage())));
        add(new Label("avglift", Model.of(results.getScoreLifts())));
        add(new Label("avgpitch", Model.of(results.getScorePitch())));
        add(new Label("avglanding", Model.of(results.getScoreLanding())));
        add(new Label("avgcockpit", Model.of(results.getScoreCockpit())));
        add(new Label("avgpower", Model.of(results.getScorePower())));
        add(new Label("avgfinish", Model.of(results.getScoreFinish())));
        add(new Label("avginnovation", Model.of(results.getScoreInnovation())));
    }
}
