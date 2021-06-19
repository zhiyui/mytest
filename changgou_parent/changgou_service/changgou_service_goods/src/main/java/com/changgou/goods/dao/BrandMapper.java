package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import feign.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<Brand>{

    //根据分类名称查询品牌列表
    @Select("select * from tb_brand where id in" +
            "(select brand_id from tb_category_brand where category_id in" +
            "(select id from  tb_category where name = #{name})) order by seq")
    public List<Map> findListByCategoryName(@Param("name") String name);

}
