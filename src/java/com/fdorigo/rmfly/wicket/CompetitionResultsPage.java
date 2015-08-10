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
import com.fdorigo.rmfly.wicket.components.AirplaneType;
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
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Francesco Dorigo
 */
public final class CompetitionResultsPage extends BasePage {

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    @EJB(name = "ScoreFacade")
    private ScoreFacade scoreFacade;

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(CompetitionResultsPage.class.getName());

    private Record record = new Record();

    private final Boolean validNnumber;

    public CompetitionResultsPage() {
        validNnumber = false;
        init();
    }

    public CompetitionResultsPage(PageParameters params) {

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
        List<Record> records = recordFacade.findAll();
        List<Record> judgeableRecords = new ArrayList<>();
        List<ScoreResults> resultList = new ArrayList<>();
        
        records.stream().filter((r) -> (r.getNeedJudging() != null && r.getNeedJudging() == true)).forEach((r) -> {
            judgeableRecords.add(r);
        });
        
        for (Record r : judgeableRecords) {
            List<Score> scores = (List<Score>)r.getScoreCollection();
            ScoreResults result = new ScoreResults(r.getNnumber(), scores);
            resultList.add(result);
        }
                
        add(new CategoryResultPanel("all_results", "All Categories", new ArrayList(resultList)));
        
        resultList.clear();
        
        judgeableRecords.stream().filter((r) -> (AirplaneType.HOMEKIT.toString().equals(r.getCategory()))).forEach((r) -> {
            List<Score> scores = (List<Score>)r.getScoreCollection();
            ScoreResults result = new ScoreResults(r.getNnumber(), scores);
            resultList.add(result);
        });
        
//        for (Record r : judgeableCategory) {
//            List<Score> scores = (List<Score>)r.getScoreCollection();
//            ScoreResults result = new ScoreResults(scores);
//            resultList.add(result);
//        }
                
        add(new CategoryResultPanel("homekit_results", AirplaneType.HOMEKIT.toString(), new ArrayList(resultList)));
        resultList.clear();
    }
}
