/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.entities.Score;
import com.fdorigo.rmfly.jpa.session.RecordFacade;
import com.fdorigo.rmfly.utils.ScoreResults;
import com.fdorigo.rmfly.wicket.components.AirplaneType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author Francesco Dorigo
 */
public final class CompetitionResultsPage extends BasePage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(CompetitionResultsPage.class.getName());

    public CompetitionResultsPage() {
        init();
    }

    private void init() {
        recordFacade.lazyRefresh();
        List<Record> records = recordFacade.findAll();
        List<Record> judgeableRecords = new ArrayList<>();

        records.stream().filter((r) -> (r.getNeedJudging() != null && r.getNeedJudging() == true)).forEach((r) -> {
            judgeableRecords.add(r);
        });

        populateResults(judgeableRecords);
    }

    public static Comparator<ScoreResults> ResultsComparator
            = new Comparator<ScoreResults>() {
                public int compare(ScoreResults o1, ScoreResults o2) {
                    return o2.getTotalScore().compareTo(o1.getTotalScore());
                }
            };

    private void populateResults(final List<Record> judgeableRecords) {
        final List<ScoreResults> resultList = new ArrayList<>();

        for (Record r : judgeableRecords) {
            final List<Score> scores = (List<Score>) r.getScoreCollection();
            ScoreResults result = new ScoreResults(r.getNnumber(), scores);
            resultList.add(result);
        }
        
        //Collections.sort(resultList, ResultsComparator);
        Collections.sort(resultList);
        add(new CategoryResultPanel("all_results", "All Categories", new ArrayList(resultList)));

        for (AirplaneType t : AirplaneType.values()) {
            resultList.clear();
            judgeableRecords.stream().filter((r) -> (t.toString().equals(r.getCategory()))).forEach((r) -> {
                List<Score> scores = (List<Score>) r.getScoreCollection();
                ScoreResults result = new ScoreResults(r.getNnumber(), scores);
                resultList.add(result);
            });

            //Collections.sort(resultList, ResultsComparator);
            Collections.sort(resultList);
            final CategoryResultPanel resultPanel = new CategoryResultPanel(t.getWicketId(), t.toString(), new ArrayList(resultList));
            add(resultPanel);
            if (resultList.isEmpty()) {
                resultPanel.setVisible(false);
            }
        }
    }
}
