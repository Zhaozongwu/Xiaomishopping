package com.zzw.controller;
import com.github.pagehelper.PageInfo;
import com.zzw.pojo.ProductInfo;
import com.zzw.pojo.vo.ProductInfoVo;
import com.zzw.service.ProductInfoService;
import com.zzw.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
    public static final int PAGE_AIZE=5;

    String savefilename="";

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/getpage.action")
    public String getpage(HttpServletRequest request)
    {
        List<ProductInfo> list=productInfoService.getallproduct();
        request.setAttribute("list",list);
        return "product";
    }

    @RequestMapping("/splitpage.action")
    public String splitpage(HttpServletRequest request)
    {
        PageInfo info=null;
        Object vo=request.getSession().getAttribute("provo");
        if(vo!=null)
        {
            info=productInfoService.splitpagevo((ProductInfoVo) vo,PAGE_AIZE);
        }
        else {
            info = productInfoService.splitpage(1, PAGE_AIZE);
        }
        request.setAttribute("info",info);

        request.getSession().removeAttribute("provo");

        return "product";
    }

    @ResponseBody  //将java对象转化为指定的json格式
    @RequestMapping("/splitnum.action")
    public  void ajaxsplit(HttpSession session,ProductInfoVo vo)
    {
        PageInfo<ProductInfo> list=productInfoService.splitpagevo(vo,PAGE_AIZE);

        session.setAttribute("info",list);
    }

    //使用ajax分页对图像进行上传
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request) throws IOException {
        //UUid+文件后缀名，为了避免不同的用户上传的文件夹名称一样
       savefilename= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到路径名
        String path=request.getServletContext().getRealPath("/image_big");
        //转存,separator路径分隔符
        pimage.transferTo(new File(path+File.separator+savefilename));

        //返回客户端JSON对象，封装图片的路径，为了在页面进行立即回显
        JSONObject object=new JSONObject();
        object.put("imgurl",savefilename);

        return object.toString();

    }
    //增加商品功能的实现
    @RequestMapping("/addproduct.action")
    public String  save(ProductInfo info,HttpServletRequest request)
    {
        info.setpDate(new Date());
        info.setpImage(savefilename);
        int num=-1;

        try {
            num=productInfoService.addProduct(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("msg","增加成功");
        }else
        {
            request.setAttribute("msg","增加失败");
        }
        savefilename="";

        return "forward:/product/splitpage.action";
    }
    //跟新商品，数据进行回显
    @RequestMapping("/one.action")
    public  String  one(int pid,ProductInfoVo vo,HttpServletRequest request,HttpSession session)
    {
        ProductInfo info = productInfoService.getByID(pid);
        request.setAttribute("prod",info);
        //将多条件放入session中
        session.setAttribute("provo",vo);
        return "update";

    }
    //跟新商品
    @RequestMapping("update.action")
    public String updateproduct(ProductInfo info,HttpServletRequest request)
    {
        //判断当前的图像是否为空,日期是不变的。
        if(!savefilename.equals(""))
        {
            info.setpImage(savefilename);
        }
        int num =-1;
        try {
            num= productInfoService.updateproduct(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("msg","跟新成功");
        }else
        {
            request.setAttribute("msg","跟新失败");
        }
        savefilename="";
        return "forward:/product/splitpage.action";
    }
    //删除单个商品
    @RequestMapping("/delete.action")
    public String delete(int pid,ProductInfoVo vo,HttpServletRequest request)
    {
        int num=-1;

        try {
            num=productInfoService.deleteproduct(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("msg","删除成功");
            request.getSession().setAttribute("deletevo",vo);
        }else
        {
            request.setAttribute("msg","删除失败");
        }
        return "forward:/product/deleteAjaxSplit";

    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=utf-8")
    public Object deleteAjaxSplit(HttpServletRequest request)
    {
        PageInfo info=null;
        Object vo=request.getSession().getAttribute("deletevo");
        if(vo!=null)
        {
            info=productInfoService.splitpagevo((ProductInfoVo) vo,PAGE_AIZE);
        }
        else
        {
            info = productInfoService.splitpage(1, PAGE_AIZE);
        }
        //浏览器不关闭，保存的值也不会消失
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }
    //批量删除商品
    @RequestMapping("/deleteBatch.action")
    public String deletebatch(String pids,HttpServletRequest request)
    {
        String []id=pids.split(",");
        int num=-1;
        try {
             num=productInfoService.deletbatchproduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("msg","批量删除成功");
        }else
        {
            request.setAttribute("msg","批量删除失败");
        }
        return  "forward:/product/deleteAjaxSplit";

    }








}
