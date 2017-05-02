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
package com.app.gpo.services;

import com.app.gpo.dao.FieldMappingDAO;
import com.app.gpo.dao.ProductionSlotDAO;
import com.app.gpo.model.FieldMapping;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Service("fieldMappingService")
@Transactional
public class FieldMappingService {
   
    @Autowired
    private FieldMappingDAO dao;
    
    public List<FieldMapping> findAll() {
        return dao.findAll();
    }
 
    public FieldMapping find(int id) {
        return dao.find(id);
    }
    
    public List<FieldMapping> findByProductionSlotID (int id) {
        return dao.findByProductionSlotID(id);
    }
    
    public void save (FieldMapping fieldMapping) {
        dao.save(fieldMapping);
    }
    
    public void delete (int id) {
        dao.delete(id);
    }
    
    public void deleteByProductionSlotID(Integer productionSlotID) {
        dao.deleteByProductionSlotID(productionSlotID);
    }
    
}
