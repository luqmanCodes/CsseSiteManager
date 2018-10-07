package com.buzzhive.luqman.sitemanager;

import com.buzzhive.luqman.definedClases.DateHelper;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class TestDateHelper {

    @Test
    public void testDateValidation() {
        assertEquals(false,DateHelper.validateDate(DateHelper.getNowSQLDate()));
    }
    @Test
    public void testNowSqlDate() {
        java.sql.Date testDate = new java.sql.Date(new java.util.Date().getTime());
        assertEquals(testDate,DateHelper.getNowSQLDate());
        assertEquals(java.sql.Date.class,DateHelper.getNowSQLDate().getClass());
    }
    @Test
    public void testDateFromString() throws ParseException {
        java.sql.Date testDate = new java.sql.Date(new java.util.Date().getTime());
        assertEquals(testDate,DateHelper.getDateFromString("08/10/2018"));
        assertEquals(java.sql.Date.class,DateHelper.getNowSQLDate().getClass());
    }

}
