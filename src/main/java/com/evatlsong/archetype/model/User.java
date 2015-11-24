package com.evatlsong.archetype.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * A user of the CaveatEmptor auction application.
 *
 * @author Christian Bauer
 */
@Entity
@Table(name = "USERS")
@SecondaryTable(
    name = "BILLING_ADDRESS",
    pkJoinColumns = {
        @PrimaryKeyJoinColumn(name="USER_ID")
    }
)
@org.hibernate.annotations.BatchSize(size = 10)
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id = null;

    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;

    @Column(name = "FIRSTNAME", length = 255, nullable = false)
    private String firstname;

    @Column(name = "LASTNAME", length = 255, nullable = false)
    private String lastname;

    @Column(name = "USERNAME", length = 16, nullable = false, unique = true)
    //@org.hibernate.annotations.Check( constraints = "regexp_like(USERNAME,'^[[:alpha:]]+$')" )
    private String username; // Unique and immutable

    @Column(name = "`PASSWORD`", length = 12, nullable = false)
    private String password;

    @Column(name = "EMAIL", length = 255, nullable = false)
    private String email;

    @Column(name = "RANK", nullable = false)
    private int ranking = 0;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean admin = false;

    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name   = "street",
                           column = @Column(name="HOME_STREET", length = 255) ),
        @AttributeOverride(name   = "zipcode",
                           column = @Column(name="HOME_ZIPCODE", length = 16) ),
        @AttributeOverride(name   = "city",
                           column = @Column(name="HOME_CITY", length = 255) )
        })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(
            name   = "street",
            column = @Column(name="STREET", length = 255,
                             table = "BILLING_ADDRESS")
        ),
        @AttributeOverride(
            name   = "zipcode",
            column = @Column(name="ZIPCODE", length = 16,
                             table = "BILLING_ADDRESS")
        ),
        @AttributeOverride(
            name   = "city",
            column = @Column(name="CITY", length = 255,
                             table = "BILLING_ADDRESS")
        )
    })
    // TODO: This is ignored: @org.hibernate.annotations.ForeignKey(name = "FK_BILLING_ADDRESS_ID")
    private Address billingAddress;


    /**
     * No-arg constructor for JavaBean tools
     */
    public User() {}



    /**
     * Simple constructor.
     */
    public User(String firstname, String lastname,
                String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }
    public int getVersion() { return version; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public Address getHomeAddress() { return homeAddress; }
    public void setHomeAddress(Address homeAddress) { this.homeAddress = homeAddress; }

    public Address getBillingAddress() { return billingAddress; }
    public void setBillingAddress(Address billingAddress) { this.billingAddress = billingAddress; }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        final User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    public int hashCode() {
        return getUsername().hashCode();
    }

    public String toString() {
        return  "User ('" + getId() + "'), " +
                "Username: '" + getUsername() + "'";
    }



    // ********************** Business Methods ********************** //


}
