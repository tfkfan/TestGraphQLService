package com.tfkfan.service.impl;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;
import com.tfkfan.exception.EntityAlreadyExistsException;
import com.tfkfan.exception.EntityNotFoundException;
import com.tfkfan.graphql.CategoryModelResult;
import com.tfkfan.graphql.CategoryModelsResult;
import com.tfkfan.graphql.PageSettings;
import com.tfkfan.mapper.CategoryModelMapper;
import com.tfkfan.repository.CategoryProductModelRepository;
import com.tfkfan.service.CategoryModelService;
import com.tfkfan.service.criteria.CategoryQueryService;
import com.tfkfan.service.criteria.ProductModelQueryService;
import com.tfkfan.service.criteria.model.CategoryCriteria;
import com.tfkfan.service.criteria.model.ProductModelCriteria;
import com.tfkfan.shared.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.StringFilter;

/**
 * @author Baltser Artem tfkfan
 */
@Service
@Transactional
public class CategoryModelServiceImpl  implements CategoryModelService {
    private final Logger log = LoggerFactory.getLogger(CategoryModelServiceImpl.class);

    private final CategoryQueryService categoryQueryService;
    private final ProductModelQueryService modelQueryService;
    private final CategoryProductModelRepository repository;
    private final CategoryModelMapper categoryModelMapper;

    public CategoryModelServiceImpl(CategoryQueryService categoryQueryService,
                                    ProductModelQueryService modelQueryService,
                                    CategoryProductModelRepository repository,
                                    CategoryModelMapper categoryModelMapper) {
        this.categoryQueryService = categoryQueryService;
        this.modelQueryService = modelQueryService;
        this.repository = repository;
        this.categoryModelMapper = categoryModelMapper;
    }

    @Override
    public CategoryModelResult linkCategoryToModel(String categoryCode, String modelCode){
        final CategoryProductModel catmodel = createModelFromExistingEntities(categoryCode, modelCode);
        repository.findById(catmodel.getId()).ifPresent(categoryProductModel -> {
            throw EntityAlreadyExistsException.buildForCategoryModel(categoryCode,modelCode);
        });

        return categoryModelMapper.toResponse(repository.save(catmodel));
    }

    @Override
    public  CategoryModelResult unlinkCategoryToModel(String categoryCode, String modelCode) {
        final CategoryProductModel catmodel = createModelFromExistingEntities(categoryCode, modelCode);
        return repository.findById(catmodel.getId()).map(e -> {
            repository.delete(e);
            return categoryModelMapper.toResponse(e);
        }).orElseThrow(() -> EntityNotFoundException.buildForCategoryModel(categoryCode,
            modelCode));
    }

    @Override
    public CategoryModelsResult findModelsByCategoryCode(String categoryCode, PageSettings pageSettings){
        final Category category = categoryQueryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter().setEquals(categoryCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(categoryCode));

        Pageable pageable = PageUtil.pageable(pageSettings);

        Page<ProductModel> page = modelQueryService.findByCriteria(
            new ProductModelCriteria()
                .categoryCode((StringFilter) new StringFilter().setEquals(category.getCode())), pageable
        );
        return categoryModelMapper.toFindModelsByCategoryCodeResponse(category, page);
    }

    private CategoryProductModel createModelFromExistingEntities(String categoryCode, String modelCode) {
        final Category category = categoryQueryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter().setEquals(categoryCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(categoryCode));

        final ProductModel model = modelQueryService.findOneByCriteria(new ProductModelCriteria()
            .code((StringFilter) new StringFilter().setEquals(modelCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForModel(modelCode));

        return new CategoryProductModel(category, model);
    }
}
