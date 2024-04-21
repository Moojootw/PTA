package sisims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sisims.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long> {

}
