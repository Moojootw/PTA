package pet.store.dao;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

import sisims.entity.Category;

public interface CategoryDao extends JpaAttributeConverter<Category, Long> {

}
