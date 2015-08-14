package com.evatlsong.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by evatlsong on 15-8-14.
 */
public class HibernatePersonDao implements PersonDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory =  sessionFactory;
    }

    @Override
    public List<Person> findByLastname(String lastname) {
        Session session = sessionFactory.getCurrentSession();
        String hql =  "from Person p where p.lastname = :lastname";
        Query query = session.createQuery(hql);
        query.setParameter("lastname", lastname);
        return query.list();
    }
}
