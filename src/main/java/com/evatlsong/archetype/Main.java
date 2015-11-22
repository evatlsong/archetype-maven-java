package com.evatlsong.archetype;

import com.evatlsong.archetype.config.ArchetypeConfig;
import com.evatlsong.archetype.model.Person;
import com.evatlsong.archetype.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by evatlsong on 15-11-20.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(ArchetypeConfig.class);
        PersonService personService = config.getBean(PersonService.class);

        Person person = new Person();
        person.setFirstname("song");
        person.setLastname("tie");
        personService.save(person);
        List<Person> personList = personService.getPerson();

        System.out.println(personList.size());
    }
}
