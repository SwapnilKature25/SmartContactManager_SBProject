package com.scm.main.helpers;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
// to remove signup message
@Component
public class SessionHelper {
        public void removeMessage(){
        try{
            System.out.println("Removing message from Session");
            HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        }
        catch(Exception e){
            System.err.println("Error in SessionHelper : "+e);
            e.printStackTrace();
        }
    }

}
