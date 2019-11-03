package com.wp.exam.entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
    //页面信息
    private Integer currentPage;  //当前页面(前端选择的页码数,默认为1)
    private Integer totalRecord;  //总记录数(从数据库中查询出来的总记录数)
    private Integer pageSize;     //页面大小(当前页面最大记录数,一页包含几条数据)
    private Integer totalPage;    //总页面数(一共需要分几页-->总记录数/页面大小)
    private Integer startIndex;   //开始索引


    //用来存数据
    private List<T> data;//存放具体的数据
    private List<Integer> integers;//分页栏

    public PageBean(int startIndex, Integer pageSize, List<T> data) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.totalRecord = data.size();
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == data.size() % pageSize)
            this.totalPage = totalRecord / pageSize;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            this.totalPage = totalRecord / pageSize + 1;
        if (startIndex > totalPage) {
            startIndex = totalPage;
        }
        if (null == this.integers) {
            this.integers = new ArrayList<>();
            for (Integer i = 1; i <= totalPage; i++) {
                this.integers.add(i);
            }
        }
        int start = pageSize * startIndex - pageSize;
        if (start<0){
            start=0;
        }
        int end = start + pageSize;
        if (end > totalRecord) {
            end = totalRecord;
        }

        this.data = data.subList(start, end);
        this.currentPage = startIndex;
    }

    /* getter and setter */
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "当前页面:" + currentPage +
                ", 总记录数:" + totalRecord +
                ", 页面大小:" + pageSize +
                ", 总页码数:" + totalPage +
                ", 开始索引:" + startIndex +
                ", 数据:" + data +
                "分页栏"+integers+
                '}';
    }

}
