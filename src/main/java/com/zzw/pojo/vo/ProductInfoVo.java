package com.zzw.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoVo {
    private String  pname;

    private Integer typeid;

    private Integer lprice;

    private  Integer hprice;

    private Integer page;
}
