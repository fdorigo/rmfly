/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.utils.ScoreResults;
import java.util.List;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

/**
 *
 * @author Francesco Dorigo
 */
public final class CategoryResultPanel extends Panel {

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(CategoryResultPanel.class.getName());

    private String category;

    public CategoryResultPanel(String id, String category, List<ScoreResults> scoreList) {
        super(id);
        this.category = category;
        init(scoreList);
    }

    private void init(List<ScoreResults> l) {
        List<ScoreResults> scoreList = l;

        add(new Label("totalScored", Model.of(scoreList.size())));
        add(new Label("category", category));

        DataView<ScoreResults> recordListView = new DataView<ScoreResults>("scorelist", new ListDataProvider(scoreList)) {

            @Override
            protected void populateItem(Item<ScoreResults> item) {

                ScoreResults score = item.getModelObject();

                item.add(new Label("overall", Model.of(score.getScoreOverall())));
                item.add(new Label("fuselage", Model.of(score.getScoreFuselage())));
                item.add(new Label("lift", Model.of(score.getScoreLifts())));
                item.add(new Label("pitch", Model.of(score.getScorePitch())));
                item.add(new Label("landing", Model.of(score.getScoreLanding())));
                item.add(new Label("cockpit", Model.of(score.getScoreCockpit())));
                item.add(new Label("power", Model.of(score.getScorePower())));
                item.add(new Label("finish", Model.of(score.getScoreFinish())));
                item.add(new Label("innovation", Model.of(score.getScoreInnovation())));
                item.add(new Label("total", Model.of(score.getTotalScore())));
            }
        };

        add(recordListView);

//        ScoreResults results = new ScoreResults(scoreList);
//
//        add(new Label("avgoverall", Model.of(results.getScoreOverall())));
//        add(new Label("avgfuselage", Model.of(results.getScoreFuselage())));
//        add(new Label("avglift", Model.of(results.getScoreLifts())));
//        add(new Label("avgpitch", Model.of(results.getScorePitch())));
//        add(new Label("avglanding", Model.of(results.getScoreLanding())));
//        add(new Label("avgcockpit", Model.of(results.getScoreCockpit())));
//        add(new Label("avgpower", Model.of(results.getScorePower())));
//        add(new Label("avgfinish", Model.of(results.getScoreFinish())));
//        add(new Label("avginnovation", Model.of(results.getScoreInnovation())));
    }
}
