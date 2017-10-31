# 中科大旗开发部通用工具包-前后端交互接口实体

#### Version:2.0
无任何修改,只为与新的规范做版本统一

#### Version:1.0.2
##### 新功能
2017-2-20
* 增加自动计算页码数
* 添加通过IO序列化后反序列化的支持
* 添加对扩展属性的支持
#### Version:1.0.1
##### 新功能
2017-1-23
* 增加单体数据接口
* 增加多体数据接口
* 增加分页数据接口
* 增加构建器方便构建response
---
使用方式

```+java
1.在Root pom.xml里添加
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.daqsoft.commons</groupId>
                <artifactId>daqsoft-commons-dependencies</artifactId>
                <version>1.0.3</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    然后在你子项目中引入需要的工具,不再需要版本号
    <dependencies>
        <dependency>
            <groupId>com.daqsoft.commons</groupId>
            <artifactId>commons-response-entity</artifactId>
        </dependency>
    </dependencies>
```
2.代码以及数据展示
```+java
 @Test
    public void dataReponseTest() throws JsonProcessingException {
        DataResponse<Foo> dataResponse = new DataResponse <>();
        Foo foo = new Foo();
        foo.setName("shoper");
        foo.setAddr("Chengdu");
        Map<String,String> other = new HashMap<>();
        other.put("app","12");
        dataResponse.setExtend(other);
        dataResponse.setData(foo);
        System.out.println(toJson(dataResponse));
    }
    
```
```+xml
{
    "responseTime": 1485157005317,
    "message": "success",
    "code": 0,
    "_extend":{"app":"12"}
    "data": {
        "name": "shoper",
        "addr": "Chengdu"
    }
}
```
```+java
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
        dataResponse.setDatas(foos);
        dataResponse.setResponseTime(System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(dataResponse));
    }
```
```+xml
{
    "responseTime": 1485157036580,
    "message": "success",
    "code": 0,
    "datas": [
        {
            "name": "shoper",
            "addr": "Chengdu"
        },
        {
            "name": "shawn",
            "addr": "Chengdu"
        }
    ]
}
```
```+java
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
        dataResponse.setDatas(foos);
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrPage(1);
        pageResponse.setPageSize(1);
        pageResponse.setTotal(2);
        pageResponse.setTotalPage(1);
        dataResponse.setPage(pageResponse);
        dataResponse.setResponseTime(System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(dataResponse));
    }
```
```+xml
{
    "responseTime": 1485156854346,
    "message": "success",
    "code": 0,
    "datas": [
        {
            "name": "shoper",
            "addr": "Chengdu"
        },
        {
            "name": "shawn",
            "addr": "Chengdu"
        }
    ],
    "page": {
        "total": 2,
        "currPage": 1,
        "pageSize": 1,
        "totalPage": 1
    }
}
```
或者使用Builder来构建一个response(建议)
```+java
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
```
