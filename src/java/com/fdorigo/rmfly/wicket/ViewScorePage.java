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
import com.fdorigo.rmfly.wicket.dataproviders.ScoreDataProvider;
import java.util.ArrayList;
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

        LOG.info("Score List: " + scoreList.toString());

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
                List<Score> records = getFacade().findRange(range);
                List<Score> filtered = new ArrayList<>();

                for (Score s : records) {
                    if (s.getNnumber().equals(record)) {
                        filtered.add(s);
                    }
                }

                return filtered.iterator();
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
        add(new Label("avgoverall", "n/a"));
        add(new Label("avgfuselage", "n/a"));
        add(new Label("avglift", "n/a"));
        add(new Label("avgpitch", "n/a"));
        add(new Label("avglanding", "n/a"));
        add(new Label("avgcockpit", "n/a"));
        add(new Label("avgpower", "n/a"));
        add(new Label("avgfinish", "n/a"));
        add(new Label("avginnovation", "n/a"));
    }
}
