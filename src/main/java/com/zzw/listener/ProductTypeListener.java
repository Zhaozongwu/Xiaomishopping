package com.zzw.listener;

import com.zzw.pojo.ProductType;
import com.zzw.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //不能保证哪一个监听先被创建，所以需要手动创建监听器
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        ProductTypeService productTypeService= (ProductTypeService) context.getBean("ProductTypeServiceImpl");

        List<ProductType> typeList=productTypeService.getalltype();

        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
