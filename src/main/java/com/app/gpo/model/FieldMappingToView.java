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
@Table(name="fieldmappingtoview")
public class FieldMappingToView implements Serializable {
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "arrayViewID")
    private ArrayView arrayView;
    
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "fieldID")
    private Field field;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fieldMappingToViewID", nullable = false)
    private int fieldMappingToViewID;
    
    @Column(name = "fieldMappingToViewOrder", nullable = false)
    private Integer fieldMappingToViewOrder;
    
    public FieldMappingToView () {
    }
    
    public ArrayView getArrayView() {
        return this.arrayView;
    }
    
    public void setArrayView(ArrayView arrayView) {
        this.arrayView = arrayView;
    }
    
    
    public Field getfield() {
        return this.field;
    }
    
    public void setfield(Field field) {
        this.field = field;
    }
    
    
    public int getfieldMappingToViewID() {
        return fieldMappingToViewID;
    }
 
    public void setfieldMappingToViewID(int id) {
        this.fieldMappingToViewID = id;
    }
    
    
    public Integer getfieldMappingToViewOrder () {
        return this.fieldMappingToViewOrder;
    }
    
    public void setfieldMappingToViewOrder(Integer number) {
        this.fieldMappingToViewOrder = number;
    }

  
}

