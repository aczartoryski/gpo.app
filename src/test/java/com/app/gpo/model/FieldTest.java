package com.app.gpo.model;

import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FieldTest {

    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(Field.class);
    }

}