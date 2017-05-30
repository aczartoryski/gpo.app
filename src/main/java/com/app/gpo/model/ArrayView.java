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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Entity
@Table(name="arrayview")
public class ArrayView implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "arrayViewID",unique = true, nullable = false)
    private int arrayViewID;
    
    @Column(name = "arrayViewName", nullable = false)
    private String arrayViewName;
    
    @Column(name = "arrayViewDescription", nullable = false)
    private String arrayViewDescription;
    
    @Column(name = "arrayViewForClosed")
    private Boolean arrayViewForClosed;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrayView", cascade=CascadeType.ALL)
    @OrderBy ("fieldMappingToViewOrder")
    private Set<FieldMappingToView> fieldMappingToViews = new HashSet<FieldMappingToView>(0);
    
    public ArrayView () {
    }
    
    public ArrayView (int arrayViewID,String arrayViewName,String arrayViewDescription, Boolean arrayViewForClosed ) {
        this.arrayViewID = arrayViewID;
        this.arrayViewName = arrayViewName;
        this.arrayViewDescription = arrayViewDescription;
        this.arrayViewForClosed = arrayViewForClosed;
    }
    
    public ArrayView (int arrayViewID,String arrayViewName,String arrayViewDescription, Boolean arrayViewForClosed, Set<FieldMappingToView> fieldMappingToViews ) {
        this.arrayViewID = arrayViewID;
        this.arrayViewName = arrayViewName;
        this.arrayViewDescription = arrayViewDescription;
        this.arrayViewForClosed = arrayViewForClosed;
        this.fieldMappingToViews = fieldMappingToViews;
    }

    public int getArrayViewID() {
        return arrayViewID;
    }

    public void setArrayViewID(int arrayViewID) {
        this.arrayViewID = arrayViewID;
    }
    
    public String getArrayViewName() {
        return arrayViewName;
    }
 
    public void setArrayViewName(String name) {
        this.arrayViewName = name;
    }
    
    public String getArrayViewDescription() {
        return arrayViewDescription;
    }
 
    public void setArrayViewDescription(String name) {
        this.arrayViewDescription = name;
    }
    
    
    public Boolean getArrayViewForClosed() {
        if (arrayViewForClosed == null) {
            return false;
        } else {
            return arrayViewForClosed;
        }
    }
 
    public void setArrayViewForClosed(Boolean arrayViewForClosed) {
        this.arrayViewForClosed = arrayViewForClosed;
    }
    
    
    public Set<FieldMappingToView> getFieldMappingToView() {
	return this.fieldMappingToViews;
    }

    public void setFieldMappingToView(Set<FieldMappingToView> fieldMappingToViews) {
	this.fieldMappingToViews = fieldMappingToViews;
    }
 
}
