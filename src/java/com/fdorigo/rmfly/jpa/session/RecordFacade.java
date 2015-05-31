/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa.session;

import com.fdorigo.rmfly.jpa.entities.Acftref;
import com.fdorigo.rmfly.jpa.entities.Master;
import com.fdorigo.rmfly.jpa.entities.Record;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author fdorigo
 */
@Stateless
public class RecordFacade extends AbstractFacade<Record> {

    private static final Logger LOG = Logger.getLogger(RecordFacade.class.getName());

    @PersistenceContext(unitName = "AirshowPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordFacade() {
        super(Record.class);
    }

    public Record buildFromMaster(String id) {
        Record newRecord = em.find(Record.class, id);
        if (newRecord == null) {
            newRecord = new Record(id);

            final Master faaMasterRecord = em.find(Master.class, id);
            if (faaMasterRecord != null) {
                newRecord.setLastName(WordUtils.capitalizeFully(StringUtils.trim(faaMasterRecord.getName())));
                newRecord.setAddressCity(WordUtils.capitalizeFully(StringUtils.trim(faaMasterRecord.getCity())));
                newRecord.setAddressOne(WordUtils.capitalizeFully(StringUtils.trim(faaMasterRecord.getStreet())));
                newRecord.setAddressTwo(WordUtils.capitalizeFully(StringUtils.trim(faaMasterRecord.getStreet2())));
                newRecord.setAddressState(WordUtils.capitalizeFully(StringUtils.trim(faaMasterRecord.getState())));
                newRecord.setAddressZip(StringUtils.trim(faaMasterRecord.getZip()));

                final Acftref faaAircraftRecord = em.find(Acftref.class, faaMasterRecord.getMfrmdlcode());
                if (faaAircraftRecord != null) {
                    newRecord.setAirplaneModel(WordUtils.capitalizeFully(faaAircraftRecord.getModel()));
                    newRecord.setAirplaneMake(WordUtils.capitalizeFully(faaAircraftRecord.getMfrname()));
                }
            }
        }

        return newRecord;
    }

}
