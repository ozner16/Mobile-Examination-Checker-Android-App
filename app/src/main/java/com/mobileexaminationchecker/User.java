package com.mobileexaminationchecker;

public class User {

    String exam_code, exam_date_created, date_submitted, instructor;
    long score, no_of_items;

    public User(){}

    public User(String exam_code, long no_of_items, String exam_date_created, String date_submitted, String instructor, long score) {
        this.exam_code = exam_code;
        this.no_of_items = no_of_items;
        this.exam_date_created = exam_date_created;
        this.date_submitted = date_submitted;
        this.instructor = instructor;
        this.score = score;
    }

    public String getExam_code() {
        return exam_code;
    }

    public void setExam_code(String exam_code) {
        this.exam_code = exam_code;
    }

    public long getNo_of_items() {
        return no_of_items;
    }

    public void setNo_of_items(long no_of_items) {
        this.no_of_items = no_of_items;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
