/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author anonymous
 */
public class DefaultLogoutFilter extends LogoutFilter {

    public DefaultLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler... handlers) {
        super(logoutSuccessHandler, handlers);
    }

    public DefaultLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers) {
        super(logoutSuccessUrl, handlers);
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //super.doFilter(req, res, chain);
        //response.sendRedirect("http://localhost:8080/authen/signout?sesionId=" + request.getSession().getAttribute("session.id") + "&redirect_url=http://localhost:8080/central/");
    }
}
