package org.shoper.commons.responseentity;

/**
 * 独立数据Response
 * @author ShawnShoper
 * @date 2017/1/23 0023 14:33
 */
public class DataResponse<T> extends BaseResponse {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
