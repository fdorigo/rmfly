/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.dataproviders;

import com.fdorigo.rmfly.jpa.entities.Score;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author Francesco Dorigo
 */
public abstract class ScoreDataProvider implements IEjbDataProvider<Score> {

    @Override
    public Iterator<? extends Score> iterator(long first, long count) {
        int[] range = {(int) first, (int) count};
        List<Score> records = getFacade().findRange(range);
        return records.iterator();
    }

    @Override
    public IModel<Score> model(final Score object) {
        final Integer id = object.getId();
        LoadableDetachableModel<Score> ldm = new LoadableDetachableModel<Score>(object) {
            @Override
            protected Score load() {
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
