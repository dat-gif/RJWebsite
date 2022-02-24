/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nguyen Dinh Dat
 */
public class SecurityConfig {

    public static final String ROLE_CANDIDATE = "candidate";
    public static final String ROLE_RECRUITER = "recruiter";
    public static final String ROLE_ADMIN = "admin";

// String: Role
// List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configuration "CANDIDATE" role.
        List<String> urlPatterns1 = new ArrayList<String>();

//        urlPatterns1.add("/seachingjob");
//        urlPatterns1.add("/seachingcompany");
        urlPatterns1.add("/candidateapplyjobpage");
        urlPatterns1.add("/companyfollowingPage");
        urlPatterns1.add("/candidateprofilecontroller");

        mapConfig.put(ROLE_CANDIDATE, urlPatterns1);

        // Configuration "RECRUITER" role.
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/RecruiterDashBoard");

        mapConfig.put(ROLE_RECRUITER, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
