/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.dataproviders;

import com.fdorigo.rmfly.jpa.entities.Judge;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author Francesco Dorigo
 */
public abstract class JudgeDataProvider implements IEjbDataProvider<Judge> {

    @Override
    public Iterator<? extends Judge> iterator(long first, long count) {
        int[] range = {(int) first, (int) count};
        List<Judge> records = getFacade().findRange(range);
        return records.iterator();
    }

    @Override
    public IModel<Judge> model(final Judge object) {
        final Integer id = object.getId();
        LoadableDetachableModel<Judge> ldm = new LoadableDetachableModel<Judge>(object) {
            @Override
            protected Judge load() {
                return getFacade().find(id);
            }
        };
        return ldm;
    }

    @Override
    public long size() {
        return getFacade().count();
    }

    @Override
    public void detach() {
    }
}
