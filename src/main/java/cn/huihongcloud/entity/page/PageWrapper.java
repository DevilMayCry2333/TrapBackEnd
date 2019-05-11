package cn.huihongcloud.entity.page;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/16
 */
public class PageWrapper {

    private int currentPage;
    private int totalPage;
    private long totalNum;
    private Object data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
