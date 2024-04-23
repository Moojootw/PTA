package sisims.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sisims.controller.model.SisimsData.TransactionItemsData;
import sisims.dao.TransactionItemsDao;
import sisims.entity.TransactionItems;


@Service
public class TransactionItemsService {
	
	//this table is majority of my development time for this project.

    @Autowired
    private TransactionItemsDao transactionItemsDao;

    public List<TransactionItemsData> getTransactionItemsByTransactionId(Long transactionId) {
        List<TransactionItems> transactionItemsList = transactionItemsDao.findByTransactionId(transactionId); //findByTransactionId is a custom Dao method using @Query
        return Collections.singletonList(new TransactionItemsData(transactionItemsList));
    }

    @Transactional
    //this is to remove a signle item from a transaction. instead of removing the entire transaction
    public void deleteTransactionItemsWithId(Long transactionId, Long itemId) {
        TransactionItems.TransactionItemId id = new TransactionItems.TransactionItemId(transactionId, itemId);
        if (!transactionItemsDao.existsById(id)) {
            throw new NoSuchElementException("Transaction item with ID:" + transactionId + " and Item ID:" + itemId + " does not exist");
        }
        transactionItemsDao.deleteById(id);
    }
}