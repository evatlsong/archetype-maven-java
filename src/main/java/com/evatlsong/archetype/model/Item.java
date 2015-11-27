package com.evatlsong.archetype.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/**
 * An item for auction.
 *
 * @author Christian Bauer
 */
@Entity
@Table(
        name = "ITEM",
        indexes = {
                @Index(name = "IDX_AAAAA", columnList = "DESCRIPTION, SELLER_ID"),
                @Index(name = "IDX_END_DATE", columnList = "END_DATE")
        }
)
@org.hibernate.annotations.DynamicInsert
public class Item implements Serializable, Comparable {

    private static final String COLLECTION_ID_GENERATOR = "identity";

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id = null;

    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;

    /**
     * hello
     */
    @Column(name = "ITEM_NAME", length = 255, nullable = false, updatable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "SELLER_ID",
            foreignKey = @ForeignKey(name = "FK_SELLER_ID"),
            nullable = false,
            updatable = false)
    private User seller;


    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = false, updatable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false, updatable = false)

    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITEM_STATE", length = 5, nullable = false)
    private ItemState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "APPROVED_BY_USER_ID",
            foreignKey = @ForeignKey(name = "FK_APPROVED_BY_USER_ID"),
            nullable = true)
    private User approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVAL_DATETIME", nullable = true)
    private Date approvalDatetime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    @org.hibernate.annotations.IndexColumn(name = "BID_POSITION")
    @OrderColumn(name = "BID_POSITION")
    @org.hibernate.annotations.ListIndexBase(1)
    @org.hibernate.annotations.BatchSize(size = 10)
    private List<Bid> bids = new ArrayList<Bid>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUCCESSFUL_BID_ID", nullable = true)
    @org.hibernate.annotations.ForeignKey(name = "FK_SUCCESSFUL_BID_ID")
    private Bid successfulBid;

    @MapKey(name = "id")
    @OneToMany(mappedBy = "item")
    private Map<Long, Bid> bidsByIdentifier = new HashMap<Long, Bid>(); // Not very useful

    @ElementCollection
    @org.hibernate.annotations.CollectionId(
            columns = @Column(name = "ITEM_IMAGE_ID"),
            type = @org.hibernate.annotations.Type(type = "long"),
            generator = COLLECTION_ID_GENERATOR
    )
    @JoinTable(
            name = "ITEM_IMAGES",
            joinColumns = @JoinColumn(name = "ITEM_ID",
                    foreignKey = @ForeignKey(name = "FK_ITEM_IMAGE_ITEM_ID"))
    )
    @Column(name = "FILENAME")
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<String> images = new ArrayList<String>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false, updatable = false)
    private Date created = new Date();

    /**
     * No-arg constructor for JavaBean tools.
     */
    public Item() {
    }


    // ********************** Accessor Methods ********************** //

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public User getSeller() {
        return seller;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ItemState getState() {
        return state;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovalDatetime() {
        return approvalDatetime;
    }

    public void setApprovalDatetime(Date approvalDatetime) {
        this.approvalDatetime = approvalDatetime;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        if (bid == null)
            throw new IllegalArgumentException("Can't add a null Bid.");
        this.getBids().add(bid);
        // Don't have to set the "other" side, a Bid can only be instantiated with a given item
    }

    public Bid getSuccessfulBid() {
        return successfulBid;
    }

    public void setSuccessfulBid(Bid successfulBid) {
        // Has to preserve the integrity by using a procedure and loop, bad Java...
        if (successfulBid != null) {
            for (Bid bid : getBids())
                bid.setSuccessful(false);
            successfulBid.setSuccessful(true);
            this.successfulBid = successfulBid;
        }
    }

    public Map<Long, Bid> getBidsByIdentifier() {
        return bidsByIdentifier;
    }

    public void setBidsByIdentifier(Map<Long, Bid> bidsByIdentifier) {
        this.bidsByIdentifier = bidsByIdentifier;
    }


    public Collection<String> getImages() {
        return images;
    }

    public Date getCreated() {
        return created;
    }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        final Item item = (Item) o;

        if (!(created.getTime() == item.created.getTime())) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + created.hashCode();
        return result;
    }

    public String toString() {
        return "Item ('" + getId() + "'), " +
                "Name: '" + getName() + "'";
    }

    public int compareTo(Object o) {
        if (o instanceof Item) {
            // Don't compare Date objects! Use the time in milliseconds!
            return Long.valueOf(this.getCreated().getTime()).compareTo(
                    Long.valueOf(((Item) o).getCreated().getTime())
            );
        }
        return 0;
    }

    // ********************** Business Methods ********************** //


    /**
     * Anyone can set this item into PENDING state for approval.
     */
    public void setPendingForApproval() {
        state = ItemState.PENDING;
    }


}
