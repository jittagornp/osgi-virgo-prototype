/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;

/**
 *
 * @author anonymous
 */
public class AuthenServiceImpl implements AuthenService {

    private static final String AUTHENTICATION_SESSION = "session.authen.attr";

    @Override
    public void storeAuthentication(String sessionId, Authentication authen) {
        HttpSession session = SessionStore.findSession(sessionId);
        if (session != null) {
            session.setAttribute(AUTHENTICATION_SESSION, authen);
        }
    }

    @Override
    public Authentication findAuthentication(String sessionId) {
        HttpSession session = SessionStore.findSession(sessionId);
        if (session != null) {
            return (Authentication) session.getAttribute(AUTHENTICATION_SESSION);
        }

        return null;
    }

    @Override
    public void removeAuthentication(String sessionId) {
        HttpSession session = SessionStore.findSession(sessionId);
        if (session != null) {
            session.removeAttribute(AUTHENTICATION_SESSION);
        }
    }

}
