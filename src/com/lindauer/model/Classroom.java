package com.lindauer.model;

import java.util.Collections;
import java.util.List;

/**
 * @author clindauer
 * @since 10/20/14
 */
public class Classroom {

    private int roomID;
    private String name;
    private Teacher teacher;
    private List<Student> students;

    public Classroom (int id, String name, Teacher teacher, List<Student> students) {
        this.roomID = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return roomID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public String toString() {
        return "Classroom {" +
                "roomID=" + roomID +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}
