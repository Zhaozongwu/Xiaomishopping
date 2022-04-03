package com.zzw.Test;

import com.zzw.dao.ProductInfoMapper;
import com.zzw.pojo.ProductInfo;
import com.zzw.pojo.vo.ProductInfoVo;
import com.zzw.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Mytest {
    @Autowired
    ProductInfoMapper productInfoMapper;

  @Test
    public void test()
    {
        String pwd= MD5Util.getMD5("000000");

        System.out.println(pwd);

    }
@Test
    public void test1()
    {
        //测试的时候，要将所有的情况都考虑进去
        ProductInfoVo vo=new ProductInfoVo();
        vo.setTypeid(3);
        vo.setPname("4");
//        vo.setHprice(4000);
//        vo.setLprice(2000);
        List<ProductInfo>list=productInfoMapper.selectcondition(vo);
        list.forEach(ProductInfo-> System.out.println(ProductInfo));

    }



}
