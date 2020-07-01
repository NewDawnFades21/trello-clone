package edu.zsc.todolistproject.domain;

import edu.zsc.todolistproject.constants.Constants;
import lombok.Data;

/**
 * 封装分页的请求
 */
@Data
public class PageDomain {
    Integer pageSize;   //分页的大小
    Integer pageNum;     //请求页
    String sortColumn;   //排序的字段名
    String sortOrder;   //排序方向

    /**
     * 封装默认的分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Constants.PAGE_NUM);
        pageDomain.setPageSize(Constants.PAGE_SIZE);
        pageDomain.setSortColumn(Constants.ORDER_BY_COLUMN);
        pageDomain.setSortOrder(Constants.ORDER_DIRECTION);
        return pageDomain;
    }

    public PageDomain(){

    }
    /**
     * 构造函数
     * @return
     */
    public PageDomain(int pageNum, int pageSize){
        this(pageNum,pageSize,Constants.ORDER_BY_COLUMN,Constants.ORDER_DIRECTION);
    }

    /**
     * 构造函数
     * @return
     */
    public PageDomain(int pageNum, int pageSize, String sortColumn, String direction){
        this.pageNum =pageNum;
        this.pageSize = pageSize;
        this.sortColumn=sortColumn;
        this.sortOrder = direction;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
    /**
     * 返回SQL语句中的排序字符串部分,即order by后面的内容
     * @return
     */
    public String getOrderStr(){
        return sortColumn+ " " + sortOrder;
    }
}
