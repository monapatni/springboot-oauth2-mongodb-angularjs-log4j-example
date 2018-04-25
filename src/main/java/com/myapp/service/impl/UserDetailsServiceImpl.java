package com.myapp.service.impl;

import com.myapp.dao.CounterDAO;
import com.myapp.dao.beans.UserDetails;
import com.myapp.dao.UserDetailsRepository;
import com.myapp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mona.p on 05/04/18.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USER_DETAIL_SEQ_KEY = "user_id";

    @Autowired
    private CounterDAO counterDAO;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<UserDetails> getAll() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserDetails findById(Integer id) {
        return userDetailsRepository.findOne(id);
    }

    @Override
    public void addUpdateUserDetails(UserDetails userDetails) throws Exception {
        if (userDetails.getId() == null) {
            userDetails.setId(counterDAO.getNextSequenceId(USER_DETAIL_SEQ_KEY));
        }
        userDetailsRepository.save(userDetails);
    }

    @Override
    public void deleteUserDetailsById(Integer userId) {
        userDetailsRepository.delete(userId);
    }

    public List<UserDetails> searchByName(String name) {
        UserDetails userDetails = new UserDetails();
        Example<UserDetails> example = Example.of(userDetails);
        return userDetailsRepository.findAll(example);
    }
}
