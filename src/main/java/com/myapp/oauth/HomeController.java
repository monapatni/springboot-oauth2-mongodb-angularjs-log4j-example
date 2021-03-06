package com.myapp.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
   private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    @ResponseBody
    public final String home() {
        LOGGER.info("inside HomeController -> home() ");
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LOGGER.info(username);
        return "Welcome, " + username;
    }
}
