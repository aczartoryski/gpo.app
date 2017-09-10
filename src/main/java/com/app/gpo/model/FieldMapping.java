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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size=100)
    @JoinColumn(name = "productionSlotID")
    private ProductionSlot productionSlot;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @BatchSize(size=100)
    @JoinColumn(name = "fieldID")
    private Field field;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fieldMappingID", nullable = false)
    private int fieldMappingID;
    
    @Column(name = "fieldMappingOrder", nullable = false)
    private Integer fieldMappingOrder;
    
    public FieldMapping () {
    }
    
       
    
    public ProductionSlot getProductionSlot() {
        return this.productionSlot;
    }
    
    public void setProductionSlot(ProductionSlot productionSlot) {
        this.productionSlot = productionSlot;
    }
    
    
    public Field getfield() {
        return this.field;
    }
    
    public void setfield(Field field) {
        this.field = field;
    }
    
    
    public int getfieldMappingID() {
        return fieldMappingID;
    }
 
    public void setfieldMappingID(int id) {
        this.fieldMappingID = id;
    }
    
    
    public Integer getfieldMappingOrder () {
        return this.fieldMappingOrder;
    }
    
    public void setfieldMappingOrder(Integer number) {
        this.fieldMappingOrder = number;
    }

  
}

