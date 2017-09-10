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

import com.app.gpo.model.ArrayView;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("arrayViewDAO")
public class ArrayViewDAO extends AbstractDao<Integer, ArrayView> {
    private static final Logger logger = Logger.getLogger(ArrayViewDAO.class);

   public ArrayView find (int id) {
       logger.info("HQL started find ArrayView");
        ArrayView arrayView = getByKey(id);
        // Hibernate.initialize(arrayView.getFieldMappingToView());
       logger.info("HQL finished find ArrayView");
        return arrayView;
    }
 
    public void save (ArrayView arrayView) {

        logger.info("HQL started save ArrayView");
       persist(arrayView);
        logger.info("HQL finished save ArrayView");
    }
    
    public void update (ArrayView arrayView) throws HibernateException  {
        logger.info("HQL started update ArrayView");
        try {
           saveUpdate(arrayView);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
        logger.info("HQL finished update ArrayView");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete ArrayView");
        Query query = getSession().createSQLQuery("delete from arrayView where arrayViewID = :arrayViewID");
        query.setString("arrayViewID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete ArrayView");
    }
 
    @SuppressWarnings("unchecked")
    public List<ArrayView> findAll() {
        logger.info("HQL started findAll ArrayView");
        Criteria criteria = createEntityCriteria();
        List<ArrayView> arrayViews = (List<ArrayView>) criteria.list(); 
        //for(ArrayView arrayView : arrayViews){
        //    Hibernate.initialize(arrayView.getFieldMappingToView());
        //}
        logger.info("HQL finished findAll ArrayView");
        return arrayViews;
    }
}
