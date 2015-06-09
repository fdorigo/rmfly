/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket;

import com.fdorigo.rmfly.jpa.entities.Judge;
import com.fdorigo.rmfly.jpa.session.JudgeFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

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

//    @Override
//    public void renderHead(IHeaderResponse response) {
//        PackageResourceReference TEL_JS = new PackageResourceReference(JudgePage.class, "js/airshow-telephone.js");
//        response.render(JavaScriptReferenceHeaderItem.forReference(TEL_JS));
//    }
//    
//    

    private void init() {
        add(new FeedbackPanel("feedback"));

        final TextField<String> firstNameField = new TextField<>("firstName");
        final TextField<String> lastNameField = new TextField<>("lastName");
        final TextField<String> phoneNumber = new TextField<>("phoneNumber");

        Model<Judge> judgeModel = new Model<>(judge);
        Form<Judge> judgeForm = new Form<Judge>("judgeForm", new CompoundPropertyModel<>(judgeModel)) {
            @Override
            protected void onSubmit() {
                judgeFacade.edit(judge);
            }
        };

        add(judgeForm);

        /* Mandatory Fields */
        judgeForm.add(firstNameField.setRequired(true));
        judgeForm.add(lastNameField.setRequired(true));
        judgeForm.add(phoneNumber.setRequired(true));
    }

}
