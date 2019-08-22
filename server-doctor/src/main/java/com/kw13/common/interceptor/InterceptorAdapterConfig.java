package com.kw13.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @Desc 拦截请求 验证token是否正确
 * @Date 2019/3/8
 */
@Configuration
public class InterceptorAdapterConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    
    @Value("${Interceptor.url-exclude}")
    private String urls ;

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
    	String[]  excludes = urls.split(";");
        interceptorRegistry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {
    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
