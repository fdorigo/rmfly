/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Judge;
import com.fdorigo.rmfly.jpa.session.JudgeFacade;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author fdorigo
 */
public final class JudgePage extends BasePage {

    private static final Logger LOG = Logger.getLogger(JudgePage.class.getName());

    @EJB(name = "JudgeFacade")
    private JudgeFacade judgeFacade;

    private Judge judge;

    public JudgePage() {
        this.judge = new Judge();
        init();
    }

    public JudgePage(PageParameters params) {
        this.judge = judgeFacade.find(Integer.parseInt(params.get("jId").toString()));
        init();
    }

    private void init() {

        add(new FeedbackPanel("feedback"));

        final DropDownChoice<Judge> ddc;
        ddc = new DropDownChoice<Judge>("judgeName",
                new CompoundPropertyModel<Judge>(judge),
                new LoadableDetachableModel<List<Judge>>() {
                    @Override
                    protected List<Judge> load() {
                        return judgeFacade.findAll();
                    }

                }) {

            @Override
            public boolean isNullValid() {
                return true; 
            }
                        
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

            @Override
            protected void onSelectionChanged(Judge newSelection) {
                judge = newSelection;
                PageParameters params = new PageParameters();
                params.add("jId", judge.getId());
                setResponsePage(JudgePage.class, params);
            }
        };

        final TextField<String> firstNameField = new TextField<>("firstName");
        final TextField<String> lastNameField = new TextField<>("lastName");
        final TextField<String> phoneNumber = new TextField<>("phoneNumber");

        Model<Judge> judgeModel = new Model<>(judge);
        Form<Judge> judgeForm = new Form<Judge>("judgeForm", new CompoundPropertyModel<>(judgeModel)) {

            @Override
            protected void onSubmit() {
                if (judgeFacade.exists(judgeModel.getObject())) {
                    judge = judgeFacade.getByFirstLast(judgeModel.getObject());
                    judge.setFirstName(judgeModel.getObject().getFirstName());
                    judge.setLastName(judgeModel.getObject().getLastName());
                    judge.setPhoneNumber(judgeModel.getObject().getPhoneNumber());
                }

                judgeFacade.edit(judge);
                setResponsePage(HomePage.class);
            }
        };

        add(judgeForm.setOutputMarkupId(true));

        /* Mandatory Fields */
        judgeForm.add(firstNameField.setRequired(true));
        judgeForm.add(lastNameField.setRequired(true));
        judgeForm.add(phoneNumber.setRequired(true));
        judgeForm.add(ddc.setRequired(false));
    }
}
