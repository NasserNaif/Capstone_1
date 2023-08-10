package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.CategoryModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CategoryService {
    ArrayList<CategoryModel> categories = new ArrayList<>();

    public ArrayList<CategoryModel> getAllCategories() {
        return categories;
    }

    public void addCategory(CategoryModel newCat) {
        categories.add(newCat);
    }


    public boolean updateCategory(CategoryModel newCat, Integer id) {
        for (int i = 0; i < categories.size(); i++) {
            if (Objects.equals(categories.get(i).getId(), id)) {
                categories.set(i, newCat);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(Integer id) {
        for (int i = 0; i < categories.size(); i++) {
            if (Objects.equals(categories.get(i).getId(), id)) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkCategory(Integer id) {
        for (CategoryModel category : categories) {
            if (Objects.equals(category.getId(), id)) {
                return true;
            }
        }
        return false;
    }

}
