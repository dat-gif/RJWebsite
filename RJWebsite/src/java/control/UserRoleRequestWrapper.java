/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author Admin
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String email;
    private String role1;
    private HttpServletRequest request;

    public UserRoleRequestWrapper(String email, String role, HttpServletRequest request) {
        super(request);
        this.email = email;
        this.role1 = role;
        this.request = request;
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.email == null) {
            return request.getUserPrincipal();
        }
        return new Principal() {
            @Override
            public String getName() {
                return email;
            }
        };
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role == null) {
            return this.request.isUserInRole(role);
        }
        return role1.equalsIgnoreCase(role);
    }

}
