package com.myapp.dao;

/**
 * Created by mona.p on 05/04/18.
 */
public interface CounterDAO {
    Integer getNextSequenceId(String key) throws Exception;
}
