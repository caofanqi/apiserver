package cn.caofanqi.apiserver.web.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author BobbyCao
 * @date 2022/8/21 00:42
 */
//@Component
public class CrossOriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        /*
         * 带cookie的时候，origin必须是全匹配，不能使用通配符*， 而且对外系统直接使用*也不安全；
         * 在实际使用中，可以获取请求头中的Origin，判断是否允许跨域访问，做不不同的设置；
         * 我们这里，直接将Origin中的值取出，设置到Access-Control-Allow-Origin中
         */
        String origin = httpServletRequest.getHeader(HttpHeaders.ORIGIN);
        if (StringUtils.hasText(origin)) {
            httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        }

        // 允许跨域请求携带Cookie信息
        httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        // 设置允许跨域请求的方法
        httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");

        // 从Access-Control-Request-Headers中获取获取请求会额外发送的头字段
        String headers = httpServletRequest.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
        if (StringUtils.hasText(headers)) {
            httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, headers);
        }

        // 设置预检请求缓存时间
        httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

        // 获取请求的方法
        String requestMethod = httpServletRequest.getMethod();
        // 如果是预检请求，这里直接进行返回
        if (HttpMethod.OPTIONS.toString().equals(requestMethod)) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return;
        }

        chain.doFilter(request, response);
    }

}
