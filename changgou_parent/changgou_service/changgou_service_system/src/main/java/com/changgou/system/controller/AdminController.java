package com.changgou.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Admin;
import com.changgou.system.service.AdminService;
import com.changgou.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //注入
    @Autowired
    private AdminService adminService;

    //添加
    @RequestMapping("/insert")
    public Result add(@RequestBody Admin admin){

        try {
            adminService.add(admin);
            return new Result(true, StatusCode.OK,"添加成功");

        }catch (Exception e){
            return new Result(false, StatusCode.ERROR,"添加失败" + e.getMessage());
        }

    }

    //校验
    @RequestMapping("/login")
    public Result login(@RequestBody Admin admin){
        try {
            Boolean login = adminService.login(admin);

            if(login){
                Map<String,String> info = new HashMap<>();
                info.put("username",admin.getLogin_name());

                //登录成功   签发  token
                String token = JwtUtil.createJWT(UUID.randomUUID().toString(), admin.getLogin_name(), null);
                info.put("token",token);
                return new Result(true,StatusCode.OK,"登陆成功",info);
            }else{
                return new Result(false,StatusCode.LOGINERROR,"用户名和密码不一致");
            }
        }catch (Exception e){
            return  new Result(false, StatusCode.ERROR,"登录出错");
        }
    }
    //测试jwt
    @RequestMapping("/testJwt.do")
    public Result testJwt(){
        return new Result(true,StatusCode.OK,"OK");
    }
}
