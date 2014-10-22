package com.lindauer.dao;

import com.lindauer.model.Teacher;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TeacherDaoTest {

    @Test
    public void lookupTeacherByIdFindsTeacher() throws Exception {
        Teacher teacher = TeacherDao.getTeacherByID(4);
        assertEquals("lhartmann", teacher.getUserName());
        assertEquals("Leigh", teacher.getFirstName());
        assertEquals("Hartmann", teacher.getLastName());
    }

    @Test
    public void lookupBadTeacherReturnsNull() throws Exception {
        Teacher teacher = TeacherDao.getTeacherByID(9999);
        assertNull(teacher);
    }

}
