package com.changgou.system.service;

import com.changgou.goods.pojo.Admin;

public interface AdminService {

    //增加
    public void add(Admin admin);

    //校验
    public Boolean login(Admin admin);
}
