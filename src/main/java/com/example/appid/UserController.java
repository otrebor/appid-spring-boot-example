package com.example.appid;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Authentication auth, Principal principal) {
        final boolean isAuthenticated = auth instanceof OAuth2AuthenticationToken;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Principal holds the logged in user information.
        // Spring automatically populates this principal object after login.
        return principal;
    }

    @RequestMapping("/userInfo")
    public String userInfo(Principal principal) {
        return String.valueOf(principal);
    }
}
