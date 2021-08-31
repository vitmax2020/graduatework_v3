package ru.itstep.graduatework_v3.service.impl;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            Principal user = httpServletRequest.getUserPrincipal();
            modelAndView.addObject("__user", user);
        }
    }

  /*  @Override
    public void postHandle(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler, ModelAndView aModelAndView) throws Exception {

    }*/

    @Override
    public void afterCompletion(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler, Exception aEx) throws Exception { }

}