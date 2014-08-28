/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;
import th.co.geniustree.osgi.prototype.authen.util.SpringUtils;

/**
 *
 * @author anonymous
 */
@WebListener
public class AuthenHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionStore store = SpringUtils.getBean(se.getSession().getServletContext(), SessionStore.class);
        if (store != null) {
            store.storeSession(se.getSession().getId(), se.getSession());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionStore store = SpringUtils.getBean(se.getSession().getServletContext(), SessionStore.class);
        if (store != null) {
            store.removeSession(se.getSession().getId());
        }
    }

}
