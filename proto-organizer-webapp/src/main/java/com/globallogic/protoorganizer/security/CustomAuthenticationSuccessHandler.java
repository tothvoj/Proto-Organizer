package com.globallogic.protoorganizer.security;

import org.springframework.security.core.Authentication;  
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;  
  


import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

import java.io.IOException;  
import java.util.Collection;
  
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  
  
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
                                        HttpServletResponse httpServletResponse,  
                                        Authentication authentication)  
            throws IOException, ServletException {  
        //do some logic here if you want something to be done whenever  
        //the user successfully logs in.  
  
        HttpSession session = httpServletRequest.getSession();  
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        session.setAttribute("username", authUser.getUsername());  
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
        session.setAttribute("authorities", authorities);  
  
        //set our response to OK status  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
  
        //since we have created our custom success handler, its up to us to where  
        //we will redirect the user after successfully login  
        
        if(authorized) {
        	httpServletResponse.sendRedirect("getListAdmin");  
        }
        else {
        	httpServletResponse.sendRedirect("getList");  
        }
    }  
}  