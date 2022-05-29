package com.example.restapidao.dao;

import com.example.restapidao.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> getAllTransactions();

    Transaction findTransactionById(int theId);

    Transaction saveTransaction(Transaction theTransaction);

    void deleteTransactionById(int theId);
}
