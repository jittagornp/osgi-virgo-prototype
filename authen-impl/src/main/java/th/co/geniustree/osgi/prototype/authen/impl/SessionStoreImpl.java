/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.impl;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;

/**
 *
 * @author anonymous
 */
public class SessionStoreImpl implements SessionStore {

    private Map<String, HttpSession> store;

    @Override
    public HttpSession findSession(String sessionId) {
        return findAll().get(sessionId);
    }

    @Override
    public void storeSession(String sessionId, HttpSession session) {
        findAll().put(sessionId, session);
    }

    @Override
    public HttpSession removeSession(String sessionId) {
        return findAll().remove(sessionId);
    }

    @Override
    public Map<String, HttpSession> findAll() {
        if (store == null) {
            store = new HashMap<String, HttpSession>();
        }

        return store;
    }

    @Override
    public void clearSession() {
        findAll().clear();
    }
}
