package sisims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sisims.entity.TransactionItems;

public interface TransactionItemsDao extends JpaRepository<TransactionItems, TransactionItems.TransactionItemId> {
	//Custom
	//This pain was the only way I found to persist data in the Transaction Items table when an item is deleted, but after a transaction is removed, then
	//The entry is removed
	//very similar to EmployeeDao's delete problem
	@Query("SELECT ti FROM TransactionItems ti WHERE ti.id.transactionId = :transactionId")
	List<TransactionItems> findByTransactionId(Long transactionId);

	 List<TransactionItems> findByItem_ItemId(Long itemId);
}
