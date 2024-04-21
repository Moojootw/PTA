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

    @Autowired
    private TransactionItemsDao transactionItemsDao;
    //this gets a list of item Ids in a transaction 

    public List<TransactionItemsData> getTransactionItemsByTransactionId(Long transactionId) {
        List<TransactionItems> transactionItemsList = transactionItemsDao.findByTransactionId(transactionId);
        if (transactionItemsList.isEmpty()) {
            throw new NoSuchElementException("No items found for transaction with ID: " + transactionId);
        }
        return Collections.singletonList(new TransactionItemsData(transactionItemsList));
    }

    @Transactional
    //deletes a transaction
    public void deleteTransactionItemsWithId(Long transactionId, Long itemId) {
        TransactionItems.TransactionItemId id = new TransactionItems.TransactionItemId(transactionId, itemId);
        if (!transactionItemsDao.existsById(id)) {
            throw new NoSuchElementException("Transaction item with ID:" + transactionId + " and Item ID:" + itemId + " does not exist");
        }
        transactionItemsDao.deleteById(id);
    }
    }