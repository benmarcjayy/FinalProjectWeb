package ca.sait.services;

import ca.sait.dataaccess.CategoryDB;
import ca.sait.models.Category;
import java.util.List;

/**
 *
 * @author Benmarc Agulto
 */
public class CategoryService {

    public List<Category> getAll() {
        CategoryDB categoryDb = new CategoryDB();

        return categoryDb.getAll();
    }

    public void addCategory(String newCategoryName) {
        if (newCategoryName != null) {
            CategoryDB categoryDb = new CategoryDB();
            Category newCategory = new Category(getNextCategoryIdNumber(), newCategoryName);

            categoryDb.addCategory(newCategory);
        }
    }

    private int getNextCategoryIdNumber() {
        List<Category> CategoryList = (List<Category>) getAll();

        return CategoryList.get(CategoryList.size() - 1).getCategoryId() + 1;
    }

    public void updateCategory(Category selectedCategory) {
        if (selectedCategory != null) {
            CategoryDB categoryDb = new CategoryDB();
            categoryDb.updateCategory(selectedCategory);
        }
    }
}
