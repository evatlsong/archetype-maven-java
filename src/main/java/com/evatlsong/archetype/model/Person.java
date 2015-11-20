package com.evatlsong.archetype.model;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String name) {
        this.firstName = name;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String name) {
        this.lastName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        Person other = (Person) obj;
        return firstName.equals(other.firstName)
                && lastName.equals(other.lastName);
    }

    public String toString() {
        return "{" + firstName + " " + lastName + "}";
    }

}
