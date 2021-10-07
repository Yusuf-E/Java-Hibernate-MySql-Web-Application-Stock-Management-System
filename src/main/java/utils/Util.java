package utils;

import entities.Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Util {
    public final static String base_url = "http://localhost:8080/depo_project_war_exploded/";

    public Admin isLogin(HttpServletRequest request , HttpServletResponse response){
        if (request.getCookies() != null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("user")){
                    String values = cookie.getValue();
                    try{
                        String[] arr = values.split("_");
                        request.getSession().setAttribute("aid", Integer.parseInt(arr[0]) );
                        request.getSession().setAttribute("name",arr[1] + " " + arr[2] );

                    }catch (NumberFormatException e){
                        Cookie cookie1 = new Cookie("user","");
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                    break;
                }
            }
        }
        Object sessionObj = request.getSession().getAttribute("aid");
        Admin admin = new Admin();
        if (sessionObj == null){
            try {
                response.sendRedirect(base_url);
            }catch (IOException e){
                System.err.println("Session Error " + e);
            }
        }else {
            int aid = (int) request.getSession().getAttribute("aid");
            String name = (String) request.getSession().getAttribute("name");
            String email = (String) request.getSession().getAttribute("email");
            admin.setAid(aid);
            admin.setName(name);
            admin.setEmail(email);
        }
        return admin;
    }
}
