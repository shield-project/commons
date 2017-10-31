package org.shoper.commons.responseentity;

/**
 * 包含时间锉的Response
 * @author ShawnShoper
 * @date 2017/1/23 0023 14:12
 */
public class TimeStampResponse {
    private long responseTime = System.currentTimeMillis();

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
