package com.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTestDemo {

    public static void main(String[] args) {

        //当前时间
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        JwtBuilder builder = Jwts.builder();

        //设置编号
        builder.setId("888")  //设置唯一编号
        .setSubject("测试")  //主题
        .setIssuedAt(new Date()) //设置签发日期
        .claim("name","zhangsan")
        .claim("phone","1845772722")
        .signWith(SignatureAlgorithm.HS256,"123456");

        //构建   返回一个字符串
        System.out.println(builder.compact());

        String compact = builder.compact();

        //解析
        Claims body = Jwts.parser().setSigningKey("123456").parseClaimsJws(compact).getBody();

        System.out.println(body);


    }
}
