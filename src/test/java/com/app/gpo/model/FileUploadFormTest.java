package com.app.gpo.model;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileUploadFormTest {
    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(FileUploadForm.class);
    }
}