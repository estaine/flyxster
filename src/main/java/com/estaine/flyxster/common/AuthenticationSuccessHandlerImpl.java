package com.estaine.flyxster.common;


import com.estaine.flyxster.model.security.User;
import com.estaine.flyxster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by AndreyRykhalsky on 13.02.15.
 */
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    @Resource
    private LocaleResolver localeResolver;

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setLocale(authentication, request, response);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    protected void setLocale(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userService.getUserByLogin(auth.getName());

            localeResolver.setLocale(request, response, new Locale(currentUser.getLanguage()));
        }
}

