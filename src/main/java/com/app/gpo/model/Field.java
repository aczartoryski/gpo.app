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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Entity
@Table(name="field")
public class Field implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fieldID", nullable = false)
    private int fieldID;
    
    @Column(name = "fieldValueID")
    private String fieldValueID;
    
    @Column(name = "fieldLabel", nullable = false)
    private String fieldLabel;
    
    @Column(name = "fieldOriginID", nullable = false)
    private String fieldOriginID;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    @BatchSize(size=100)
    //@Fetch(FetchMode.JOIN)
    private Set<FieldMapping> fieldMappings = new HashSet<FieldMapping>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    @BatchSize(size=100)
    //@Fetch(FetchMode.JOIN)
    private Set<FieldMappingToView> fieldMappingToViews = new HashSet<FieldMappingToView>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    @BatchSize(size=100)
    private Set<OrderItemField> orderItemFields = new HashSet<OrderItemField>(0);
    
    @Column(name = "fieldShownInMainTable")
    private Boolean fieldShownInMainTable;
    
    
    /**
     * Default empty conrtuctor
     */
    public Field () {}
    
    /**
     * Constructor initializing fieldID only
     * @param id 
     */
    public Field (int id) {
        this.fieldID = id;
    }
    
    /**
     * Constructor which initializes all columns
     * @param fieldID
     * @param fieldOriginID
     * @param fieldValueID
     * @param fieldLabel 
     * @param fieldMappings 
     */
    public Field (int fieldID, String fieldOriginID, String fieldValueID, String fieldLabel, Set<FieldMapping> fieldMappings) {
        this.fieldID = fieldID;
        this.fieldLabel= fieldLabel;
        this.fieldValueID = fieldValueID; 
        this.fieldOriginID = fieldOriginID;
        this.fieldMappings = fieldMappings;
    }
    
    
    public int getFieldID() {
        return fieldID;
    }
 
    public void setFieldID(int id) {
        this.fieldID = id;
    }
    
    
    public String getFieldOriginID() {
        return fieldOriginID;
    }
 
    public void setFieldOriginID(String id) {
        this.fieldOriginID = id;
    }
    
    
    public String getFieldValueID() {
        return fieldValueID;
    }
 
    public void setFieldValueID(String id) {
        this.fieldValueID = id;
    }
    
    
    public String getFieldLabel() {
        return fieldLabel;
    }
 
    public void setFieldLabel(String name) {
        this.fieldLabel = name;
    }
    
    
    public Set<FieldMapping> getFieldMappings() {
	return (Set<FieldMapping>) this.fieldMappings;
    }

    public void setFieldMappings(Set<FieldMapping> fieldMappings) {
	this.fieldMappings = fieldMappings;
    }
    
    
    public Set<OrderItemField> getOrderItemFields() {
        return this.orderItemFields;
    }
    
    public void setOrderItemFields (Set<OrderItemField> orderItemFields) {
        this.orderItemFields = orderItemFields;
    }
    
    
    public Boolean getfieldShownInMainTable() {
        return fieldShownInMainTable;
    }
 
    public void setfieldShownInMainTable(Boolean value) {
        this.fieldShownInMainTable = value;
    }
    
}
