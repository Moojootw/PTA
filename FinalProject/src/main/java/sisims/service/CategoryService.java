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
	//Make sure that there is always a category for items to have reference to should their category get removed
	//this category current final name of "Unspecified"
	public void ensureDefaultCategoryExists() {
	    final String defaultCategoryName = "Unspecified";
	    Category defaultCategory = categoryDao.findByCategoryName(defaultCategoryName).orElseGet(() -> {
	            Category newCategory = new Category();
	            newCategory.setCategoryName(defaultCategoryName);
	            newCategory.setCategoryDescription("Default category for unassigned or removed category items.");
	            return categoryDao.save(newCategory);
	        });
	    log.info("Default category ensured current ID: {}", defaultCategory.getCategoryId());
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
	        log.error("Null not allowed with convertToCategoryData");
	        return null;
	    }
	    CategoryData categoryData = new CategoryData();
	    categoryData.setCategoryId(category.getCategoryId());
	    categoryData.setCategoryName(category.getCategoryName());
	    categoryData.setCategoryDescription(category.getCategoryDescription());
	    categoryData.setItems(null); //just to be safe
	    categoryData.setItemCount(category.getItemCountInCategory());
	    return categoryData;
	}
	
	@Transactional(readOnly = true)
	public CategoryData getCategoryWithId(Long categoryId) {
	    Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with ID:" + categoryId + " not found"));
	    CategoryData categoryData = new CategoryData(category);
	    categoryData.setItemCount(category.getItems().size()); //this is where the GET request fills out the item count
	    return categoryData;
	}
	
	@Transactional(readOnly = false)
	public CategoryData updateCategoryWithID(Long categoryId, CategoryData categoryData) {
		Category category = categoryDao.findById(categoryId).orElseThrow(() -> new RuntimeException("Category with ID:" +categoryId + " does not exist"));
		copyCategoryFields(category, categoryData);
		category = categoryDao.save(category);
		return new CategoryData(category);
	}
	
	@Transactional(readOnly = false)
	//Custom
	//Deleteing a category has a lot of issues that are addressed here
	public void deleteCategoryWithId(Long categoryId) {
	    Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with ID:" + categoryId + " not found"));
	    Category defaultCategory = categoryDao.findByCategoryName("Unspecified")
	        .orElseThrow(() -> new NoSuchElementException("Default category doesn't exist"));
	    for (Item item : category.getItems()) {// reassings the orphaned items to the default category
	        item.setCategory(defaultCategory);
	        itemDao.save(item);
	    }
	    categoryDao.delete(category);
	}
}


