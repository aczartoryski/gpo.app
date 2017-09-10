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

import com.app.gpo.model.OrderItemField;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("orderItemFieldDAO")
public class OrderItemFieldDAO   extends AbstractDao<Integer, OrderItemField> {
    private static final Logger logger = Logger.getLogger(OrderItemFieldDAO.class);
   public OrderItemField find (int id) {
       logger.info("HQL started find OrderItemField");
       OrderItemField orderItemField = getByKey(id);
       Hibernate.initialize(orderItemField.getorderItem());
       Hibernate.initialize(orderItemField.getfield());
       logger.info("HQL finished find OrderItemField");
       return orderItemField;
    }
 
    public void save (OrderItemField orderItemField) {
        logger.info("HQL finished save OrderItemField");
       persist(orderItemField);
        logger.info("HQL finished save OrderItemField");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete OrderItemField");
        Query query = getSession().createSQLQuery("delete from orderItemField where orderItemFieldID = :orderItemFieldID");
        query.setString("orderItemFieldID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete OrderItemField");
    }
 
    @SuppressWarnings("unchecked")
    public List<OrderItemField> findAll() {
        logger.info("HQL started findAll OrderItemField");
        Criteria criteria = createEntityCriteria();
        List<OrderItemField> orderItemFields = criteria.list();
        //for(OrderItemField s : orderItemFields){
        //    Hibernate.initialize(s.getfield());
        //    Hibernate.initialize(s.getorderItem());
        //}
        logger.info("HQL started findAll OrderItemField");
        return orderItemFields;
    }
    
}
