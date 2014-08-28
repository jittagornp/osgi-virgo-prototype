/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.impl;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;

/**
 *
 * @author anonymous
 */
public class AuthenServiceImpl implements AuthenService {

    private static final String AUTHENTICATION_SESSION = "session.authen.attr";

    @Autowired
    private SessionStore store;

    @Override
    public Authentication findAuthentication(String sessionId) {
        if (store != null) {
            HttpSession session = store.findSession(sessionId);
            if (session != null) {
                return (Authentication) session.getAttribute(AUTHENTICATION_SESSION);
            }
        }

        return null;
    }
}
