/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private AuthenService authenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null) {
            return;
        }

        String sessionId = session.getId();
        String redirectUrl = request.getParameter("redirect_url") + "&sessionId=" + sessionId;

        strategy.sendRedirect(request, response, redirectUrl);
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (authenService != null) {
            System.out.println("store session");
            authenService.storeAuthentication(sessionId, authentication);
        }
    }

}
