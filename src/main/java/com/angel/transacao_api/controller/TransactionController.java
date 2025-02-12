package com.angel.transacao_api.controller;


import com.angel.transacao_api.business.TransactionService;
import com.angel.transacao_api.controller.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransactionController {
    //add, deletar


    @Autowired
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDTO dto){
        transactionService.createTransaction(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions(){
        transactionService.deleteAllTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
