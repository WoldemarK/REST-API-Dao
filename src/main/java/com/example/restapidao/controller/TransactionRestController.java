package com.example.restapidao.controller;

import com.example.restapidao.model.Transaction;
import com.example.restapidao.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="/demo")
public class TransactionRestController {

    private TransactionService transactionService;

    @GetMapping( "/transactions")
    public ResponseEntity<List<Transaction>> findAll(){
        System.out.println(transactionService.findAllTransactions().size());
        return new ResponseEntity<List<Transaction>>(transactionService.findAllTransactions(), HttpStatus.OK);

    }


    @GetMapping("/transactions/{transactionId}")
    public String screenTransaction(@PathVariable int transactionId)  {
        String theTransaction = transactionService.screenTransactionById(transactionId);
        return theTransaction;
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction theTransaction){
        return (transactionService.saveTransaction(theTransaction));
    }

    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction theTransaction){
        Transaction transaction = transactionService.findTransactionById(theTransaction.getId());
        if (transaction == null) {
            throw new RuntimeException("Transaction to update doesn't exist");
        }
        return (transactionService.saveTransaction(theTransaction));
    }

    @DeleteMapping("/transactions/{transactionId}")
    public String deleteTransaction(@PathVariable int transactionId){
        Transaction tempTransaction = transactionService.findTransactionById(transactionId);
        if(tempTransaction == null){
            throw new RuntimeException("Transaction Id not found");
        }
        transactionService.deleteTransactionById(transactionId);
        return "deleted transaction id " + transactionId;

    }

}
