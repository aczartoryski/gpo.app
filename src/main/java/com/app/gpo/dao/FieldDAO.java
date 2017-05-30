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
    
    public Field find (int id) {
        return getByKey(id);
    }
 
    public void save (Field field) {
        persist(field);
    }
    
    public String saveNew (Field field) {
        return addNew(field);
    }
    
    public void update (Field field) throws HibernateException  {
        try {
           saveUpdate(field);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
    }
    
    public void delete (int id) {
        Query query = getSession().createSQLQuery("delete from field where fieldID = :fieldID");
        query.setString("fieldID", Integer.toString(id));
        query.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<Field> findAll () {
        Criteria criteria = createEntityCriteria();
        return (List<Field>) criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllNotAssignToProductSlot (int id) {
        Query query = getSession().createQuery("from Field as f where f.fieldID not in "
                + "(select fm.field.fieldID from FieldMapping as fm where fm.productionSlot.productionSlotID = :productionSlotID)");
        query.setString("productionSlotID", Integer.toString(id));
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllNotAssignToArrayView (int id) {
        Query query = getSession().createQuery("from Field as f where f.fieldID not in "
                + "(select fm.field.fieldID from FieldMappingToView as fm where fm.arrayView.arrayViewID = :arrayViewID)");
        query.setString("arrayViewID", Integer.toString(id));
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> findAllAssignToArrayView (int id) {
        Query query = getSession().createQuery("from Field as f where f.fieldID in "
                + "(select fm.field.fieldID from FieldMappingToView as fm where fm.arrayView.arrayViewID = :arrayViewID)");
        query.setString("arrayViewID", Integer.toString(id));
        return query.list();
    }
    
    
    @SuppressWarnings("unchecked")
    public Field findByfieldOriginID (String fieldOriginID) {
        Query query = getSession().createQuery("from Field as f where f.fieldOriginID = :fieldOriginID)");
        query.setString("fieldOriginID", fieldOriginID);
        return (Field) query.uniqueResult();
    }
    
}
