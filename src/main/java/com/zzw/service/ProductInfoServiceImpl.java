package com.zzw.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.zzw.dao.ProductInfoMapper;
import com.zzw.pojo.ProductInfo;
import com.zzw.pojo.ProductInfoExample;
import com.zzw.pojo.vo.ProductInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    //显示所有的商品
    public List <ProductInfo> getallproduct() {

        return productInfoMapper.selectByExample(new ProductInfoExample());

    }
    @Override
    public PageInfo splitpage(int pageNum, int pageSize) {
        //定义分页插件的使用和设置
        PageHelper.startPage(pageNum,pageSize);
        //创建对象，对对象进行封装
        ProductInfoExample  example=new ProductInfoExample();

        example.setOrderByClause("p_id  desc");
        //查询
        List<ProductInfo> list=productInfoMapper.selectByExample(example);
        //将结果封装到pageinfo中
        PageInfo<ProductInfo> pageInfo=new PageInfo<>(list);

        return pageInfo;

    }
    @Override
    public int addProduct(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByID(int id) {

        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateproduct(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int deleteproduct(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deletbatchproduct(String[] pid) {
        return productInfoMapper.deletebatch(pid);
    }

    @Override
    public List<ProductInfo> selectcondition(ProductInfoVo vo) {
        return productInfoMapper.selectcondition(vo);
    }

    @Override
    public PageInfo<ProductInfo> splitpagevo(ProductInfoVo vo, int pagesize) {

        PageHelper.startPage(vo.getPage(),pagesize);
        List<ProductInfo> list=productInfoMapper.selectcondition(vo);

        return new PageInfo<ProductInfo>(list);
    }
}
