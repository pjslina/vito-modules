package com.vito.springboot.configuration;

import com.vito.springboot.util.ApplicationContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panjin
 */
@Configuration(proxyBeanMethods = false)
public class WebBeanConfig {

    /**
     * messageSource会默认被MessageSourceAutoConfiguration实例化，
     * 所以如果使用默认的，就不需要创建这个bean
     * setBasename方法指定要加载的资源文件的前缀
     * setBasenames方法指定要加载的资源文件列表的前缀
     * setDefaultEncoding方法指定要加载的资源文件的编码
     * @return
     */
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

    @Bean
    public ApplicationContextUtil applicationContextUtil() {
        return new ApplicationContextUtil();
    }
}
