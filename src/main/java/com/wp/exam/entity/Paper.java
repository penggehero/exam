package com.wp.exam.entity;

public class Paper {
    private Integer id; // 序号
    private String name; // 试卷名称
    private Integer mark; // 分值
    private Integer number; // 数量
    private Integer time; // 考试时长(分钟)
    private String teacher; // 出卷老师
    private String ext; // 备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                ", number=" + number +
                ", time=" + time +
                ", teacher='" + teacher + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
