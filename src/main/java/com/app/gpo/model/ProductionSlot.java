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
@Table(name="productionslot")
public class ProductionSlot implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "productionSlotID",unique = true, nullable = false)
    private int productionSlotID;
    
    @Column(name = "productionSlotDescription", nullable = false)
    private String productionSlotDescription;
    
    @Column(name = "productionSlotNumber", nullable = false)
    private Integer productionSlotNumber;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productionSlot", cascade=CascadeType.ALL)
    @OrderBy ("fieldMappingOrder")
    private Set<FieldMapping> fieldMappings = new HashSet<FieldMapping>(0);
    
    public ProductionSlot () {
    }
    
    public ProductionSlot (int productionSlotID,String productionSlotDescription,Integer productionSlotNumber ) {
        this.productionSlotID = productionSlotID;
        this.productionSlotNumber = productionSlotNumber;
        this.productionSlotDescription = productionSlotDescription;
    }
    
    public ProductionSlot (int productionSlotID,String productionSlotDescription,Integer productionSlotNumber,Set<FieldMapping> fieldMappings ) {
        this.productionSlotID = productionSlotID;
        this.productionSlotNumber = productionSlotNumber;
        this.productionSlotDescription = productionSlotDescription;
        this.fieldMappings = fieldMappings;
    }
    
    
    public int getproductionSlotID() {
        return productionSlotID;
    }

    public void setproductionSlotID(int productionSlotID) {
        this.productionSlotID = productionSlotID;
    }
    
    
    public String getProductionSlotDescription() {
        return productionSlotDescription;
    }
 
    public void setProductionSlotDescription(String name) {
        this.productionSlotDescription = name;
    }
    
    
    public Integer getProductionSlotNumber() {
        return productionSlotNumber;
    }
 
    public void setProductionSlotNumber(Integer number) {
        this.productionSlotNumber = number;
    }
    
    
    public Set<FieldMapping> getFieldMappings() {
	return this.fieldMappings;
    }

    public void setFieldMappings(Set<FieldMapping> fieldMappings) {
	this.fieldMappings = fieldMappings;
    }
 
}
