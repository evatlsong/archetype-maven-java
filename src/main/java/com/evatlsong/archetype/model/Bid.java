package com.evatlsong.archetype.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * An immutable class representing one bid.
 * <p/>
 * If the "successful" property is used in a legacy situation
 * (see book chapter 8), it is no longer an immutable class with
 * consequences for second-level caching.
 * <p/>
 * Note: This legacy mapping isn't currently possible with annotations.
 *
 * @author Christian Bauer
 * @see Item
 * @see User
 */
@Entity
@Table(name = "BID")
@Immutable
public class Bid implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BID_ID")
    private Long id = null;


    @ManyToOne
    @JoinColumn(
            name = "ITEM_ID",
            foreignKey = @ForeignKey(name = "FK_ITEM_ID"),
            nullable = false,
            updatable = false,
            insertable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(
            name = "BIDDER_ID",
            foreignKey = @ForeignKey(name = "FK_BIDDER_ID"),
            nullable = false,
            updatable = false)
    private User bidder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false, updatable = false)
    private Date created = new Date();

    // TODO: This can't be mapped in annotations, there is no <properties> grouping
    @Transient
    private boolean successful = false;

    /**
     * No-arg constructor for JavaBean tools
     */
    public Bid() {
    }


    // ********************** Accessor Methods ********************** //

    public Long getId() {
        return id;
    }


    public Item getItem() {
        return item;
    }

    public User getBidder() {
        return bidder;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;

        final Bid bid = (Bid) o;

        if (!getItem().getId().equals(bid.getItem().getId())) return false;
        if (!(created.getTime() == bid.created.getTime())) return false;


        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 29 * result + created.hashCode();
        return result;
    }

    public String toString() {
        return "Bid ('" + getId() + "'), " +
                "Created: '" + getCreated() + "'";
    }

    public int compareTo(Object o) {
        if (o instanceof Bid) {
            // Don't compare Date objects! Use the time in milliseconds!
            return Long.valueOf(this.getCreated().getTime()).compareTo(
                    Long.valueOf(((Bid) o).getCreated().getTime())
            );
        }
        return 0;
    }

    // ********************** Business Methods ********************** //

}