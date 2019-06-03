package com.cmp.study.springdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * Created by YANLL on 2016/03/30.
 */

@RestController
@RequestMapping
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("welcome, boy!");
        String u = "admin";
        String p = "admin";
        if ("admin".equals(u) && "admin".equals(p)) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(u, p));
            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("Unknown username or password");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            return "login success!";
        }
        return "/login?error";
    }

}