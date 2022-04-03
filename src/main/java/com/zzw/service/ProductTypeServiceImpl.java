package com.zzw.service;

import com.zzw.dao.ProductTypeMapper;
import com.zzw.pojo.ProductType;
import com.zzw.pojo.ProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService{
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getalltype() {

        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
