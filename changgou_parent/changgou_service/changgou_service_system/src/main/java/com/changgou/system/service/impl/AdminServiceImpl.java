package com.changgou.system.service.impl;

import com.changgou.goods.pojo.Admin;
import com.changgou.system.dao.AdminMapper;
import com.changgou.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    //注入
    @Autowired
    private AdminMapper adminMapper;
    /**
     * 增加
     * @param admin
     */
    @Override
    public void add(Admin admin){
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);
        adminMapper.insert(admin);
    }

    //检验
    @Override
    public Boolean login(Admin admin) {

        //根据登录名查询管理员
        Admin login_admin = new Admin();
        login_admin.setLogin_name(admin.getLogin_name());
        login_admin.setStatus("1");
        Admin admin1 = adminMapper.selectOne(login_admin);
        if (admin == null){

            return false;
        }else{
            //验证密码
            return BCrypt.checkpw(admin.getPassword(),admin1.getPassword());
        }

    }


}
