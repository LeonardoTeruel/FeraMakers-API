package com.teruel.feramakers.service;

import com.teruel.feramakers.Mappers.CategoryMapper;
import com.teruel.feramakers.dto.CategoryDTO;
import com.teruel.feramakers.exceptions.FeraMakersException;
import com.teruel.feramakers.model.Category;
import com.teruel.feramakers.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    public CategoryDTO save (CategoryDTO categoryDTO) {

        Category categorySaved = categoryRepository.save(categoryMapper.mapDtoToCategory(categoryDTO));

        categoryDTO.setCategoryId(categorySaved.getCategoryId());

        return categoryDTO;
    }

    @Transactional (readOnly = true)
    public List<CategoryDTO> getAllCategories (){

         final List<CategoryDTO> categoriesList = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDTO)
                .collect(Collectors.toList());

         return categoriesList;
    }

    @Transactional (readOnly = true)
    public CategoryDTO getCategory (Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new FeraMakersException("Nenhuma categoria encontrada com id " + categoryId));

        return categoryMapper.mapCategoryToDTO(category);
    }

}
