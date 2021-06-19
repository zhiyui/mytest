package com.changgou.system.filter;

import com.changgou.system.util.JwtUtil;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "token";

    //拦截
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //特定地址的拦截
        //业务实现
        //1 获取response
        ServerHttpResponse response = exchange.getResponse();
        //2 获取request
        ServerHttpRequest request = exchange.getRequest();
        //3 获取登陆的路径
        String path = request.getURI().getPath();
        System.out.println("第二次拦截用户的url："+ path);
        //4 判断是否登录
        if ("/admin/login".equals(path)){

            System.out.println("登录访问放行==========");

            //放行
            return chain.filter(exchange);
        }
        //不是登录  请求访问以外的路径   检验token
        //获取头信息
        HttpHeaders headers = request.getHeaders();
        //请求头中获取令牌
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        //判断token是否存在
        if (StringUtils.isEmpty(token)){

            //为空   拦截    返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            System.out.println("token为空==============");
            //拦截  返回客户端
            return response.setComplete();
        }
        //7解析token
        try{
            JwtUtil.parseJWT(token);
            System.out.println("解析token完成==================");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("解析token出错==================");
            //为空   拦截   返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //拦截   返回客户端
            return response.setComplete();
        }
        return chain.filter(exchange);


    }
   //拦截顺序
    @Override
    public int getOrder() {
        return 3;
    }
}
