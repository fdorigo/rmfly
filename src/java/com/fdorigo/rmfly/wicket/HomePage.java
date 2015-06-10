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
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends BasePage {

    private static final Logger LOG = Logger.getLogger(HomePage.class.getName());

    @EJB(name = "RecordFacade")
    private RecordFacade recordFacade;

    public HomePage() {
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

        RecordDataProvider gdp = new RecordDataProvider() {
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

                item.add(pageLink.add(new Label("nnumber", "edit")));
                item.add(new Label("firstName", record.getFirstName()));
                item.add(new Label("lastName", record.getLastName()));
                item.add(scoreLink.add(new Label("score", record.getNnumber())));
                item.add(new Label("competing", needJudging.toString()));
                item.add(new Label("judged", scoreSize.toString()));
            }
        };

        recordListView.setItemsPerPage(10L);
        add(recordListView);
        add(new PagingNavigator("pagingNavigator", recordListView));
    }

}
