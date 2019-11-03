package com.wp.exam.entity;


import java.sql.Date;

public class Grade {
    private Integer id; // 序号
    private Integer s_id; // 学号
    private String s_name; // 学生姓名
    private Integer p_id; // 试卷序号
    private String p_name; // 试卷名称
    private Date date; // 完成时间
    private Integer s_mark; // 单选题总分
    private Integer d_mark; // 多选题总分
    private Integer mark; // 总分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getS_mark() {
        return s_mark;
    }

    public void setS_mark(Integer s_mark) {
        this.s_mark = s_mark;
    }

    public Integer getD_mark() {
        return d_mark;
    }

    public void setD_mark(Integer d_mark) {
        this.d_mark = d_mark;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", date=" + date +
                ", s_mark=" + s_mark +
                ", d_mark=" + d_mark +
                ", mark=" + mark +
                '}';
    }
}
