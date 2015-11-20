package com.evatlsong.archetype.dao;

import com.evatlsong.archetype.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by evatlsong on 15-8-14.
 */
@Repository
@Transactional
public class HibernatePersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory =  sessionFactory;
    }

    public List<Person> findByLastname(String lastname) {
        Session session = sessionFactory.getCurrentSession();
        String hql =  "from Person p where p.lastName = :lastname";
        Query query = session.createQuery(hql);
        query.setParameter("lastname", lastname);
        List<Person> personList = query.list();
        System.out.println(personList.size());
        return personList;
    }
}
