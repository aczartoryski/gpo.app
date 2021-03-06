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
@Table(name="orderstatus")
public class OrderStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderStatusID", nullable = false)
    private int orderStatusID;
 
    @Size(min=3, max=100)
    @Column(name = "orderStatusName", nullable = false)
    private String orderStatusName;
    
    public int getorderStatusID() {
        return orderStatusID;
    }
 
    public void setorderStatusID(int id) {
        this.orderStatusID = id;
    }
 
    public String getorderStatusName() {
        return orderStatusName;
    }
 
    public void setorderStatusName(String name) {
        this.orderStatusName = name;
    }
    
  
}
