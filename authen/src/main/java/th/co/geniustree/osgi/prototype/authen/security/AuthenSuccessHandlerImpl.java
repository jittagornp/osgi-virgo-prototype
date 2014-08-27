/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author anonymous
 */
public class AuthenSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final RedirectStrategy strategy = new DefaultRedirectStrategy();
    private final AuthenService authenService;

    public AuthenSuccessHandlerImpl(AuthenService authenService) {
        this.authenService = authenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null) {
            return;
        }

        String sessionId = session.getId();
        String redirectUrl = request.getParameter("redirect_url");
        if (redirectUrl.contains("?")) {
            redirectUrl = redirectUrl + "&sessionId=" + sessionId;
        } else {
            redirectUrl = redirectUrl + "?sessionId=" + sessionId;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        authenService.storeAuthentication(sessionId, new Authentication() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.EMPTY_LIST;
            }

            @Override
            public Object getCredentials() {
                return "1234";
            }

            @Override
            public Object getDetails() {
                return "abcd";
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean bln) throws IllegalArgumentException {
                
            }

            @Override
            public String getName() {
                return "jittagornp";
            }
        });
        
        strategy.sendRedirect(request, response, redirectUrl);
    }

}
