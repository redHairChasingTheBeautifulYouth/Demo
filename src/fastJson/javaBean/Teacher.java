package fastJson.javaBean;
/*
 *一句话描述该类作用:
 *@Author:
 *LB
 *
 */

import fastJson.javaBean.Course;
import fastJson.javaBean.Student;

import java.util.ArrayList;
import java.util.List;

public class Teacher {


    private String teacherName;

    private Integer teacherAge;

    private Course course;

    private List<Student> students = new ArrayList<>();

    public Teacher(String teacherName, Integer teacherAge, Course course, List<Student> students) {
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
        this.course = course;
        this.students = students;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
