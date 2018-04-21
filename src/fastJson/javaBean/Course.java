package fastJson.javaBean;
/*
 *一句话描述该类作用:
 *@Author:
 *LB
 *
 */

public class Course {

    private String courseName;

    private Integer code;

    public Course(String courseName, Integer code) {
        this.courseName = courseName;
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
