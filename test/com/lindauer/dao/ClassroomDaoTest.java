package com.lindauer.dao;

import com.lindauer.model.Classroom;
import com.lindauer.model.Student;
import com.lindauer.model.Teacher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClassroomDaoTest {

    @Test
    public void lookupClassroomFindsClassroom() throws Exception {
        Classroom classroom = ClassroomDao.getClassroom(101);
        System.out.println(classroom);
        assertEquals(101, classroom.getId());

        Teacher teacher = classroom.getTeacher();
        assertEquals("swong", teacher.getUserName());
        assertEquals("Stacy", teacher.getFirstName());
        assertEquals("Wong", teacher.getLastName());

        List<Student> students = classroom.getStudents();
        assertEquals(11, students.size());

        assertEquals("Reading Room", classroom.getName());
    }

    @Test
    public void lookupBadClassroomReturnsNull() throws Exception {
        Classroom classroom = ClassroomDao.getClassroom(-5);
        assertNull(classroom);
    }

    @Test
    public void getClassroomsForTeacherReturnsCorrectValues() throws Exception {
        Teacher teacher = new Teacher(5, "pferguson", "password", "Paul", "Ferguson");
        List<Classroom> classrooms = ClassroomDao.getClassroomsForTeacher(teacher);
        assertEquals(1, classrooms.size());
        thenClassroomsContain(classrooms, "Drama Room");
    }

    @Test
    public void getClassroomsForUnknownTeacherReturnsNull() throws Exception {
        Teacher teacher = new Teacher(0, "notreal", "not-password", "not-Paul", "not-Ferguson");
        List<Classroom> classrooms = ClassroomDao.getClassroomsForTeacher(teacher);
        assertTrue(classrooms == null || classrooms.size() == 0);
    }

    @Test
    public void getClassroomsForStudentReturnsCorrectValues() throws Exception {
        Student student = new Student(6, "dkim", "password", "Dylan", "Kim");
        List<Classroom> classrooms = ClassroomDao.getClassroomsForStudent(student);
        assertEquals(2, classrooms.size());
        thenClassroomsContain(classrooms, "Reading Room");
        thenClassroomsContain(classrooms, "Drama Room");
    }

    @Test
    public void getClassroomsForUnknownStudentReturnsNull() throws Exception {
        Student student = new Student(9999, "not-dkim", "da-password", "Santa", "Claus");
        List<Classroom> classrooms = ClassroomDao.getClassroomsForStudent(student);
        assertTrue(classrooms == null || classrooms.size() == 0);
    }

    private void thenClassroomsContain(List<Classroom> classrooms, String name) {
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(name))
                return;
        }
        fail("didn't find " + name + " in " + classrooms);
    }

}