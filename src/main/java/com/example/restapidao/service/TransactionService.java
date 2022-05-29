package com.example.restapidao.service;

import com.example.restapidao.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransactions();

    String screenTransactionById(int theId);

    Transaction findTransactionById(int theId);

    Transaction saveTransaction(Transaction theTransaction);

    int deleteTransactionById(int theId);
}
