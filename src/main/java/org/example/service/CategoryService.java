package org.example.service;

import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryEntity getCategoryByName(String categoryName) {
        return  categoryRepository.getCategoryByName(categoryName);
    }

    public void getAllCategory() {
        List<CategoryEntity> categoryEntities=categoryRepository.getAllCategory();
        if(categoryEntities.isEmpty()){
            System.out.println("There is no category was found!");
        }else {
            categoryEntities.forEach(System.out::println);
        }
    }

    public void addCategory(String categoryName) {
        CategoryEntity isExists=categoryRepository.getCategoryByName(categoryName);
        if(isExists !=null && isExists.isVisible()){
            System.out.println("This type of category is exists!");
            return;
        } else if (isExists !=null && !isExists.isVisible()) {
            isExists.setVisible(true);
            categoryRepository.updateCategory(isExists);
            System.out.println("Category was created successfully!");
            return;
        }
        CategoryEntity category= new CategoryEntity();
        category.setCategoryName(categoryName);
        category.setVisible(true);
        category.setCreatedDate(LocalDateTime.now());
        categoryRepository.addCategory(category);
        System.out.println("Category was created successfully!");
    }

    public void removeCategory(Integer id) {
        int n=categoryRepository.removeCategory(id);
        if(n>0){
            System.out.println("Category removed successfully!");
        }else {
            System.out.println("Category not found!");
        }
    }
}
