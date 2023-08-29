package com.tfkfan.service;

import com.tfkfan.domain.ProductModel;
import com.tfkfan.graphql.PageSettings;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service Interface for managing {@link ProductModel}.
 */
public interface ProductModelService {
    ProductModel create( String code, String name, String description);

    ProductModel update( String code, String name, String description);

    Page<ProductModel> findAll(List<String> codes, String name, String description, PageSettings pageSettings);

}
