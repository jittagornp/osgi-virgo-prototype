/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;

/**
 *
 * @author anonymous
 */
@Component
@Scope("session")
public class AuthenController implements Serializable{

    @Autowired
    private AuthenService authenService;
    private Authentication authentication;

    public void reset() {
        String sessionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sessionId");
        if (sessionId != null) {
            authentication = authenService.findAuthenticationBySessionId(sessionId);
            System.out.println("authentication " + authentication.getName());
        }
    }

    public Authentication getAuthentication() {
        return authentication;
    }

}
