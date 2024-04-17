package pet.store.dao;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

import sisims.entity.Item;

public interface ItemDao extends JpaAttributeConverter<Item, Long> {

}
