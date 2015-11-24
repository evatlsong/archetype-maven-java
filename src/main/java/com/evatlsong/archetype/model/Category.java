package com.evatlsong.archetype.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by evatlsong on 15-11-22.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = true, foreignKey = @ForeignKey(name = "FK_CATEGORY_PARENT_ID"))
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private Set<Category> childCategories = new HashSet<Category>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }
}
