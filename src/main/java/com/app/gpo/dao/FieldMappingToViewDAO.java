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

import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(FieldMappingToViewDAO.class);
   public FieldMappingToView find (int id) {
       logger.info("HQL started find FieldMappingToView");
        FieldMappingToView fieldMappingToView = getByKey(id);
        Hibernate.initialize(fieldMappingToView.getArrayView());
        Hibernate.initialize(fieldMappingToView.getfield());
       logger.info("HQL finished find FieldMappingToView");
        return fieldMappingToView;
   }
 
    public void save (FieldMappingToView fieldMappingToView) {
        logger.info("HQL started save FieldMappingToView");
       persist(fieldMappingToView);
        logger.info("HQL finished save FieldMappingToView");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete FieldMappingToView");
        Query query = getSession().createSQLQuery("delete from fieldMappingToView where fieldMappingToViewID = :fieldMappingToViewID");
        query.setString("fieldMappingToViewID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete FieldMappingToView");
    }
 
    public void deleteByArrayViewID (int id) {
        logger.info("HQL started deleteByArrayViewID FieldMappingToView");
        Query query = getSession().createSQLQuery("delete from fieldMappingToView where arrayViewID = :arrayViewID");
       query.setString("arrayViewID", Integer.toString(id));
       query.executeUpdate();
        logger.info("HQL finished deleteByArrayViewID FieldMappingToView");
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMappingToView> findAll() {
        logger.info("HQL started findAll FieldMappingToView");
        Criteria criteria = createEntityCriteria();
        List<FieldMappingToView> fieldMappings = (List<FieldMappingToView>) criteria.list();
        //for(FieldMappingToView s : fieldMappings){
         //   Hibernate.initialize(s.getArrayView());
        //    Hibernate.initialize(s.getfield());
        //}
        logger.info("HQL finished findAll FieldMappingToView");
        return fieldMappings;
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMappingToView> findByArrayViewID (int id) {
        logger.info("HQL started findByArrayViewID FieldMappingToView");
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.sqlRestriction("{alias}.arrayViewID = "+id));
        List<FieldMappingToView> fieldMappings = (List<FieldMappingToView>) criteria.list();
        //for(FieldMappingToView s : fieldMappings){
        //    Hibernate.initialize(s.getArrayView());
        //    Hibernate.initialize(s.getfield());
        //}
        logger.info("HQL finished findByArrayViewID FieldMappingToView");
        return fieldMappings;
    }
        
}
