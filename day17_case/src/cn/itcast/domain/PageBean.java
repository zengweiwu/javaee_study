package cn.itcast.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean<T> {
    private int totalcount;//总记录数
    private  int totalpage;//总页数
    private  int currentpage;//当前页码
    private List<T> objectInPage;//每一页User对象的集合
    private  int rows;//每一页行数
    private int start;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalcount=" + totalcount +
                ", totalpage=" + totalpage +
                ", currentpage=" + currentpage +
                ", objectInPage=" + objectInPage +
                ", rows=" + rows +
                '}';
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public List<T> getObjectInPage() {
        return objectInPage;
    }

    public void setObjectInPage(List<T> objectInPage) {
        this.objectInPage = objectInPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
