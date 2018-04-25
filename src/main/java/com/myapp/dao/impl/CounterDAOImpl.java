package com.myapp.dao.impl;

import com.myapp.dao.CounterDAO;
import com.myapp.dao.beans.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by mona.p on 05/04/18.
 */
@Repository
public class CounterDAOImpl implements CounterDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer getNextSequenceId(String key) throws Exception {
        Query query = new Query(Criteria.where("id").is(key));
        Update update = new Update().inc("sequence", 1);
        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class);
        return counter.getSequence();
    }
}
