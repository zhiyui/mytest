package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    //查询全部
    @RequestMapping("/findAll.do")
    public Result findAll(){
        List<Brand> brandList = brandService.findAll();
        return  new Result(true,200,"查询成功",brandList);
    }


    //根据id查询
    //@GetMapping
   @RequestMapping("/findById/{id}")   //基于 restful 访问格式的http请求
    public Result findById(@PathVariable int id){
        Brand brand = brandService.findById(id);
        return  new Result(true,200,"查询成功",brand);
    }



    @PostMapping
    public Result add(@RequestBody Brand brand){   //将接受的json串   转成pojo类 , 封装到   brand中

        brandService.add(brand);

        return  new Result(true, StatusCode.OK, "添加成功");

    }
    /***
     * 修改品牌数据
     * @param brand
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody Brand brand,@PathVariable int id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<Brand> list = brandService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }
    /***
     * 分页搜索实现
     * @param page
     * @param size
     * @return
     */
    /*@GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@PathVariable  int page, @PathVariable  int size){
        Page<Brand> pageList = brandService.findPage(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }*/

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
   @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@RequestParam Map searchMap, @PathVariable  int page, @PathVariable  int size){
        Page<Brand> pageList = brandService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }



    /**
     * 根据分类名称查询品牌列表
     * @param
     * @return
     */
    @GetMapping("/category/{name}")
    public Result  findListByCategoryName(@PathVariable String name){
        System.out.println(name);
        List<Map> brandList = brandService.findListByCategoryName(name);
        return new Result(true,StatusCode.OK,"查询成功",brandList);
    }

}
