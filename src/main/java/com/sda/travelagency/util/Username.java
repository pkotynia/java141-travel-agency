package com.sda.travelagency.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Username {
    /**
     * This util method gets an active user from SecurityContextHolder and returns its name as a String.
     * It is secured to not return name to anonymous user
     * @return username
     **/
    public static String getActive() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
