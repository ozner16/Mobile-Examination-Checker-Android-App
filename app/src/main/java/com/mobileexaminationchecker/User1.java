package com.mobileexaminationchecker;

public class User1 {

    String student_name, course_yr_sec, exam_date_created, date_submitted ;
    long score;

    public User1(){}

    public User1(String student_name, String course_yr_sec, String exam_date_created, String date_submitted, long score) {
        this.student_name = student_name;
        this.course_yr_sec = course_yr_sec;
        this.exam_date_created = exam_date_created;
        this.date_submitted = date_submitted;
        this.score = score;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCourse_yr_sec() {
        return course_yr_sec;
    }

    public void setCourse_yr_sec(String course_yr_sec) {
        this.course_yr_sec = course_yr_sec;
    }

    public String getExam_date_created() {
        return exam_date_created;
    }

    public void setExam_date_created(String exam_date_created) {
        this.exam_date_created = exam_date_created;
    }

    public String getDate_submitted() {
        return date_submitted;
    }

    public void setDate_submitted(String date_submitted) {
        this.date_submitted = date_submitted;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
