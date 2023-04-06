//package com.fqyc.demo.filter;
//
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author lck
// * @Date 2023/3/24 10:04
// * @Version 1.0
// * @Desc
// */
//@Component
//public class CORSFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,TOKEN,token");
//        chain.doFilter(req, res);
//    }
//
//    public void init(FilterConfig filterConfig) {
//    }
//
//    public void destroy() {
//    }
//}
