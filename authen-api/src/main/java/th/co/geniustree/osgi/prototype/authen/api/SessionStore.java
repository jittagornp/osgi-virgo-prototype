/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anonymous
 */
public class SessionStore {

    private static Map<String, HttpSession> store;

    private static Map<String, HttpSession> getStore() {
        if (store == null) {
            store = new HashMap<String, HttpSession>();
        }

        return store;
    }

    public static HttpSession findSession(String sessionId) {
        return getStore().get(sessionId);
    }

    public static void storeSession(String sessionId, HttpSession session) {
        getStore().put(sessionId, session);
    }

    public static HttpSession removeSession(String sessionId) {
        return getStore().remove(sessionId);
    }
}
