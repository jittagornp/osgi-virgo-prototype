/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author anonymous
 */
public class SessionContext {
    
    public static UserDetails getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authen = securityContext.getAuthentication();
        UserDetails user = null;
        if (authen != null) {
            Object principal = authen.getPrincipal();
            if (principal instanceof UserDetails) {
                user = ((UserDetails) principal);
            } else if (principal instanceof String) {
                user = new UserDetailsImpl("anonymous", null);
            }
        }

        return user;
    }
}
