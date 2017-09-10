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

import com.app.gpo.model.OrderStatus;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("orderStatusDAO")
public class OrderStatusDAO extends AbstractDao<Integer, OrderStatus> {
    private static final Logger logger = Logger.getLogger(OrderStatusDAO.class);
    public OrderStatus find (int id) {
        logger.info("HQL started find OrderStatus");
        return getByKey(id);
    }
    
    public OrderStatus findByName (String statusName) {
        logger.info("HQL started findByName OrderStatus");
        Query query = getSession().createQuery("from OrderStatus as oS where oS.orderStatusName = :orderStatusName)");
        query.setString("orderStatusName", statusName);
        logger.info("HQL finished findByName OrderStatus");
        return (OrderStatus) query.uniqueResult();

    }
    
    public OrderStatus findIdByName (String statusName) {
        logger.info("HQL started findIdByName OrderStatus");
        Query query = getSession().createQuery("from OrderStatus as oS where oS.orderStatusName = :orderStatusName)");
        query.setString("orderStatusName", statusName);
        OrderStatus orderStatus = (OrderStatus) query.uniqueResult();
        logger.info("HQL finished findIdByName OrderStatus");
        return orderStatus;
    }
    
    public void save (OrderStatus orderStatus) {

        logger.info("HQL started save OrderStatus");
        persist(orderStatus);
        logger.info("HQL finished save OrderStatus");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete OrderStatus");
        Query query = getSession().createSQLQuery("delete from orderStatus where orderStatusID = :orderStatusID");
        query.setString("orderStatusID", Integer.toString(id));
        query.executeUpdate();
        logger.info("HQL finished delete OrderStatus");
    }
 
    @SuppressWarnings("unchecked")
    public List<OrderStatus> findAll() {
        logger.info("HQL started findAll OrderStatus");
        Criteria criteria = createEntityCriteria();
        logger.info("HQL finished findAll OrderStatus");
        return (List<OrderStatus>) criteria.list();
    }
}
