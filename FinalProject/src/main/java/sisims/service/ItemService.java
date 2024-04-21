package sisims.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sisims.controller.model.SisimsData.ItemData;
import sisims.dao.CategoryDao;
import sisims.dao.ItemDao;
import sisims.entity.Category;
import sisims.entity.Item;



@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    
    //create a new Item and assign it to the default category if it doesnt havea category Id
    public ItemData createNewItem(ItemData itemData) {
        Item item = new Item();
        Category category;

        if (itemData.getCategoryId() != null) {
            category = categoryDao.findById(itemData.getCategoryId())
                    .orElseGet(() -> getOrCreateUnspecifiedCategory());
        } else {
            category = getOrCreateUnspecifiedCategory();
        }

        copyItemFields(item, itemData, category);
        item = itemDao.save(item);
        return convertToItemData(item);
    }
    
  //creates the unspecified category
    private Category getOrCreateUnspecifiedCategory() {
        return categoryDao.findByCategoryName("Unspecified").orElseGet(() -> createUnspecifiedCategory());
    }
    

    private Category createUnspecifiedCategory() {
        Category category = new Category();
        category.setCategoryName("Unspecified");
        category.setCategoryDescription("Default category for unassigned or removed category items.");
        return categoryDao.save(category);
    }

    @Transactional(readOnly = true)
    public ItemData getItemWithId(Long itemId) {
        Item item = itemDao.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item with ID: " + itemId + " not found"));
        return convertToItemData(item);
    }

    @Transactional(readOnly = false)
    
    //update the item, if that includes an invalid or null category Id the item is assigned the default category
    public ItemData updateItemWithId(Long itemId, ItemData itemData) {
        Item item = itemDao.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item not found with ID: " + itemId));
        Category category;
        if (itemData.getCategoryId() != null) {
            category = categoryDao.findById(itemData.getCategoryId()).orElseGet(() -> getOrCreateUnspecifiedCategory());
        } else if (itemData.getCategoryName() != null && !itemData.getCategoryName().isEmpty()) {
            category = categoryDao.findByCategoryName(itemData.getCategoryName()).orElseGet(() -> getOrCreateUnspecifiedCategory());
        } else {
            category = getOrCreateUnspecifiedCategory();
        }
        
        copyItemFields(item, itemData, category);
        item = itemDao.save(item);
        return convertToItemData(item);
    }

    @Transactional
    public void deleteItemWithId(Long itemId) {
        itemDao.deleteById(itemId);
    }

    private void copyItemFields(Item item, ItemData itemData, Category category) {
        item.setItemQuantity(itemData.getItemQuantity());
        item.setItemPrice(itemData.getItemPrice());
        item.setItemIsFastSeller(itemData.getItemIsFastSeller());
        item.setItemShelfLimit(itemData.getItemShelfLimit());
        item.setItemName(itemData.getItemName());
        item.setItemIsDiscontinued(itemData.getItemIsDiscontinued());
        item.setCategory(category);
    }
    //convert
    private ItemData convertToItemData(Item item) {
        ItemData itemData = new ItemData();
        itemData.setItemId(item.getItemId());
        itemData.setItemQuantity(item.getItemQuantity());
        itemData.setItemPrice(item.getItemPrice());
        itemData.setItemIsFastSeller(item.getItemIsFastSeller());
        itemData.setItemShelfLimit(item.getItemShelfLimit());
        itemData.setItemName(item.getItemName());
        itemData.setItemIsDiscontinued(item.getItemIsDiscontinued());
        itemData.setCategoryName(item.getCategory().getCategoryName());
        return itemData;
    }
}
