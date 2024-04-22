package com.sh.sys.interceptor;

import com.sh.sys.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(Constants.LOGIN_USER_KEY);
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/loginView");
            return false;
        }
        return true;
    }
}
