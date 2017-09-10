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
import com.app.gpo.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.JoinType;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
@Repository("orderItemDAO")
public class OrderItemDAO  extends AbstractDao<Integer, OrderItem> {
    private static final Logger logger = Logger.getLogger(OrderItemDAO.class);
    
    public OrderItem find (int id) {
        OrderItem orderItem = getByKey(id);
        Hibernate.initialize(orderItem.getorderItemFields());
        return orderItem;
    }
 
    public void save (OrderItem orderItem) {
        logger.info("HQL started save OrderItems");
        persist(orderItem);
        logger.info("HQL finished save OrderItems");
    }
    
    public String saveNew (OrderItem orderItem) {
        logger.info("HQL started saveNew OrderItems");
        return addNew(orderItem);
    }
    
    public void update (OrderItem orderItem) throws HibernateException  {
        logger.info("HQL started update OrderItems");
        try {
           saveUpdate(orderItem);
        } catch (final HibernateException e) {
            throw new HibernateException(e);
        }
        logger.info("HQL finished update OrderItems");
    }
 
    public void delete (int id) {
        logger.info("HQL started delete OrderItems");
        Query query = getSession().createSQLQuery("delete from orderItem where orderItemID = :orderItemID");
        query.setString("orderItemID", Integer.toString(id));
        query.executeUpdate();
        Query query2 = getSession().createSQLQuery("delete from orderItemField where orderItemID = :orderItemID");
        query2.setString("orderItemID", Integer.toString(id));
        query2.executeUpdate();
        logger.info("HQL finished delete OrderItems");
    }
    
    public OrderItem findByOrderNumber (String orderNumber) {
        logger.info("HQL started findByOrderNumber OrderItems");
        Query query = getSession().createSQLQuery("select from orderItem where orderNumber = :orderNumber");
        query.setString("orderNumber", orderNumber);
        OrderItem orderItem = (OrderItem) query.uniqueResult();
        Hibernate.initialize(orderItem.getorderItemFields());
        logger.info("HQL finished findByOrderNumber OrderItems");
        return orderItem;
    }
    
    public boolean isInDbByOrderNumber (String orderNumber) {
        logger.info("HQL started isInDbByOrderNumber OrderItems");
        Criteria criteria = getSession().createCriteria(OrderItem.class);
        criteria.add(Restrictions.like("orderNumber", orderNumber+"%"));
        criteria.setProjection(Projections.rowCount());
        long count = (Long) criteria.uniqueResult();
        logger.info("DB count "+count+" for order items with order number "+orderNumber);

        if(count != 0){
            logger.info("In DB exist"+count+" order items with order number "+orderNumber);
            logger.info("HQL finished isInDbByOrderNumber OrderItems");
            return true;
        } else {
            logger.info("In DB no exist order items with order number "+orderNumber);
            logger.info("HQL finished isInDbByOrderNumber OrderItems");
            return false;
        }
    }
 
    @SuppressWarnings("unchecked")
    public List<OrderItem> findAll() {
        logger.info("HQL started findAll OrderItems");
        Criteria criteria = createEntityCriteria();
        List<OrderItem> orderItems = (List<OrderItem>) criteria.list();

        //for(OrderItem s : orderItems){
        //    Hibernate.initialize(s.getorderStatus());
        //    Hibernate.initialize(s.getorderItemFields());
        //}
        logger.info("HQL finished findAll OrderItems");
        return orderItems;
    }
    
    @SuppressWarnings("unchecked")
    public List<OrderItem> findByOrderStatus (OrderStatus orderStatus) {
        logger.info("HQL started OrderItems");
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("orderStatus",orderStatus));
        List<OrderItem> orderItems = (List<OrderItem>) criteria.list();
        //for(OrderItem s : orderItems){
         //   Hibernate.initialize(s.getorderStatus());
         //   Hibernate.initialize(s.getorderItemFields());
        //}
        logger.info("HQL finished OrderItems");
        return orderItems;
    }
    
    @SuppressWarnings("unchecked")
    public List<OrderItem> findNotOrderStatus (OrderStatus orderStatus) {
        logger.info("HQL started findNotOrderStatus OrderItems");
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.ne("orderStatus",orderStatus));
        List<OrderItem> orderItems = (List<OrderItem>) criteria.list();
        //for(OrderItem s : orderItems){
         //   Hibernate.initialize(s.getorderStatus());
         //   Hibernate.initialize(s.getorderItemFields());
        //}
        logger.info("HQL finished findNotOrderStatus OrderItems");
        return orderItems;
    }

    @SuppressWarnings("unchecked")
    public ArrayList findAll2 () {
        logger.info("HQL started findAll2 OrderItems");
        ArrayList allOrderItems = new ArrayList();
        Query query = getSession().createSQLQuery("SET @sql = NULL;\n" +
                "SET SESSION group_concat_max_len=4294967295;\n" +
                "SELECT\n" +
                "  GROUP_CONCAT(DISTINCT\n" +
                "    CONCAT(\n" +
                "      'MAX(CASE WHEN fieldID=',fieldID,' THEN fieldText END) AS f',fieldID)\n" +
                "  ) INTO @sql\n" +
                "FROM orderitemfield;\n" +
                "\n" +
                "SET @sql = CONCAT('SELECT oIF.orderItemID, oI.orderItemName, oI.orderItemDueDate, oS.orderStatusName, oI.orderStatusDate, oI.orderNumber,\n" +
                "                  ', @sql,\n" +
                "                  ' FROM orderItem oI, orderitemfield oIF, orderstatus oS WHERE oI.orderItemID = oIF.orderItemID\n" +
                "                  AND oI.orderStatusID = oS.orderStatusID\n" +
                "                  AND oS.orderStatusID <> 5\n" +
                "                  GROUP BY orderItemID');\n" +
                "\n" +
                "PREPARE stmt FROM @sql;\n" +
                "EXECUTE stmt;\n" +
                "DEALLOCATE PREPARE stmt;");

        allOrderItems = (ArrayList) query.list();

        logger.info("HQL finished findAll2 OrderItems");
        return allOrderItems;
    }
    
}
