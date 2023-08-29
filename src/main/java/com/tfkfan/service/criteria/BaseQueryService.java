package com.tfkfan.service.criteria;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.StringFilter;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

/**
 * @author Baltser Artem tfkfan
 */
@Transactional(
    readOnly = true
)
public abstract class BaseQueryService<ENTITY> extends QueryService<ENTITY> {
    public <OTHER,X> Specification<ENTITY> buildReferringEntityIsNulNotNullSpecification(boolean isNull,
                                                                                         SingularAttribute<? super ENTITY, OTHER> reference,
                                                                                         SingularAttribute<? super OTHER, X> valueField){
        return (root, query, builder) -> isNull ? builder.isNull(root.get(reference).get(valueField)) : builder.isNotNull(root.get(reference).get(valueField));
    }
    public <X extends Comparable<? super X>> Specification<ENTITY> buildIsNullNotNullSpecification(boolean isNull,
                                                                                                     SingularAttribute<ENTITY, X> field) {
        return (root, query, builder) -> isNull ? builder.isNull(root.get(field)) : builder.isNotNull(root.get(field));
    }
}
