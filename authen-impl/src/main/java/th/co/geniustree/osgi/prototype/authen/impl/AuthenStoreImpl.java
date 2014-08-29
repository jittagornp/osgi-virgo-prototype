/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.impl;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import th.co.geniustree.osgi.prototype.authen.api.AuthenStore;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;

/**
 *
 * @author anonymous
 */
public class AuthenStoreImpl implements AuthenStore {

    @Autowired
    private SessionStore store;

    @Override
    public Authentication findAuthentication(String sessionId) {
        if (store != null) {
            HttpSession session = validateSession(store.findSession(sessionId));
            if (session != null) {
                return (Authentication) session.getAttribute(AUTHENTICATION_SESSION);
            }
        }

        return null;
    }

    @Override
    public void storeAuthentication(HttpSession session, Authentication authentication) {
        if (store != null) {
            validateSessionStore();

            HttpSession ssion = store.findSession(session.getId());
            if (ssion == null) {
                ssion = session;
                store.storeSession(ssion.getId(), ssion);
            }

            ssion.setAttribute(AUTHENTICATION_SESSION, authentication);
        }
    }

    private void validateSessionStore() {
        for (HttpSession session : store.findAll().values()) {
            validateSession(session);
        }
    }

    private HttpSession validateSession(HttpSession session) {
        String sessionId = session.getId();
        try {
            session.getCreationTime();
        } catch (IllegalStateException ex) {
            // it's invalid
            store.removeSession(sessionId);
            session = null;
            ex.printStackTrace();
        }

        return session;
    }

}
