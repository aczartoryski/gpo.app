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

import com.app.gpo.model.OrderItem;
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
@Repository("orderItemDAO")
public class OrderItemDAO  extends AbstractDao<Integer, OrderItem> {
    
    public OrderItem find (int id) {
        OrderItem orderItem = getByKey(id);
        Hibernate.initialize(orderItem.getorderItemFields());
        return orderItem;
    }
 
    public void save (OrderItem orderItem) {
        persist(orderItem);
    }
    
    public String saveNew (OrderItem orderItem) {
        return addNew(orderItem);
    }
    
    public void update (OrderItem orderItem) throws HibernateException  {
        try {
           saveUpdate(orderItem);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
    }
 
    public void delete (int id) {
        Query query = getSession().createSQLQuery("delete from orderItem where orderItemID = :orderItemID");
        query.setString("orderItemID", Integer.toString(id));
        query.executeUpdate();
        Query query2 = getSession().createSQLQuery("delete from orderItemField where orderItemID = :orderItemID");
        query2.setString("orderItemID", Integer.toString(id));
        query2.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<OrderItem> findAll() {
        Criteria criteria = createEntityCriteria();
        List<OrderItem> orderItems = (List<OrderItem>) criteria.list();
        for(OrderItem s : orderItems){
            Hibernate.initialize(s.getorderStatus());
            Hibernate.initialize(s.getorderItemFields());
        }
        return orderItems;
    }
    
}
