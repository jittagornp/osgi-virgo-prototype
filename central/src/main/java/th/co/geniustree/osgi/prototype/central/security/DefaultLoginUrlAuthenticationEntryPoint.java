/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 *
 * @author anonymous
 */
public class DefaultLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    @Override
    public String getLoginFormUrl() {
        return "http://localhost:8080/authen/?redirect_url=http://localhost:8080/central/authen/";
    }

}
