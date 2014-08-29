/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import th.co.geniustree.osgi.prototype.authen.api.AuthenService;
import th.co.geniustree.osgi.prototype.central.util.SpringUtils;

/**
 *
 * @author anonymous
 */
@WebServlet(urlPatterns = "/authen/signout")
public class LogoutServlet extends HttpServlet {

    private static final String CALLBACK_URL = "http://localhost:8080/central/logout";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthenService authenService = SpringUtils.getBean(req.getServletContext(), AuthenService.class);
        authenService.signOut(req, resp, CALLBACK_URL);
    }

}
