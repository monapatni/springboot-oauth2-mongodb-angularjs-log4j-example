package com.myapp.controller;

import com.myapp.dao.beans.UserDetails;
import com.myapp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mona.p on 05/04/18.
 */
@RestController
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/users")
    public List<UserDetails> getData() {
        return userDetailsService.getAll();
    }

    @PostMapping("/users")
    public ResponseEntity addUpdateData(@RequestBody UserDetails userDetails) throws Exception {
        userDetailsService.addUpdateUserDetails(userDetails);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("userId") Integer userId)
    {
        userDetailsService.deleteUserDetailsById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value="/users/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails getUser(@PathVariable("userId") Integer userId)
    {
        return userDetailsService.findById(userId);
    }

    @GetMapping("/users/{name}")
    public List<UserDetails> searchByName(@PathVariable("name") String name) {
        return userDetailsService.searchByName(name);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getLoggedInName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
