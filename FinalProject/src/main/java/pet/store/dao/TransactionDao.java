package pet.store.dao;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

import sisims.entity.Transaction;

public interface TransactionDao extends JpaAttributeConverter<Transaction, Long> {

}
