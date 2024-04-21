package sisims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sisims.entity.TransactionItems;

public interface TransactionItemsDao extends JpaRepository<TransactionItems, TransactionItems.TransactionItemId> {
	 @Query("SELECT ti FROM TransactionItems ti WHERE ti.id.transactionId = :transactionId")
	List<TransactionItems> findByTransactionId(Long transactionId);
}
