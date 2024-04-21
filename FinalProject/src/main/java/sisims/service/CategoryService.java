package sisims.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import sisims.controller.model.SisimsData.CategoryData;
import sisims.dao.CategoryDao;
import sisims.dao.ItemDao;
import sisims.entity.Category;
import sisims.entity.Item;



@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@PostConstruct
	public void ensureDefaultCategoryExists() {
	    final String defaultCategoryName = "Unspecified";
	    Category defaultCategory = categoryDao.findByCategoryName(defaultCategoryName)
	        .orElseGet(() -> {
	            Category newCategory = new Category();
	            newCategory.setCategoryName(defaultCategoryName);
	            newCategory.setCategoryDescription("Default category for unassigned or removed category items.");
	            return categoryDao.save(newCategory);
	        });
	    log.info("Default category ensured: {}", defaultCategory);
	}
	
	
	@Transactional(readOnly = false)
	public CategoryData saveACategory(CategoryData categoryData) {
		log.info("Saving new category with data: {}", categoryData);
	    Category category = new Category();
	    copyCategoryFields(category, categoryData);
	    Category savedCategory = categoryDao.save(category);
	    return convertToCategoryData(savedCategory);
	}

	private void copyCategoryFields(Category category, CategoryData categoryData) {
	    category.setCategoryName(categoryData.getCategoryName());
	    category.setCategoryDescription(categoryData.getCategoryDescription());
	    log.info("Fields set for category: Name - {}, Description - {}", category.getCategoryName(), category.getCategoryDescription());
	}
	
	private CategoryData convertToCategoryData(Category category) {
	    if (category == null) {
	        log.error("convertToCategoryData called with null Category entity.");
	        return null;
	    }
	    CategoryData categoryData = new CategoryData();
	    categoryData.setCategoryId(category.getCategoryId());
	    categoryData.setCategoryName(category.getCategoryName());
	    categoryData.setCategoryDescription(category.getCategoryDescription());
	    categoryData.setItems(null); // Assuming you handle items differently or later.
	    categoryData.setItemCount(category.getItemCountInCategory());
	    return categoryData;
	}
	
	@Transactional(readOnly = true)
	public CategoryData getCategoryWithId(Long categoryId) {
	    Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with ID:" + categoryId + " not found"));
	    CategoryData categoryData = new CategoryData(category);
	    categoryData.setItemCount(category.getItems().size());
	    return categoryData;
	}
	@Transactional(readOnly = false)
	public CategoryData updateCategoryWithID(Long categoryId, CategoryData categoryData) {
		Category category = categoryDao.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
		copyCategoryFields(category, categoryData);
		category = categoryDao.save(category);
		return new CategoryData(category);
	}
	
	@Transactional(readOnly = false)
	public void deleteCategoryWithId(Long categoryId) {
	    Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with ID:" + categoryId + " not found"));
	    Category defaultCategory = categoryDao.findByCategoryName("Unspecified")
	        .orElseThrow(() -> new NoSuchElementException("Default category must exist"));
	    for (Item item : category.getItems()) {
	        item.setCategory(defaultCategory);
	        itemDao.save(item);
	    }
	    categoryDao.delete(category);
	}
}


