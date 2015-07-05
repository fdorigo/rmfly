/*
 * HomePage.java
 *
 * Created on May 5, 2015, 1:37 PM
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.session.AbstractFacade;
import com.fdorigo.rmfly.jpa.session.RecordFacade;
import com.fdorigo.rmfly.wicket.dataproviders.RecordDataProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CompetingPage extends BasePage {

    private static final Logger LOG = Logger.getLogger(CompetingPage.class.getName());

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    public CompetingPage() {
        init();
    }

    private void init() {
        recordFacade.lazyRefresh();
        List<Record> recordList = recordFacade.findAll();

        Integer competingCount = 0;
        for (Record r : recordList) {
            if (r.getNeedJudging() != null && r.getNeedJudging() == true) {
                competingCount++;
            }
        }

        add(new Label("totalRegistered", Model.of(recordList.size())));
        add(new Label("totalCompeting", Model.of(competingCount)));

        /**
         * TODO, this function needs to be modified to provide the correct range after the dataset is filtered.
         */
        RecordDataProvider gdp = new RecordDataProvider() {
            @Override
            public Iterator<? extends Record> iterator(long first, long count) {
                int[] range = {(int) first, (int) count};
                List<Record> records = getFacade().findRange(range);
                List<Record> judgeableRecords = new ArrayList<>();

                records.stream().filter((r) -> (r.getNeedJudging() != null && r.getNeedJudging() == true)).forEach((r) -> {
                    judgeableRecords.add(r);
                });
                
                return judgeableRecords.iterator();
            }

            @Override
            public AbstractFacade<Record> getFacade() {
                return recordFacade;
            }
        };

        DataView<Record> recordListView = new DataView<Record>("recordlist", gdp) {

            @Override
            protected void populateItem(Item<Record> item) {

                Record record = item.getModelObject();

                Boolean needJudging = false;
                Integer scoreSize = 0;

                if (record.getNeedJudging() != null) {
                    needJudging = record.getNeedJudging();
                }

                if (record.getScoreCollection().isEmpty() == false) {
                    scoreSize = record.getScoreCollection().size();
                }

                PageParameters parameters = new PageParameters();
                parameters.set("nNumber",
                        record.getNnumber());
                BookmarkablePageLink<Void> pageLink = new BookmarkablePageLink<>("pageLinkWithArgs", RecordPage.class, parameters);
                BookmarkablePageLink<Void> scoreLink = new BookmarkablePageLink<>("scoreLinkWithArgs", ScorePage.class, parameters);
                BookmarkablePageLink<Void> resultLink = new BookmarkablePageLink<>("resultLinkWithArgs", ViewScorePage.class, parameters);

                item.add(pageLink.add(new Label("nnumber", record.getNnumber())));
                item.add(new Label("ownerName", record.getOwnerName()));
                item.add(new Label("category", record.getCategory()));
                item.add(scoreLink.add(new Label("score", "add new score")));
                item.add(new Label("judged", scoreSize.toString()));
                item.add(resultLink.add(new Label("results", "view")));
            }
        };

        add(recordListView);

    }

}
