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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Entity
@Table(name="productionslot")
public class ProductionSlot implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productionSlotID", nullable = false)
    private int productionSlotID;
 
    @Size(min=3, max=255)
    @Column(name = "productionSlotDescription", nullable = false)
    private String productionSlotDescription;
    
    @Column(name = "productionSlotNumber", nullable = false)
    private Integer productionSlotNumber;
    
    public int getproductionSlotID() {
        return productionSlotID;
    }
 
    public void setproductionSlotID(int id) {
        this.productionSlotID = id;
    }
 
    public String getproductionSlotDescription() {
        return productionSlotDescription;
    }
 
    public void setproductionSlotDescription(String name) {
        this.productionSlotDescription = name;
    }
    
    public Integer getproductionSlotNumber() {
        return productionSlotNumber;
    }
 
    public void setproductionSlotNumber(Integer number) {
        this.productionSlotNumber = number;
    }
  
}
