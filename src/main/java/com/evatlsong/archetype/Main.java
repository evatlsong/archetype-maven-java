package com.evatlsong.archetype;

import com.evatlsong.archetype.config.ArchetypeConfig;
import com.evatlsong.archetype.dao.HibernatePersonDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by evatlsong on 15-11-20.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(ArchetypeConfig.class);
        HibernatePersonDao personDao = config.getBean(HibernatePersonDao.class);
        personDao.findByLastname("song");
    }
}
