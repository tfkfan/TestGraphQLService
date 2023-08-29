package com.tfkfan.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {
    public static final String ENTITY_NAME = "category";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", initialValue = 50, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @NotNull
    @Column(name = "is_hidden", nullable = false)
    private Boolean isHidden = false;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Column(name = "modification_date", nullable = false)
    private Instant modificationDate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "category_model",
        joinColumns = { @JoinColumn(name = "category_id") },
        inverseJoinColumns = { @JoinColumn(name = "model_id") }
    )
    private Set<ProductModel> models = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        creationDate = Instant.now();
        onUpdate();
    }

    @PreUpdate
    protected void onUpdate() {
        modificationDate = Instant.now();
    }

    public Long getId() {
        return this.id;
    }

    public Category id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Category code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Category name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getParentCategory() {
        return this.parentCategory;
    }

    public Category parentCategoryCode(Category parentCategoryCode) {
        this.setParentCategory(parentCategoryCode);
        return this;
    }

    public void setParentCategory(Category parentCategoryCode) {
        this.parentCategory = parentCategoryCode;
    }

    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public Category is_hidden(Boolean is_hidden) {
        this.setIsHidden(is_hidden);
        return this;
    }

    public void setIsHidden(Boolean is_hidden) {
        this.isHidden = is_hidden;
    }

    public Instant getCreationDate() {
        return this.creationDate;
    }

    public Category creation_date(Instant creation_date) {
        this.setCreationDate(creation_date);
        return this;
    }

    public void setCreationDate(Instant creation_date) {
        this.creationDate = creation_date;
    }

    public Instant getModificationDate() {
        return this.modificationDate;
    }

    public Category modification_date(Instant modification_date) {
        this.setModificationDate(modification_date);
        return this;
    }

    public void setModificationDate(Instant modification_date) {
        this.modificationDate = modification_date;
    }

    public Set<ProductModel> getModels() {
        return models;
    }

    public void setModels(Set<ProductModel> projects) {
        this.models = projects;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return new EqualsBuilder().append(id, category.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", parentCategoryCode=" + getParentCategory() +
            ", isHidden='" + getIsHidden() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            "}";
    }
}
