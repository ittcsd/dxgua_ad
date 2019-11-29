package com.dxgua.ad.advice;

import com.dxgua.ad.annotation.IgnoreResponseAdvice;
import com.dxgua.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一响应
 * @author caoshudong
 * @date 2019/11/19
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 标注了IgnoreResponseAdviceZ注解的类或者方法，统一响应不会生效
     * @param methodParameter
     * @param aClass
     * @return false: 不需要处理； true： 需要处理
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 当前类是否被该注解标记,则不会被拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 方法级别需要特别处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 定义返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        // 如果o是null，response不需要设置data
        if (null == o) {
            return response;
            // 如果o已经是 CommonResponse 类型，强转即可
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
            // 否则，把响应对象作为 CommonResponse 的 data 部分
        } else {
            response.setData(o);
        }

        return response;
    }
}
