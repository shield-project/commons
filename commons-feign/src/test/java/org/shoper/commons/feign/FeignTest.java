package org.shoper.commons.feign;

import org.shoper.commons.core.ImageUtil;
import org.shoper.commons.feign.support.SpringMvcFeign;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public class FeignTest {
    @Test
    public void feignTest() throws IOException {
//        RegionService target = SpringMvcFeign.target(RegionService.class,"http://192.168.0.51:6745");
//        PageDatasResponse<Region> response= (PageDatasResponse<Region>)target.regionByExample("天津",1,3);
//        response.getDatas(Region.class).stream().map(Region::getFullName).forEach(System.out::println);
//        System.out.println(ImageUtil.toBase64(new FileInputStream("C:\\Users\\ShawnShoper\\Pictures\\22.jpg")));
        org.shoper.commons.feign.Test target = SpringMvcFeign.target(org.shoper.commons.feign.Test.class, "http://192.168.0.13:8000");
        target.getCode(ImageUtil.toBase64(new FileInputStream("C:\\Users\\ShawnShoper\\Pictures\\22.jpg")));
//        System.out.println(target.login("root_scs", "daqsoft"));
    }

    public String toJson(Object object) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void weather() throws IOException {
        org.shoper.commons.feign.Test target = SpringMvcFeign.target(org.shoper.commons.feign.Test.class, "https://api.heweather.com");
        long l = System.currentTimeMillis();
        IntStream.range(0,20).parallel().forEach(e-> System.out.println(target.getWeather("CN101010100", "259b8e182cf64ac1b46aaced4a6611d2")));
        System.out.println(System.currentTimeMillis() - l);
    }
}
