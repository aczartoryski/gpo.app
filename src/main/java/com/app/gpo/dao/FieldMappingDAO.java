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

import com.app.gpo.model.FieldMapping;
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
@Repository("fieldMappingDAO")
public class FieldMappingDAO extends AbstractDao<Integer, FieldMapping> {
    
   public FieldMapping find (int id) {
        FieldMapping fieldMapping = getByKey(id);
        Hibernate.initialize(fieldMapping.getProductionSlot());
        Hibernate.initialize(fieldMapping.getfield());
        return fieldMapping;
   }
 
    public void save (FieldMapping fieldMapping) {
        persist(fieldMapping);
    }
 
    public void delete (int id) {
        Query query = getSession().createSQLQuery("delete from fieldMapping where fieldMappingID = :fieldMappingID");
        query.setString("fieldMappingID", Integer.toString(id));
        query.executeUpdate();
    }
 
    public void deleteByProductionSlotID (int id) {
       Query query = getSession().createSQLQuery("delete from fieldMapping where productionSlotID = :productionSlotID");
       query.setString("productionSlotID", Integer.toString(id));
       query.executeUpdate(); 
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMapping> findAll() {
        Criteria criteria = createEntityCriteria();
        List<FieldMapping> fieldMappings = (List<FieldMapping>) criteria.list();
        for(FieldMapping s : fieldMappings){
            Hibernate.initialize(s.getProductionSlot());
            Hibernate.initialize(s.getfield());
        }
        return fieldMappings;
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMapping> findByProductionSlotID (int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.sqlRestriction("{alias}.productionSlotID = "+id));
        List<FieldMapping> fieldMappings = (List<FieldMapping>) criteria.list();
        for(FieldMapping s : fieldMappings){
            Hibernate.initialize(s.getProductionSlot());
            Hibernate.initialize(s.getfield());
        }
        return fieldMappings;
    }
    
    @SuppressWarnings("unchecked")
    public List<FieldMapping> findByNotProductionSlotID (int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.sqlRestriction("{alias}.productionSlotID = "+id));
        List<FieldMapping> fieldMappings = (List<FieldMapping>) criteria.list();
        for(FieldMapping s : fieldMappings){
            Hibernate.initialize(s.getProductionSlot());
            Hibernate.initialize(s.getfield());
        }
        return fieldMappings;
    }
    
}
