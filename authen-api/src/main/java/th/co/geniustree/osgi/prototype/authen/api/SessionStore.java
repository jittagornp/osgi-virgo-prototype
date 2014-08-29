/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anonymous
 */
public interface SessionStore {

    HttpSession findSession(String sessionId);

    void storeSession(String sessionId, HttpSession session);

    HttpSession removeSession(String sessionId);
    
    Map<String, HttpSession> findAll();
    
    void clearSession();
}
