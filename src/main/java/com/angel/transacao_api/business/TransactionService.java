package com.angel.transacao_api.business;

import com.angel.transacao_api.business.exceptions.UnprocessableEntity;
import com.angel.transacao_api.controller.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final List<TransactionDTO> listTransactions = new ArrayList<>();

    public void createTransaction(TransactionDTO dto) {
        if (dto.dateTime().isAfter(OffsetDateTime.now())){
            throw new UnprocessableEntity("A transação NÃO DEVE acontecer no futuro");
        }

        if (dto.value() < 0 ){
            throw new UnprocessableEntity("A transação DEVE ter valor igual ou maior que 0");
        }

        listTransactions.add(dto);
    }

    public void deleteAllTransactions(){
        listTransactions.clear();
    }

    public List<TransactionDTO> getTransactionsStatistics(Integer timeInterval) {

        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(timeInterval);

        return listTransactions.stream().filter(transaction -> transaction.dateTime().isAfter(interval)).toList();
    }
}
