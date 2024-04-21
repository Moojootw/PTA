package sisims.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sisims.controller.model.SisimsData.TransactionData;
import sisims.dao.TransactionDao;
import sisims.entity.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Transactional(readOnly = true)
    public TransactionData getTransactionWithId(Long transactionId) {
        Transaction transaction = transactionDao.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction with ID:" + transactionId + " not found"));
        return new TransactionData(transaction);
    }

    @Transactional(readOnly = false)
    public TransactionData updateTransactionWithId(Long transactionId, TransactionData transactionData) {
        Transaction transaction = transactionDao.findById(transactionId).orElseThrow(() -> new NoSuchElementException("Transaction not found with ID: " + transactionId));

        transaction.setTransactionItemCount(transactionData.getTransactionItemCount());
        transaction.setTransactionTotal(transactionData.getTransactionTotal());
        transaction.setTransactionDate(transactionData.getTransactionDate());
        transaction.setTransactionDetails(transactionData.getTransactionDetails());
        return new TransactionData(transactionDao.save(transaction));
    }

    @Transactional(readOnly = false)
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionDao.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction with ID: " + transactionId + " not found"));
        transactionDao.delete(transaction);
    }
}
