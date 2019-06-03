package com.unicom.blog.servlet.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.blog.VO.UserBlogVO;
import com.unicom.blog.beans.Result;
import com.unicom.blog.service.BlogService;
import com.unicom.blog.utils.RespCode;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.List;

/**
 * @author Xueting Ou
 */
public class HighBolgListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BlogService blogService = new BlogService();
        final ServletContext application = sce.getServletContext();
        Result<List<UserBlogVO>> result = new Result<>();
        result = blogService.qryHighlightBlogList();
        application.setAttribute("respCode", result.getRespCode());
        application.setAttribute("respDesc", result.getRespDesc());
        application.setAttribute("highBlogs", result.getRespData());

        System.out.println("size+"+result.getRespData().size());
        if(result.getRespData()!=null){
            application.setAttribute("length",result.getRespData().size());
            System.out.println("size+"+result.getRespData().size());
        }

        //System.out.println(result.getRespData());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {



    }
}
