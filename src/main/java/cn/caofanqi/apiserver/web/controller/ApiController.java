package cn.caofanqi.apiserver.web.controller;

import cn.caofanqi.apiserver.dto.Result;
import cn.caofanqi.apiserver.dto.User;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 用于测试跨域提供的后端接口
 *
 * @author BobbyCao
 * @date 2022/8/20 23:29
 */
@Slf4j
@RestController
@RequestMapping("/api")
//@CrossOrigin(originPatterns = "*", allowCredentials = "true", exposedHeaders = "X-Custom-Header", allowedHeaders = "*",maxAge = 3600)
public class ApiController {

    //    @ResponseJSONP(callback = "jsonp")
    @RequestMapping("/hello")
    public Result<String> sayHello() {
        log.debug("sayHello...");
        return Result.success("hello!");
    }

    @GetMapping(value = "/jsonp", produces = "application/javascript")
    public String jsonp(String callback) {
        log.debug("jsonp...");
        Result<String> result = Result.success("hello jsonp!");
        String jsonStr = JSONUtil.toJsonStr(result);
        return callback + "(" + jsonStr + ")";
    }


    @GetMapping("/with/cookie")
    public Result<String> withCookie(HttpServletRequest request) {
        log.debug("withCookie...");
        Cookie[] cookies = request.getCookies();
        StringJoiner joiner = new StringJoiner(";");
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                joiner.add(cookie.getName() + "=" + cookie.getValue());
            }
        }
        return Result.success("withCookie: " + joiner);
    }

    @GetMapping("/expose/headers")
    public Result<Void> exposeHeaders(HttpServletResponse response) {
//        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Custom-Header");
        response.setHeader("X-Custom-Header", "BobbyCao");
        log.info("exposeHeaders...");
        return Result.success();
    }

    @PutMapping("/put/json")
    public Result<User> putJson(@RequestBody User user) {
        log.debug("jsonp...");
        return Result.success(user);
    }

    @GetMapping("/with/header")
    public Result<String> withHeader(@RequestHeader("X-Custom-Header1") String header1,
                                     @RequestHeader("X-Custom-Header2") String header2) {
        log.debug("withHeader...");
        return Result.success("withHeader " + header1 + " " + header2);
    }

}
