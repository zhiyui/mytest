package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecServiceImpl implements SpecService {

    //注入mapper
    @Autowired
    private SpecMapper specMapper;

    @Override
    public List<Map> findMapByCategoryName(String name) {
        return specMapper.findMapByCategoryName(name);
    }
}
