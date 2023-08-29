package com.tfkfan.service;

import com.tfkfan.domain.Category;
import com.tfkfan.graphql.PageSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Category}.
 */
public interface CategoryService {
    Category create(String code, String name, String description, String parent_category_code);

    Category update(String code, String name, String description, Boolean isHidden, String parent_category_code);

    Page<Category> findAll(List<String> codes, String name, String description, List<String> parent_category_codes, Boolean is_hidden, Boolean only_parent, PageSettings page_settings);
}
