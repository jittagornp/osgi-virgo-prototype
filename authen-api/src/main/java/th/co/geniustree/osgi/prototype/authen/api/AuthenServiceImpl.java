/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;

/**
 *
 * @author anonymous
 */
public class AuthenServiceImpl implements AuthenService {

    private Map<String, Authentication> authenStore;

    private Map<String, Authentication> getStore() {
        if (authenStore == null) {
            authenStore = new HashMap<String, Authentication>();
        }

        return authenStore;
    }

    @Override
    public void storeAuthentication(String sessionId, Authentication authen) {
        getStore().put(sessionId, authen);
    }

    @Override
    public Authentication findAuthenticationBySessionId(String sessionId) {
        return getStore().get(sessionId);
    }

    @Override
    public void removeAuthentication(String sessionId) {
        getStore().remove(sessionId);
    }

}
