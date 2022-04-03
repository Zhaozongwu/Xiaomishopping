package com.zzw.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.zzw.pojo.ProductInfo;
import com.zzw.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> getallproduct();

    PageInfo splitpage(int pageNum, int pageSize);

    int  addProduct(ProductInfo info);

    ProductInfo getByID(int id);

    int updateproduct(ProductInfo info);

    int  deleteproduct(int pid);

    int  deletbatchproduct(String[] pid);

    List<ProductInfo> selectcondition(ProductInfoVo vo);

    //多条件查询分页
    public PageInfo splitpagevo(ProductInfoVo vo,int pagesize);




}
