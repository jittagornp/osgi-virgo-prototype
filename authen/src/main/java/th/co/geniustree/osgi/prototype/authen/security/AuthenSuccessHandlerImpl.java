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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import th.co.geniustree.osgi.prototype.authen.api.AuthenStore;

/**
 *
 * @author anonymous
 */
public class AuthenSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private static final String REDIRECT_URL_PARAM = "redirect_url";
    private static final String DEFAULT_REDIRECT_URL = "/account/";
    private static final String SESSION_PARAM = "sessionId";
    private final RedirectStrategy strategy = new DefaultRedirectStrategy();
    private final AuthenStore store;

    public AuthenSuccessHandlerImpl(AuthenStore store) {
        this.store = store;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        store.storeAuthentication(session, authentication);
        strategy.sendRedirect(request, response, getRedirectUrl(request));
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String redirectUrl = request.getParameter(REDIRECT_URL_PARAM);
        if (redirectUrl == null || "".equals(redirectUrl)) {
            redirectUrl = DEFAULT_REDIRECT_URL;
        }

        String sessionId = request.getSession().getId();
        if (redirectUrl.contains("?")) {
            redirectUrl = redirectUrl + "&" + SESSION_PARAM + "=" + sessionId;
        } else {
            redirectUrl = redirectUrl + "?" + SESSION_PARAM + "=" + sessionId;
        }

        return redirectUrl;
    }
}
