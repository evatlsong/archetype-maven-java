package com.evatlsong.archetype.dao.hibernate;

import com.evatlsong.archetype.dao.PersonDAO;
import com.evatlsong.archetype.model.Person;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by evatlsong on 15-11-21.
 */
@Repository
public class PersonHibernateDAO extends GenericHibernateDAO<Person, Long>
        implements PersonDAO {
}
