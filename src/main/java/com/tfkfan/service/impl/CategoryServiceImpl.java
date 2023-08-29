package com.tfkfan.service.impl;

import com.tfkfan.domain.Category;
import com.tfkfan.exception.EntityAlreadyExistsException;
import com.tfkfan.exception.EntityNotFoundException;
import com.tfkfan.exception.EntitySubordinationException;
import com.tfkfan.graphql.PageSettings;
import com.tfkfan.repository.CategoryRepository;
import com.tfkfan.service.CategoryService;
import com.tfkfan.service.criteria.CategoryQueryService;
import com.tfkfan.service.criteria.model.CategoryCriteria;
import com.tfkfan.shared.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository repository;
    private final CategoryQueryService queryService;

    public CategoryServiceImpl(CategoryRepository repository, CategoryQueryService queryService) {
        this.repository = repository;
        this.queryService = queryService;
    }

    @Override
    public Category create(String code, String name, String description, String parentCategoryCode) {
        log.debug("Request to save Category : {}", code);
        if (!StringUtils.isEmpty(parentCategoryCode) && code.equals(parentCategoryCode))
            throw EntitySubordinationException.buildForCategory(Map.of("parentCategoryCode", parentCategoryCode,
                "code", code));

        final Category category = new Category();
        if (!StringUtils.isEmpty(parentCategoryCode)) {
            final Category parent = queryService
                .findOneByCriteria(new CategoryCriteria()
                    .code((StringFilter) new StringFilter().setEquals(parentCategoryCode))
                    .parentCategoryIdNull(true))
                .orElseThrow(() -> EntityNotFoundException.buildForParentCategory(parentCategoryCode));
            category.setParentCategory(parent);
        }

        queryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter()
                .setEquals(code)))
            .ifPresent((e) -> {
                throw EntityAlreadyExistsException.buildForCategory(code);
            });

        category.setCode(code);
        category.setName(name);
        category.setDescription(description);
        return repository.save(category);
    }

    @Override
    public Category update(String code, String name, String description, Boolean isHidden, String parentCategoryCode) {
        log.debug("Request to partially update Category : {}", code);

        final Category entity = queryService
            .findOneByCriteria(new CategoryCriteria().code((StringFilter) new StringFilter().setEquals(code)))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(code));

        if (!StringUtils.isEmpty(parentCategoryCode) && entity.getCode().equals(parentCategoryCode))
            throw EntitySubordinationException.buildForCategory(Map.of("parentCategoryCode",
                parentCategoryCode, "code", entity.getCode()));

        if (!StringUtils.isEmpty(parentCategoryCode)) {
            final Category parent = queryService
                .findOneByCriteria(new CategoryCriteria()
                    .code((StringFilter) new StringFilter().setEquals(parentCategoryCode))
                    .parentCategoryIdNull(true))
                .orElseThrow(() -> EntityNotFoundException.buildForParentCategory(parentCategoryCode));

            entity.setParentCategory(parent);
        }

        if (Objects.nonNull(name))
            entity.setName(name);
        if (Objects.nonNull(isHidden))
            entity.setIsHidden(isHidden);

        entity.setDescription(description);

        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAll(List<String> codes, String name, String description, List<String> parentCategoryCodes, Boolean isHidden,
                                  Boolean onlyParent, PageSettings pageSettings) {
        log.debug("Request to get all Categories");

        Pageable pageable = PageUtil.pageable(pageSettings);

        if (Objects.nonNull(parentCategoryCodes) && !parentCategoryCodes.isEmpty()) {
            List<Category> parentCategories = queryService.findByCriteria(new CategoryCriteria()
                .code((StringFilter) new StringFilter().setIn(parentCategoryCodes))
                .parentCategoryIdNull(true));

            if (Objects.isNull(codes))
                codes = new ArrayList<>();

            codes.addAll(queryService
                .findByCriteria(new CategoryCriteria()
                    .parentCategoryId((LongFilter)
                        new LongFilter().setIn(parentCategories
                            .stream()
                            .map(Category::getId)
                            .collect(Collectors.toList()))))
                .stream()
                .map(Category::getCode)
                .collect(Collectors.toList()));
        }

        CategoryCriteria criteria = new CategoryCriteria();
        if (Objects.nonNull(codes) && !codes.isEmpty())
            criteria.setCode((StringFilter) new StringFilter().setIn(codes));
        if (!StringUtils.isEmpty(name))
            criteria.setName(new StringFilter().setContains(name));
        if (!StringUtils.isEmpty(description))
            criteria.setDescription(new StringFilter().setContains(description));
        if (Objects.nonNull(isHidden))
            criteria.setIsHidden((BooleanFilter) new BooleanFilter().setEquals(isHidden));
        if (Objects.nonNull(onlyParent))
            criteria.setParentCategoryIdNull(true);

        return queryService.findByCriteria(criteria, pageable);
    }
}
