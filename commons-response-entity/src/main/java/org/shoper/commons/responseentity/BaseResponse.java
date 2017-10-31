package org.shoper.commons.responseentity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * 标准默认的BaseResponse
 *
 * @author ShawnShoper
 * @date 2017/1/23 0023 14:12
 */
public class BaseResponse extends TimeStampResponse implements Serializable {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("_extend")
    Map<String, Object> extend;
    private String message = "success";
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
