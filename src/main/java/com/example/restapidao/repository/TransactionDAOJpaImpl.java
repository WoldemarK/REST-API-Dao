package com.example.restapidao.repository;

import com.example.restapidao.dao.TransactionDAO;
import com.example.restapidao.model.Transaction;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class TransactionDAOJpaImpl implements TransactionDAO {

    private final EntityManager entityManager;


    @Override
    public List<Transaction> getAllTransactions() {
        Query theQuery = (Query) entityManager.createQuery("from Transaction");
        List<Transaction> transactions = theQuery.getResultList();

        return transactions;
    }

    @Override
    public Transaction findTransactionById(int theId) {
        Transaction theTransaction = entityManager.find(Transaction.class, theId);

        return theTransaction;
    }

    @Override
    public Transaction saveTransaction(Transaction theTransaction) {
        Transaction dbTransaction = entityManager.merge(theTransaction);
        theTransaction.setId(dbTransaction.getId());
        return theTransaction;
    }


    @Override
    public void deleteTransactionById(int theId) {
        Query theQuery = (Query) entityManager.createQuery("delete from Transaction where id=:transactionId");
        theQuery.setParameter("transactionId", theId);
        theQuery.executeUpdate();
    }
}
