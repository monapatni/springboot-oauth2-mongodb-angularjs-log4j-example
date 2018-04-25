package com.myapp.dao;

import com.myapp.dao.beans.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mona.p on 05/04/18.
 */
public interface UserDetailsRepository extends MongoRepository<UserDetails, Integer> {
}
