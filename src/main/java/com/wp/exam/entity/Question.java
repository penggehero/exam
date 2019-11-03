package com.wp.exam.entity;

public class Question implements Comparable<Question> {
    private Integer id; // 序号
    private Integer p_id; // 试卷编号
    private Integer number; // 题目编号
    private String name; // 题目名称
    private Integer flag; // 是否多选(1为多选,0为单选)
    private Integer mark; // 分值
    private String q_A; // A选项
    private String q_B; // B选项
    private String q_C; // C选项
    private String q_D; // D选项
    private String answer; // 正确答案

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getQ_A() {
        return q_A;
    }

    public void setQ_A(String q_A) {
        this.q_A = q_A;
    }

    public String getQ_B() {
        return q_B;
    }

    public void setQ_B(String q_B) {
        this.q_B = q_B;
    }

    public String getQ_C() {
        return q_C;
    }

    public void setQ_C(String q_C) {
        this.q_C = q_C;
    }

    public String getQ_D() {
        return q_D;
    }

    public void setQ_D(String q_D) {
        this.q_D = q_D;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", p_id=" + p_id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                ", mark=" + mark +
                ", q_A='" + q_A + '\'' +
                ", q_B='" + q_B + '\'' +
                ", q_C='" + q_C + '\'' +
                ", q_D='" + q_D + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public int compareTo(Question o) {
        return this.getNumber()-o.getNumber();
    }
}
