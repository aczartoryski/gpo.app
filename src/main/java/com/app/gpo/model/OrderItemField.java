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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Entity
@Table(name="orderitemfield")
public class OrderItemField implements Serializable {
 
    @Id
    @Column(name = "orderItemfieldID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemfieldID;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fieldID")
    private Field field;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orderItemID")
    private OrderItem orderItem;
    
    @Column(name = "fieldText", nullable = false)
    private String fieldText;
    
    /**
     * Default empty conrtuctor
     */
    public OrderItemField() {}
    
    /**
     * Constructor initializing orderItemfieldID only
     * @param orderItemfieldID 
     */
    public OrderItemField (int orderItemfieldID) {
        this.orderItemfieldID = orderItemfieldID;
    }
    
    /**
     * Constructor which initializes all columns
     * @param orderItemfieldID
     * @param field
     * @param orderItem
     * @param fieldText 
     */
    public OrderItemField (int orderItemfieldID, Field field, OrderItem orderItem, String fieldText ) {
        this.orderItemfieldID = orderItemfieldID;
        this.field = field;
        this.orderItem = orderItem;
        this.fieldText = fieldText;
    }
    
    public int getorderItemfieldID() {
        return orderItemfieldID;
    }
 
    public void setorderItemfieldID(int id) {
        this.orderItemfieldID = id;
    }
    
    public Field getfield() {
        return field;
    }
 
    public void setfield(Field field) {
        this.field = field;
    }
    
    public OrderItem getorderItem() {
        return orderItem;
    }
 
    public void setorderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
    
    public String getfieldText() {
        return fieldText;
    }
 
    public void setfieldText(String fieldText) {
        this.fieldText = fieldText;
    }
}
