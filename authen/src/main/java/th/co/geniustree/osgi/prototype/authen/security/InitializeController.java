/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.authen.security;

import th.co.geniustree.osgi.prototype.authen.api.AuthenService;

/**
 *
 * @author anonymous
 */
public class InitializeController {

    private final AuthenService authenService;

    public InitializeController(AuthenService authenService) {
        this.authenService = authenService;
        
        System.out.println("call authen service.");
    }

}
