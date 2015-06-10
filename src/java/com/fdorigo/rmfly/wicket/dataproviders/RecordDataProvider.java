/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.dataproviders;

import com.fdorigo.rmfly.jpa.entities.Record;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author fdorigo
 */
public abstract class RecordDataProvider implements IEjbDataProvider<Record> {

    @Override
    public Iterator<? extends Record> iterator(long first, long count) {
        List<Record> records = getFacade().findAll();
        return records.subList((int) first, (int) (first + count)).iterator();
    }

    @Override
    public IModel<Record> model(final Record object) {
        final String id = object.getNnumber();
        LoadableDetachableModel<Record> ldm = new LoadableDetachableModel<Record>(object) {
            @Override
            protected Record load() {
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
