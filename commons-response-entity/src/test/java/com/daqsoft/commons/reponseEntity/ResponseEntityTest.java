package com.daqsoft.commons.reponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.shoper.commons.responseentity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public class ResponseEntityTest {
    public String toJson(Object object) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.writeValueAsString(object);
    }

    @Test
    public void builder() throws JsonProcessingException {
        System.out.println(toJson(ResponseBuilder.custom().failed("error").build()));
        System.out.println(toJson(ResponseBuilder.custom().failed("error",2).build()));
        System.out.println(toJson(ResponseBuilder.custom().success().build()));
        System.out.println(toJson(ResponseBuilder.custom().success("success").build()));
        System.out.println(toJson(ResponseBuilder.custom().success("success",5001).build()));
        //演示错误情况,如果只设置了页码，并没有数据，那么作为BaseResponse返回
        System.out.println(toJson(ResponseBuilder.custom().success().currPage(1).pageSize(10).totalCount(50).totalPage(5).build()));
        List<Foo> list = new ArrayList <>(2);
        list.add(new Foo("shawn","addr"));
        list.add(new Foo("shoper","rdda"));
        System.out.println(toJson(ResponseBuilder.custom().success().currPage(1).pageSize(10).totalCount(50).totalPage(5).data(list).build()));
        System.out.println(toJson(ResponseBuilder.custom().success().data(new Foo("ShawnShoper","addr")).build()));
    }
    @Test
    public void dataReponseTest() throws JsonProcessingException {
        DataResponse<Foo> dataResponse = new DataResponse <>();
        Foo foo = new Foo();
        foo.setName("shoper");
        foo.setAddr("Chengdu");
        Map<String,Object> other = new HashMap<>();
        other.put("app","12");
        dataResponse.setExtend(other);
        dataResponse.setData(foo);
        System.out.println(toJson(dataResponse));
    }
    @Test
    public void datasReponseTest() throws JsonProcessingException {
        DatasResponse<Foo> dataResponse = new DatasResponse <>();
        List<Foo> foos = new ArrayList <>();
        {
            Foo foo = new Foo();
            foo.setName("shoper");
            foo.setAddr("Chengdu");
            foos.add(foo);
        }
        {
            Foo foo = new Foo();
            foo.setName("shawn");
            foo.setAddr("Chengdu");
            foos.add(foo);
        }
        dataResponse.setData(foos);
        System.out.println(toJson(dataResponse));
    }
    @Test
    public void NondataReponseTest() throws JsonProcessingException {
        DatasResponse<Foo> dataResponse = new DatasResponse <>();
        System.out.println(toJson(dataResponse));
    }
    @Test
    public void pageDatasReponseTest() throws JsonProcessingException {
        PageDatasResponse<Foo> dataResponse = new PageDatasResponse <>();
        List<Foo> foos = new ArrayList <>();
        {
            Foo foo = new Foo();
            foo.setName("shoper");
            foo.setAddr("Chengdu");
            foos.add(foo);
        }
        {
            Foo foo = new Foo();
            foo.setName("shawn");
            foo.setAddr("Chengdu");
            foos.add(foo);
        }
        dataResponse.setData(foos);
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrPage(1);
        pageResponse.setPageSize(1);
        pageResponse.setTotal(2);
        pageResponse.setTotalPage(1);
        dataResponse.setPage(pageResponse);
        System.out.println(toJson(dataResponse));
    }

    @Test
    public void extendReponseTest() throws JsonProcessingException {
        Map<String ,Object> obj = new HashMap<>();
        long jh = 1232131232321321L;
        obj.put("id",jh);
        BaseResponse build = ResponseBuilder.custom().extend(obj).build();
        System.out.println(toJson(build));
    }

}
