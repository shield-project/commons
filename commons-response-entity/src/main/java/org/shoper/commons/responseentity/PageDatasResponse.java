package org.shoper.commons.responseentity;

/**
 * 带分页数据的Response
 * @author ShawnShoper
 * @date 2017/1/23 0023 14:32
 */
public class PageDatasResponse<T> extends DatasResponse<T>{
    public PageResponse page;

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }
}
