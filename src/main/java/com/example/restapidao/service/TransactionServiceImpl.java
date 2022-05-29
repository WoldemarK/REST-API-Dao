package com.example.restapidao.service;

import com.example.restapidao.dao.TransactionDAO;
import com.example.restapidao.model.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionDAO transactionDAO;
    private static final String[] blackListEmails = new String[]{"blacklist1@gmail.com", "blacklist2@gmail.com", "blacklist3@gmail.com", "blacklist4@gmail.com"};


    public TransactionServiceImpl(@Qualifier("transactionDAOJpaImpl") TransactionDAO theTransactionDao) {
        transactionDAO = theTransactionDao;
    }

    @Override
    @Transactional
    public List<Transaction> findAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    @Transactional
    public String screenTransactionById(int theId) {
        Transaction theTransaction = transactionDAO.findTransactionById(theId);
        String dateInString = theTransaction.getDate();
        LocalDate localDate = LocalDate.parse(dateInString);
        LocalDate today = LocalDate.now();

        long difference = DAYS.between(localDate, today);
        boolean isInBlackList = Arrays.asList(blackListEmails).contains(theTransaction.getEmail());

        if (isInBlackList && difference < 30) {
            return "REJECT";
        } else {
            return "ACCEPT";
        }

    }

    @Override
    @Transactional
    public Transaction findTransactionById(int theId) {
        return transactionDAO.findTransactionById(theId);
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction theTransaction) {
        return transactionDAO.saveTransaction(theTransaction);
    }

    @Override
    @Transactional
    public int deleteTransactionById(int theId) {
        transactionDAO.deleteTransactionById(theId);
        return theId;
    }
}
