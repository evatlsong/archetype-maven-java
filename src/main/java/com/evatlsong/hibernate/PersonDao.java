package com.evatlsong.hibernate;

import java.util.List;

/**
 * Created by evatlsong on 15-8-14.
 */
public interface PersonDao {
    List<Person> findByLastname(String lastname);
}
