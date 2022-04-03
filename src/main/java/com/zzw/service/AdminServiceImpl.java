package com.zzw.service;

import com.zzw.dao.AdminMapper;
import com.zzw.pojo.Admin;
import com.zzw.pojo.AdminExample;
import com.zzw.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    //访问数据层的对象
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {

        //根据传入的用户到DB中查询相应的用户
        AdminExample example= new AdminExample();
        //做了查询条件的拼接
        example.createCriteria().andANameEqualTo(name);
       //查询出的用户放入列表中
        List<Admin> list= adminMapper.selectByExample(example);
        //密码进行比对
        if(list.size()>0)
        {
            Admin admin=list.get(0);

            String pass= MD5Util.getMD5(pwd);

            if(pass.equals(admin.getaPass()))
            {
                return admin;
            }
        }
        return null;
    }

    @Override
    public int  regist(Admin admin) {

       return adminMapper.insert(admin);

    }
}
