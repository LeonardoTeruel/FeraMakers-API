package com.teruel.feramakers.Mappers;

import com.teruel.feramakers.dto.CategoryDTO;
import com.teruel.feramakers.model.Category;
import com.teruel.feramakers.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(category.getPosts()))")
    CategoryDTO mapCategoryToDTO(Category category);

    default Integer mapPosts (List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Category mapDtoToCategory(CategoryDTO categoryDTO);

}
