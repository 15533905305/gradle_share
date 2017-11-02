package com.share.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by antLuck on 2017/8/8.
 */
@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 自定义消息转换器
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter =new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfiguration = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfiguration.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,       //消除同一对象循环引用问题
                SerializerFeature.WriteMapNullValue
                //SerializerFeature.WriteNullStringAsEmpty
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfiguration);
        //将fastJosn添加到视图消息转换器中
        converters.add(fastJsonHttpMessageConverter);
    }

}
