/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.api;

import org.springframework.security.core.Authentication;

/**
 *
 * @author anonymous
 */
public interface AuthenService {

    public void storeAuthentication(String sessionId, Authentication authen);
    
    public void removeAuthentication(String sessionId);

    public Authentication findAuthentication(String sessionId);
}
