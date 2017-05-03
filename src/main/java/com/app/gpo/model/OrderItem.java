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
package com.app.gpo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Entity
@Table(name="orderItem")
public class OrderItem implements Serializable {
  
    @Id
    @Column(name = "orderItemID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemID;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orderStatusID")
    private OrderStatus orderStatus;
    
    @Column(name = "orderItemName", nullable = false)
    private String orderItemName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "orderStatusDate", nullable = false, length=10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderStatusDate;
    
            
    @Temporal(TemporalType.DATE)
    @Column(name = "orderItemDueDate", nullable = false, length=10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderItemDueDate;
    
    @Column(name = "orderNumber", nullable = false)
    private String orderNumber;
    
    @OneToMany(targetEntity = OrderItemField.class, fetch = FetchType.LAZY, mappedBy = "orderItem", cascade=CascadeType.ALL)
    private Set<OrderItemField> orderItemFields = new HashSet<OrderItemField>(0);
    /**
     * Default empty constructor
     */
    public OrderItem () {}
    
    /**
     * Constructor initializes orderItemID only
     * @param id
     */
    public OrderItem (int id) {
        this.orderItemID = id;
    }
    
    /**
     * Constructor which initializes all columns
     * @param orderItemID
     * @param orderStatus
     * @param orderItemName
     * @param orderStatusDate
     * @param orderItemDueDate
     * @param orderNumber
     * @param orderItemFields
     */
    public OrderItem (int orderItemID, OrderStatus orderStatus, String orderItemName, Date orderStatusDate, Date orderItemDueDate, String orderNumber, Set<OrderItemField> orderItemFields) {
        this.orderItemID = orderItemID;
        this.orderItemName = orderItemName;
        this.orderStatus = orderStatus;
        this.orderStatusDate = orderStatusDate;
        this.orderItemDueDate = orderItemDueDate;
        this.orderNumber = orderNumber;
        this.orderItemFields = orderItemFields;
    }

    public int getorderItemID() {
        return orderItemID;
    }
 
    public void setorderItemID(int id) {
        this.orderItemID = id;
    }

    public OrderStatus getorderStatus() {
        return orderStatus;
    }
 
    public void setorderStatus(OrderStatus object) {
        this.orderStatus = object;
    }

    public String getorderItemName() {
        return orderItemName;
    }
 
    public void setorderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }
    
    public Date getorderStatusDate() {
        return orderStatusDate;
    }
 
    public void setorderStatusDate(Date orderStatusDate) {
        this.orderStatusDate = orderStatusDate;
    }
    
    public Date getorderItemDueDate() {
        return orderItemDueDate;
    }
 
    public void setorderItemDueDate(Date orderItemDueDate) {
        this.orderItemDueDate = orderItemDueDate;
    }
    
        
    public String getorderNumber() {
        return this.orderNumber;
    }
 
    public void setorderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public Set<OrderItemField> getorderItemFields() {
	return this.orderItemFields;
    }

    public void setorderItemFields(Set<OrderItemField> orderItemFields) {
	this.orderItemFields = orderItemFields;
    }
}
