/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.components;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 *
 * @author fdorigo
 */
public class NameTextField extends TextField<String> {

    /**
     *
     * @param id
     * @param model
     */
    public NameTextField(String id, IModel<String> model) {
        super(id, model);
        setRequired(true);
    }

    /**
     *
     * @param id
     */
    public NameTextField(String id) {
        this(id, null);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        tag.put("maxlength", "45");
        tag.put("size", "55");
        super.onComponentTag(tag);
    }

}
