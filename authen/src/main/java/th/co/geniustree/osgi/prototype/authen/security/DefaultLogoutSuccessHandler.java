/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import th.co.geniustree.osgi.prototype.authen.api.SessionStore;

/**
 *
 * @author anonymous
 */
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String DEFAULT_REDIRECT_URL = "/";
    private final SessionStore store;

    public DefaultLogoutSuccessHandler(SessionStore store) {
        this.store = store;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        
        String redirectUrl = request.getParameter("redirect_url");
        String sessionId = request.getParameter("sessionId");

        if (store != null && sessionId != null) {
            HttpSession session = store.findSession(sessionId);
            if (session != null) {
                store.removeSession(sessionId);
                invalidSessionAndRedirect(
                        session,
                        response,
                        redirectUrl
                );
            }
        } else {
            invalidSessionAndRedirect(
                    request.getSession(),
                    response,
                    DEFAULT_REDIRECT_URL
            );
        }
    }

    private void invalidSessionAndRedirect(
            HttpSession session,
            HttpServletResponse response,
            String redirectUrl
    ) throws IOException {
        try {
            session.invalidate();
        } catch (Exception ex) {
            //swallow exception
            ex.printStackTrace();
        } finally {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(redirectUrl);
        }
    }
}
