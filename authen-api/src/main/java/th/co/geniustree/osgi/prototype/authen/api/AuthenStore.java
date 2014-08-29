/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;

/**
 *
 * @author anonymous
 */
public interface AuthenStore {

    public static final String AUTHENTICATION_SESSION = "GT_AUTHENTICATION_SESSION";

    public Authentication findAuthentication(String sessionId);

    public void storeAuthentication(HttpSession session, Authentication authentication);
}
