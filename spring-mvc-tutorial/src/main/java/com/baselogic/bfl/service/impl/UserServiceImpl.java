package com.baselogic.tutorials.service.impl;


import com.baselogic.bfl.domain.User;
import com.baselogic.bfl.service.UserService;

/**
 * https://www.packtpub.com/article/vaadin-project-with-spring-maven-handling-login-spring
 *
 * https://vaadin.com/forum#!/thread/2207105
 *
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        User user = new User();
        user.setName("Adela");
        return user;
    }
}
