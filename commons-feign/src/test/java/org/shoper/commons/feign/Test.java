package org.shoper.commons.feign;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ShawnShoper on 2017/5/27.
 */
public interface Test {
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String getCode(@RequestParam("pic") String pic);

    @RequestMapping(value = "/LoginValidation", method = RequestMethod.POST)
    String login(@RequestParam("account") String account, @RequestParam("password") String password);
    @RequestMapping(value = "/v5/weather",method = RequestMethod.GET)
    String getWeather(@RequestParam("city")String city,@RequestParam("key")String key);
}
