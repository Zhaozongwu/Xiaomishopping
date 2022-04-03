package com.zzw.controller;
import com.zzw.pojo.Admin;
import com.zzw.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login.action")
    public String login(String name, String pwd, Model model,HttpServletRequest request)
    {
        Admin admin=adminService.login(name, pwd);
        if(admin!=null)
        {
            request.setAttribute("admin",admin);
            model.addAttribute("name",admin.getaName());
            return "main";

        }
        else {
            model.addAttribute("errmsg","用户或密码不正确");

            return  "login";
        }
    }
    @RequestMapping("/regist.action")
    public  String regist(Admin admin,HttpServletRequest request)
    {
        int num=-1;
        try {
            num= adminService.regist(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("errmsg","用户增加成功");
            return  "login";
        }else
        {
            request.setAttribute("errmsg","用户增加失败");
            return  "regist";

        }
    }



}
