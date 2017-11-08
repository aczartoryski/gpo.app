package com.app.gpo.model;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductionSlotTest {
    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(ProductionSlot.class);
    }
}