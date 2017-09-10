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

import com.app.gpo.model.ProductionSlot;
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
@Repository("productionSlotDAO")
public class ProductionSlotDAO extends AbstractDao<Integer, ProductionSlot> {
    private static final Logger logger = Logger.getLogger(ProductionSlotDAO.class);
   public ProductionSlot find (int id) {
       logger.info("HQL started find ProductionSlot");
        ProductionSlot productionSlot = getByKey(id);
        //Hibernate.initialize(productionSlot.getFieldMappings());
       logger.info("HQL finished find ProductionSlot");
        return productionSlot;
    }
 
    public void save (ProductionSlot productionSlot) {
        logger.info("HQL started save ProductionSlot");
       persist(productionSlot);
        logger.info("HQL finished save ProductionSlot");
    }
    
    public void update (ProductionSlot productionSlot) throws HibernateException  {
        logger.info("HQL started update ProductionSlot");
       try {
           saveUpdate(productionSlot);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
        logger.info("HQL finished update ProductionSlot");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete ProductionSlot");
        Query query = getSession().createSQLQuery("delete from productionSlot where productionSlotID = :productionSlotID");
        query.setString("productionSlotID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete ProductionSlot");
    }
 
    @SuppressWarnings("unchecked")
    public List<ProductionSlot> findAll() {
        logger.info("HQL started findAll ProductionSlot");
        Criteria criteria = createEntityCriteria();
        List<ProductionSlot> productionSlots = (List<ProductionSlot>) criteria.list(); 
        //for(ProductionSlot productionSlot : productionSlots){
        //    Hibernate.initialize(productionSlot.getFieldMappings());
        //}
        logger.info("HQL finished findAll ProductionSlot");
        return productionSlots;
    }
}
