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

import com.app.gpo.model.FieldMappingToView;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("fieldMappingToViewDAO")
public class FieldMappingToViewDAO extends AbstractDao<Integer, FieldMappingToView> {
    
   public FieldMappingToView find (int id) {
        FieldMappingToView fieldMappingToView = getByKey(id);
        Hibernate.initialize(fieldMappingToView.getArrayView());
        Hibernate.initialize(fieldMappingToView.getfield());
        return fieldMappingToView;
   }
 
    public void save (FieldMappingToView fieldMappingToView) {
        persist(fieldMappingToView);
    }
 
    public void delete (int id) {
        Query query = getSession().createSQLQuery("delete from fieldMappingToView where fieldMappingToViewID = :fieldMappingToViewID");
        query.setString("fieldMappingToViewID", Integer.toString(id));
        query.executeUpdate();
    }
 
    public void deleteByArrayViewID (int id) {
       Query query = getSession().createSQLQuery("delete from fieldMappingToView where arrayViewID = :arrayViewID");
       query.setString("arrayViewID", Integer.toString(id));
       query.executeUpdate(); 
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMappingToView> findAll() {
        Criteria criteria = createEntityCriteria();
        List<FieldMappingToView> fieldMappings = (List<FieldMappingToView>) criteria.list();
        for(FieldMappingToView s : fieldMappings){
            Hibernate.initialize(s.getArrayView());
            Hibernate.initialize(s.getfield());
        }
        return fieldMappings;
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMappingToView> findByArrayViewID (int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.sqlRestriction("{alias}.arrayViewID = "+id));
        List<FieldMappingToView> fieldMappings = (List<FieldMappingToView>) criteria.list();
        for(FieldMappingToView s : fieldMappings){
            Hibernate.initialize(s.getArrayView());
            Hibernate.initialize(s.getfield());
        }
        return fieldMappings;
    }
        
}