package com.lindauer.model;

import com.lindauer.dao.ClassroomDao;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.fail;

public class ClassroomTest {
    private Classroom classroom;

    /**
     * don't go messin' with my internal dataz
     */
    @Test (expected= UnsupportedOperationException.class)
    public void studentsAreUnmodifiable() throws Exception {
        givenAClassroom();
        thenStudentsAreUnmodifiable();
    }

    private void thenStudentsAreUnmodifiable() throws UnsupportedOperationException {
        classroom.getStudents().add(new Student(99, "clindauer", "password", "Christine", "Lindauer"));
        fail("Exception should have been thrown!");
    }

    private void givenAClassroom() throws IOException, SQLException {
        classroom = ClassroomDao.getClassroom(101);
    }
}