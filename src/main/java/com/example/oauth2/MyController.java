package com.example.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
public class MyController {

    @Value("${test}")
    String secret;

    @GetMapping("/")
    String home() {
        return "This is the home! - ";
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello World! - "+secret;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/userall")
    public OAuth2User userall(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }

    @GetMapping("/test")
    public Collection<GrantedAuthority> userAuthority(@AuthenticationPrincipal OAuth2User principal) {
        return (Collection<GrantedAuthority>) principal.getAuthorities();

    }
}
