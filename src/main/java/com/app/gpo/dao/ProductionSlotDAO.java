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
    
   public ProductionSlot find (int id) {
        ProductionSlot productionSlot = getByKey(id);
        Hibernate.initialize(productionSlot.getFieldMappings());
        return productionSlot;
    }
 
    public void save (ProductionSlot productionSlot) {
        persist(productionSlot);
    }
    
    public void update (ProductionSlot productionSlot) throws HibernateException  {
        try {
           saveUpdate(productionSlot);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
    }
 
    public void delete (int id) {
        Query query = getSession().createSQLQuery("delete from productionSlot where productionSlotID = :productionSlotID");
        query.setString("productionSlotID", Integer.toString(id));
        query.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<ProductionSlot> findAll() {
        Criteria criteria = createEntityCriteria();
        List<ProductionSlot> productionSlots = (List<ProductionSlot>) criteria.list(); 
        for(ProductionSlot productionSlot : productionSlots){
            Hibernate.initialize(productionSlot.getFieldMappings());
        }
        return productionSlots;
    }
}
