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
 
    private int fieldID;
    private Integer fieldValueID;
    private String fieldLabel;
    private Integer fieldOriginID;
    private Set<FieldMapping> fieldMappings = new HashSet<FieldMapping>(0);
    
    
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
    public Field (int fieldID, Integer fieldOriginID, Integer fieldValueID, String fieldLabel, Set<FieldMapping> fieldMappings) {
        this.fieldID = fieldID;
        this.fieldLabel= fieldLabel;
        this.fieldValueID = fieldValueID; 
        this.fieldOriginID = fieldOriginID;
        this.fieldMappings = fieldMappings;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fieldID", nullable = false)
    public int getFieldID() {
        return fieldID;
    }
 
    public void setFieldID(int id) {
        this.fieldID = id;
    }
    
    @Column(name = "fieldOriginID", nullable = false)
    public int getFieldOriginID() {
        return fieldOriginID;
    }
 
    public void setFieldOriginID(int id) {
        this.fieldOriginID = id;
    }
    
    @Column(name = "fieldValueID", nullable = false)
    public int getFieldValueID() {
        return fieldValueID;
    }
 
    public void setFieldValueID(int id) {
        this.fieldValueID = id;
    }
    
    @Column(name = "fieldLabel", nullable = false)
    public String getFieldLabel() {
        return fieldLabel;
    }
 
    public void setFieldLabel(String name) {
        this.fieldLabel = name;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    public Set<FieldMapping> getFieldMappings() {
	return (Set<FieldMapping>) this.fieldMappings;
    }

    public void setFieldMappings(Set<FieldMapping> fieldMappings) {
	this.fieldMappings = fieldMappings;
    }
    
}
