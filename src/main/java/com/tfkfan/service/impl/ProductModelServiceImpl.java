package com.tfkfan.service.impl;

import com.tfkfan.domain.ProductModel;
import com.tfkfan.exception.EntityAlreadyExistsException;
import com.tfkfan.exception.EntityNotFoundException;
import com.tfkfan.graphql.PageSettings;
import com.tfkfan.mapper.ProductModelMapper;
import com.tfkfan.repository.ProductModelRepository;
import com.tfkfan.service.ProductModelService;
import com.tfkfan.service.criteria.ProductModelQueryService;
import com.tfkfan.service.criteria.model.ProductModelCriteria;
import com.tfkfan.shared.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.StringFilter;

import java.util.List;
import java.util.Objects;

/**
 * @author Baltser Artem tfkfan
 */
@Service
public class ProductModelServiceImpl   implements ProductModelService {
    private final Logger log = LoggerFactory.getLogger(ProductModelServiceImpl.class);

    private final ProductModelMapper mapper;
    private final ProductModelRepository repository;
    private final ProductModelQueryService queryService;

    public ProductModelServiceImpl(ProductModelMapper mapper, ProductModelRepository repository, ProductModelQueryService queryService) {
        this.mapper = mapper;
        this.repository = repository;
        this.queryService = queryService;
    }

    @Override
    public ProductModel create(String code, String name, String description) {
        log.debug("Request to save Model : {}", code);

        queryService.findOneByCriteria(new ProductModelCriteria()
                .code((StringFilter) new StringFilter().setEquals(code)))
            .ifPresent((e) -> {
            throw EntityAlreadyExistsException.buildForModel(code);
        });

        ProductModel entity = new ProductModel();

        entity.setDescription(description);
        entity.setName(name);
        entity.setCode(code);

        return repository.save(entity);
    }

    @Override
    public ProductModel update(String code, String name, String description) {
        log.debug("Request to update Model : {}", code);

        final ProductModel entity = queryService
            .findOneByCriteria(new ProductModelCriteria()
                .code((StringFilter) new StringFilter().setEquals(code)))
            .orElseThrow(() -> EntityNotFoundException.buildForModel(code));

        if (Objects.nonNull(name))
            entity.setName(name);

        entity.setDescription(description);

        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductModel> findAll(List<String> codes, String name, String description, PageSettings pageSettings) {
        log.debug("Request to get all Models");

        Pageable pageable = PageUtil.pageable(pageSettings);

        ProductModelCriteria criteria = new ProductModelCriteria();
        if (Objects.nonNull(codes) && !codes.isEmpty())
            criteria.setCode((StringFilter) new StringFilter().setIn(codes));
        if(!StringUtils.isEmpty(name))
            criteria.setName(new StringFilter().setContains(name));
        if(!StringUtils.isEmpty(description))
            criteria.setDescription(new StringFilter().setContains(description));

        return queryService.findByCriteria(criteria, pageable);
    }
}
