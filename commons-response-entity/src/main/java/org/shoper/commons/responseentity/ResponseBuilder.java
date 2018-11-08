package org.shoper.commons.responseentity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Response构建器
 *
 * @author ShawnShoper
 * @date 2017/1/24 0024 10:41
 */
public class ResponseBuilder {
    private String message = "success";
    private Object data;
    private int code = 200;
    private List<Object> datas;

    private int total;
    private int currPage;
    private int pageSize;
    private int totalPage;
    private Map<String, Object> extend;

    private ResponseBuilder() {

    }

    public static ResponseBuilder custom() {
        return new ResponseBuilder();
    }

    public BaseResponse build() {
        BaseResponse baseResponse;
        if (this.total > 0 && this.pageSize > 0 && this.totalPage <= 0)
            this.totalPage = (int) Math.ceil((double) this.total / (double) this.pageSize);
        if (Objects.nonNull(this.datas) && this.total > 0 && this.currPage > 0 && this.pageSize > 0 && this.totalPage > 0) {
            PageDatasResponse pageDatasResponse = new PageDatasResponse();
            pageDatasResponse.setCode(this.code);
            pageDatasResponse.setDatas(this.datas);
            pageDatasResponse.setMessage(this.message);
            pageDatasResponse.setPage(new PageResponse(this.total, this.currPage, this.pageSize, this.totalPage));
            pageDatasResponse.setExtend(extend);
            baseResponse = pageDatasResponse;
        } else if (Objects.nonNull(this.datas)) {
            DatasResponse datasResponse = new DatasResponse<>();
            datasResponse.setCode(this.code);
            datasResponse.setDatas(this.datas);
            datasResponse.setMessage(this.message);
            datasResponse.setExtend(extend);
            baseResponse = datasResponse;
        } else if (Objects.nonNull(this.data)) {
            DataResponse dataResponse = new DataResponse<>();
            dataResponse.setData(this.data);
            dataResponse.setCode(this.code);
            dataResponse.setMessage(this.message);
            dataResponse.setExtend(extend);
            baseResponse = dataResponse;
        } else {
            baseResponse = new BaseResponse();
            baseResponse.setMessage(this.message);
            baseResponse.setCode(this.code);
            baseResponse.setExtend(extend);
        }
        return baseResponse;
    }

    public ResponseBuilder extend(Map<String, Object> extend) {
        this.extend = extend;
        return this;
    }

    public ResponseBuilder data(Object data) {
        if (data instanceof List)
            this.datas = (List<Object>) data;
        else
            this.data = data;
        return this;
    }

    public ResponseBuilder totalCount(int totalCount) {
        this.total = totalCount;
        return this;
    }

    public ResponseBuilder currPage(int currPage) {
        this.currPage = currPage;
        return this;
    }

    public ResponseBuilder pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ResponseBuilder totalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public ResponseBuilder success() {
        return this;
    }

    public ResponseBuilder success(String message) {
        this.message = message;
        return this;
    }

    public ResponseBuilder success(String message, int code) {
        this.message = message;
        this.code = code;
        return this;
    }

    public ResponseBuilder failed(String message, int code) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseBuilder failed(String message) {
        this.code = 1;
        this.message = message;
        return this;
    }
}
