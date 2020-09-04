package com.ssoclient1.demo.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    //得到security所保存的用户
    public static String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         /*
        if (authentication.isAuthenticated()) {
            System.out.println("isAuthenticated");
        } else {
            System.out.println("not isAuthenticated");
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("instanceof AnonymousAuthenticationToken");
        } else {
            System.out.println("not instanceof AnonymousAuthenticationToken");
        }
        */
        if (authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();

            System.out.println("principal:");
            System.out.println(principal);


            if (principal instanceof String) {
                //System.out.println("-----------------------------type userDetails");
                //System.out.println("instanceof String");
                return (String)principal;
            } else if (principal instanceof UserDetails) {
                //System.out.println("-----------------------------type userDetails");
                String currentuser = ((UserDetails) principal).getUsername();
                return currentuser;
            } else {
                //System.out.println("not instanceof UserDetails");
            }

            return null;
        }
        return null;
    }
}
