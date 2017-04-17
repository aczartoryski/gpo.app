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
@Table(name="fieldmapping")
public class FieldMapping implements Serializable {
  
    @Id
    @Column(name = "fieldMappingID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldMappingID;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fieldID")
    private Field field;
 
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productionSlotID")
    private ProductionSlot productionSlot;
    
    @Column(name = "fieldMappingOrder", nullable = false)
    private Integer fieldMappingOrder;
    
    @Column(name = "fieldShownInMainTable", nullable = false)
    private Boolean fieldShownInMainTable;
    
    /**
     * Default empty constructor
     */
    public FieldMapping () {}
    
    /**
     * Constructor initializes fieldMappingID only
     * @param id 
     */
    public FieldMapping (int id) {
        this.fieldMappingID = id;
    }
    
    /**
     * Constructor which initializes all columns
     * @param fieldMappingID
     * @param field
     * @param productionSlot
     * @param fieldMappingOrder
     * @param fieldShownInMainTable 
     */
    public FieldMapping (int fieldMappingID, Field field, ProductionSlot productionSlot, Integer fieldMappingOrder, Boolean fieldShownInMainTable) {
        this.fieldMappingID = fieldMappingID;
        this.field = field;
        this.productionSlot = productionSlot;
        this.fieldMappingOrder = fieldMappingOrder;
        this.fieldShownInMainTable = fieldShownInMainTable;
    }
    
    public int getfieldMappingID() {
        return fieldMappingID;
    }
 
    public void setfieldMappingID(int id) {
        this.fieldMappingID = id;
    }
    
    public Field getfield() {
        return field;
    }
 
    public void setfield(Field field) {
        this.field = field;
    }
    
    public ProductionSlot getproductionSlot() {
        return productionSlot;
    }
 
    public void setproductionSlot(ProductionSlot productionSlot) {
        this.productionSlot = productionSlot;
    }
 
    public Integer getfieldMappingOrder() {
        return fieldMappingOrder;
    }
 
    public void setfieldMappingOrder(Integer number) {
        this.fieldMappingOrder = number;
    }
    
    public Boolean getfieldShownInMainTable() {
        return fieldShownInMainTable;
    }
 
    public void setfieldShownInMainTable(Boolean value) {
        this.fieldShownInMainTable = value;
    }
  
}

