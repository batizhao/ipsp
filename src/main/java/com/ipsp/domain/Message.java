package com.ipsp.domain;

public class Message {

    private int userId;
    private String coursewareCode;
    private String Result;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCoursewareCode() {
        return coursewareCode;
    }

    public void setCoursewareCode(String coursewareCode) {
        this.coursewareCode = coursewareCode;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
