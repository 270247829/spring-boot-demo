package com.lankegp.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lankegp.common.base.R;
import com.lankegp.common.debug.RunMode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/11/23.
 */
public class SessionInterceptor implements HandlerInterceptor {

    private static String SESSION_USER_KEY = "user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(RunMode.isDebug()){
            return true;
        }
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }
        //获取session
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute(SESSION_USER_KEY);
        //判断用户ID是否存在，不存在就跳转到登录界面
        if(!request.getDispatcherType().name().equals("ERROR")&&obj == null){
//            internalDispatcherType
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null ;
                try{
                    R results = new R();
                    Map res = new HashMap<>();
                    res.put("status","999");
                    results.setData(res);
                    out = response.getWriter();
                    out.append(JSONObject.toJSONString(results));

                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    response.sendError(500);
                    return false;
                }
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
