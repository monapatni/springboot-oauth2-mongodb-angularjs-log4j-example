package com.myapp.service;

import com.myapp.dao.beans.UserDetails;

import java.util.List;

/**
 * Created by mona.p on 05/04/18.
 */
public interface UserDetailsService {
    List<UserDetails> getAll();

    UserDetails findById(Integer id);

    void addUpdateUserDetails(UserDetails userDetails) throws Exception;

    void deleteUserDetailsById(Integer userId);

    List<UserDetails> searchByName(String name);
}
