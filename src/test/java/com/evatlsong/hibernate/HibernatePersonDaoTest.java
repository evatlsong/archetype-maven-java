package com.evatlsong.hibernate;


import com.evatlsong.archetype.dao.HibernatePersonDao;
import com.evatlsong.archetype.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by evatlsong on 15-8-14.
 */
public class HibernatePersonDaoTest {
    private SessionFactory factory;
    private Session session;
    private Query query;
    @Before
    public void setUp() {
        factory = createMock(SessionFactory.class);
        session = createMock(Session.class);
        query = createMock(Query.class);
    }
    @Test
    public void testFindByLastname() {
        String hql = "from Person p where p.lastName = :lastname";
        String lastname = "Smith";

        List<Person> theSmiths = new ArrayList<Person>();
        theSmiths.add(new Person("Alice", lastname));
        theSmiths.add(new Person("Billy", lastname));
        theSmiths.add(new Person("Clark", lastname));

        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("lastname", lastname)).andReturn(query);
        expect(query.list()).andReturn(theSmiths);

        replay(factory, session, query);

        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);
        assertEquals(theSmiths, dao.findByLastname(lastname));

        verify(factory, session, query);

    }
}
