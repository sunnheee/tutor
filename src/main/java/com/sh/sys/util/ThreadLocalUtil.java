package com.sh.sys.util;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;

@Component
public class ThreadLocalUtil {

    private static ThreadLocal<HttpSession> httpSessionThreadLocal = new ThreadLocal<>();

    public HttpSession getSession() {
        return httpSessionThreadLocal.get();
    }

    public void setSession(HttpSession httpSession) {
        httpSessionThreadLocal.set(httpSession);
    }

    public void remove() {
        httpSessionThreadLocal.remove();
    }
}
