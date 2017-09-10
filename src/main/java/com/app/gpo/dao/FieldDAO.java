/*
 * The MIT License
 *
 * Copyright 2017 Artur Czartoryski <artur at czartoryski.wroclaw.pl>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.app.gpo.dao;

import com.app.gpo.model.Field;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("fieldDAO")
public class FieldDAO extends AbstractDao<Integer, Field> {
    private static final Logger logger = Logger.getLogger(FieldDAO.class);
    
    public Field find (int id) {
        logger.info("HQL started find Field");
        return getByKey(id);
    }
 
    public void save (Field field) {
        logger.info("HQL started save Field");
        persist(field);
        logger.info("HQL finished save Field");
    }
    
    public String saveNew (Field field) {
        logger.info("HQL started saveNew Field");
        return addNew(field);
    }
    
    public void update (Field field) throws HibernateException  {
        logger.info("HQL started update Field");
        try {
           saveUpdate(field);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
        logger.info("HQL finished update Field");
    }
    
    public void delete (int id) {
        logger.info("HQL started delete Field");
        Query query = getSession().createSQLQuery("delete from field where fieldID = :fieldID");
        query.setString("fieldID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete Field");
    }

 
    @SuppressWarnings("unchecked")
    public List<Field> findAll () {
        logger.info("HQL started findAll Field");
        Criteria criteria = createEntityCriteria();
        logger.info("HQL finished findAll Field");
        return (List<Field>) criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllNotAssignToProductSlot (int id) {
        logger.info("HQL started findAllNotAssignToProductSlot Field");
        Query query = getSession().createQuery("from Field as f where f.fieldID not in "
                + "(select fm.field.fieldID from FieldMapping as fm where fm.productionSlot.productionSlotID = :productionSlotID)");
        query.setString("productionSlotID", Integer.toString(id));
        logger.info("HQL finished findAllNotAssignToProductSlot Field");
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllNotAssignToArrayView (int id) {
        logger.info("HQL started findAllNotAssignToArrayView Field");
        Query query = getSession().createQuery("from Field as f where f.fieldID not in "
                + "(select fm.field.fieldID from FieldMappingToView as fm where fm.arrayView.arrayViewID = :arrayViewID)");
        query.setString("arrayViewID", Integer.toString(id));
        logger.info("HQL finished findAllNotAssignToArrayView Field");
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllAssignToArrayView (int id) {
        logger.info("HQL started findAllAssignToArrayView Field");
        Query query = getSession().createQuery("from Field as f where f.fieldID in "
                + "(select fm.field.fieldID from FieldMappingToView as fm where fm.arrayView.arrayViewID = :arrayViewID)");
        query.setString("arrayViewID", Integer.toString(id));
        logger.info("HQL finished findAllAssignToArrayView Field");
        return query.list();
    }
    
    
    @SuppressWarnings("unchecked")
    public Field findByfieldOriginID (String fieldOriginID) {
        logger.info("HQL started findByfieldOriginID Field");
        Query query = getSession().createQuery("from Field as f where f.fieldOriginID = :fieldOriginID)");
        query.setString("fieldOriginID", fieldOriginID);
        logger.info("HQL finished findByfieldOriginID Field");
        return (Field) query.uniqueResult();
    }
    
}
