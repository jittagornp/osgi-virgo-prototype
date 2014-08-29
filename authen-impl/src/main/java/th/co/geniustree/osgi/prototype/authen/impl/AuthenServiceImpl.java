/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.impl;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import th.co.geniustree.osgi.prototype.authen.api.AuthenStore;

/**
 *
 * @author anonymous
 */
public class AuthenServiceImpl implements AuthenService {

    private static final String AUTHENTICATION_URL = "http://localhost:8080/authen/";
    private static final String SIGNOUT_URL = AUTHENTICATION_URL + "signout";

    @Autowired
    private AuthenStore store;

    @Override
    public Authentication findAuthentication(String sessionId) {
        return store.findAuthentication(sessionId);
    }

    @Override
    public void signIn(String callbackUrl) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            try {
                facesContext
                        .getExternalContext()
                        .redirect(
                                AUTHENTICATION_URL
                                + "?redirect_url="
                                + callbackUrl
                        );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void signOut(String callbackUrl) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            try {
                ExternalContext ectx = facesContext.getExternalContext();
                String sessionId = (String) ectx
                        .getSessionMap()
                        .get("session.id");

                ectx.redirect(
                        SIGNOUT_URL
                        + "?sessionId="
                        + sessionId
                        + "&redirect_url="
                        + callbackUrl
                );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void signIn(HttpServletResponse response, String callbackUrl) {
        try {
            response.sendRedirect(
                    AUTHENTICATION_URL
                    + "?redirect_url="
                    + callbackUrl
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void signOut(HttpServletRequest request, HttpServletResponse response, String callbackUrl) {
        try {
            String sessionId = (String) request
                    .getSession()
                    .getAttribute("session.id");

            response.sendRedirect(
                    SIGNOUT_URL
                    + "?sessionId="
                    + sessionId
                    + "&redirect_url="
                    + callbackUrl
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getSignInUrl() {
        return AUTHENTICATION_URL;
    }

    @Override
    public String getSignOutUrl() {
        return SIGNOUT_URL;
    }
}
