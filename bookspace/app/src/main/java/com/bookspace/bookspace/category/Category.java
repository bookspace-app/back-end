package com.bookspace.bookspace.category;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bookspace.bookspace.enums.CategoryEnum;
import com.bookspace.bookspace.publication.Publication;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(
        name = "user_sequence", 
        sequenceName = "user_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "user_sequence"
    )
    private Long id;
    

    @Column(name = "theme", unique = true)
    private CategoryEnum category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Publication> category_publications;

    //Test constructor
    public Category() {
        this.id = 1L;
        this.category = CategoryEnum.WAR;
        this.category_publications = null;
    }
}
