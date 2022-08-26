package cn.caofanqi.apiserver.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支持JSONP配置类
 * @author BobbyCao
 * @date 2022/8/20 23:42
 */
//@Configuration
public class JsonpConfig {


//    @Bean
//    public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice() {
//        // 默认处理参数名为callback和jsonp的请求，可以使用构造函数指定
//        return new FastJsonpResponseBodyAdvice();
//    }
//
//    @Bean
//    public FastJsonpHttpMessageConverter4 fastJsonpHttpMessageConverter4() {
//        return new FastJsonpHttpMessageConverter4();
//    }


    @Bean
    public JSONPResponseBodyAdvice jsonpResponseBodyAdvice() {
        return new JSONPResponseBodyAdvice();
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }

}
