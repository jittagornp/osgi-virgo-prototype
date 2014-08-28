/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.controller;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;

/**
 *
 * @author anonymous
 */
@Component
@Scope("session")
public class AuthenController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuthenController.class.getName());

    @Autowired
    private AuthenService authenService;
    private Authentication authentication;
    private String sessionId;

    public void reset() {
        sessionId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("sessionId");
        
        if (sessionId != null) {
            authentication = authenService.findAuthentication(sessionId);
            if (authentication != null) {
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
            
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("session.id", sessionId);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

}
