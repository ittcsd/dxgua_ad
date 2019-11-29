package com.dxgua.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 自定义网关过滤器
 *
 * @Author caoshudong
 * @Version v1.0
 * @Date 2019/11/18 23:58
 */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter{

    /**/
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /*定义filter执行的顺序，数字越小表示顺序越高，最先被执行*/
    @Override
    public int filterOrder() {
        return 0;
    }

    /*表示是否要执行这个过滤器 true-执行 false-不执行，默认*/
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*执行的具体操作*/
    @Override
    public Object run() throws ZuulException {
        /*应用上下文*/
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis() );
        return null;
    }
}
