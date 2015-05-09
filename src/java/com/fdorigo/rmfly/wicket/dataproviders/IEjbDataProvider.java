/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.wicket.dataproviders;

import com.fdorigo.rmfly.jps.session.AbstractFacade;
import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 *
 * @author fdorigo
 */
public interface IEjbDataProvider<T> extends IDataProvider<T> {

    AbstractFacade<T> getFacade();
}
