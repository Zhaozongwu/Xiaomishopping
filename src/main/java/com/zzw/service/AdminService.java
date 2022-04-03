package com.zzw.service;

import com.zzw.pojo.Admin;

public interface AdminService {
    Admin login(String name,String pwd);

    int   regist(Admin admin);



}
