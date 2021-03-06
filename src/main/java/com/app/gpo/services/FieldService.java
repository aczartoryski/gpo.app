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

import com.app.gpo.dao.FieldDAO;
import com.app.gpo.model.Field;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */

@Service("fieldService")
@Transactional
public class FieldService {
    
    @Autowired
    private FieldDAO dao;
    
    public List<Field> findAll() {
        return dao.findAll();
    }
    
    public List<Field> findAllNotAssignToProductSlot (int id) {
        return dao.findAllNotAssignToProductSlot(id);
    }
 
    public List<Field> findAllNotAssignToArrayView (int id) {
        return dao.findAllNotAssignToArrayView(id);
    }
    
    public List<Field> findAllAssignToArrayView (int id) {
        return dao.findAllAssignToArrayView(id);
    }
    
    public Field find(int id) {
        return dao.find(id);
    }
    
    public Field findByfieldOriginID(String id) {
        return dao.findByfieldOriginID(id);
    }
    
    public void save (Field field) {
        dao.save(field);
    }
    
    public String saveNew (Field field) {
        return dao.saveNew(field);
    }
    
    public void update (Field field) {
        dao.update(field);
    }
    
    public void delete (int id) {
        dao.delete(id);
    }
    
}
