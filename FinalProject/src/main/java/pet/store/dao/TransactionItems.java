package pet.store.dao;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface TransactionItems extends JpaAttributeConverter<TransactionItems, Long> {

}
