package org.shoper.commons.responseentity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 多条数据Response
 *
 * @author ShawnShoper
 * @date 2017/1/23 0023 14:33
 */
public class DatasResponse<T> extends BaseResponse {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public List<T> getData(Class<T> clazz) throws IOException {
        List<T> collect = this.data.stream().map(region -> {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.readValue(om.writeValueAsString(region), clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return collect;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
