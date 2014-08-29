/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

/**
 *
 * @author anonymous
 */
public interface AuthenService {
    
    String getSignInUrl();
    
    String getSignOutUrl();

    Authentication findAuthentication(String sessionId);

    void signIn(String callbackUrl);
    
    void signIn(HttpServletResponse response, String callbackUrl);

    void signOut(String callbackUrl);
    
    void signOut(HttpServletRequest request, HttpServletResponse response, String callbackUrl);
}
