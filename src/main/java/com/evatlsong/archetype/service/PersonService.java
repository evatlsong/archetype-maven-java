package com.evatlsong.archetype.service;

import com.evatlsong.archetype.dao.PersonDAO;
import com.evatlsong.archetype.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by evatlsong on 15-11-21.
 */
@Service
@Transactional
public interface PersonService extends GenericService<Person, Long> {
    List<Person> getPerson();
}
