package com.lindauer.dao;

import com.lindauer.model.Student;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentDaoTest {

    @Test
    public void lookupStudentByIdFindsStudent() throws Exception {
        Student student = StudentDao.getStudentByID(22);
        assertEquals("jlah", student.getUserName());
        assertEquals("Jane", student.getFirstName());
        assertEquals("Lah", student.getLastName());
    }

    @Test
    public void lookupUnknownStudentReturnsNull() throws Exception {
        Student student = StudentDao.getStudentByID(99999);
        assertNull(student);
    }
}
