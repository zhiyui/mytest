package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    //注入mapper
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {

        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }
    //添加
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }
    //修改
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }
    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 品牌的首字母
            if(searchMap.get("firstChar")!=null && !"".equals(searchMap.get("firstChar"))){
                criteria.andEqualTo("firstChar",searchMap.get("firstChar"));
            }
        }
        return brandMapper.selectByExample(example);
    }
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    /*@Override
    public Page<Brand> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Brand>)brandMapper.selectAll();
    }*/

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Brand> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 品牌的首字母
            if(searchMap.get("firstChar")!=null && !"".equals(searchMap.get("firstChar"))){
                criteria.andEqualTo("firstChar",searchMap.get("firstChar"));
            }
        }
        return (Page<Brand>)brandMapper.selectByExample(example);
    }

    @Override
    public List<Map> findListByCategoryName(String name) {
        return brandMapper.findListByCategoryName(name);
    }

}
