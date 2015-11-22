package com.evatlsong.archetype.service.impl;

import com.evatlsong.archetype.dao.PersonDAO;
import com.evatlsong.archetype.model.Person;
import com.evatlsong.archetype.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by evatlsong on 15-11-21.
 */
@Service
@Transactional
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Override
    public List<Person> getPerson() {
        return personDAO.findAll();
    }
}
