package com.angel.transacao_api.business;

import com.angel.transacao_api.business.exceptions.UnprocessableEntity;
import com.angel.transacao_api.controller.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionDTO> listTransactions = new ArrayList<>();

    public void createTransaction(TransactionDTO dto) {


        log.info("Iniciado o processamento de gravar transações " + dto);
        if (dto.dateTime().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("A transação NÃO DEVE acontecer no futuro");
        }

        if (dto.value() < 0 ){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("A transação DEVE ter valor igual ou maior que 0");
        }

        log.info("Transacoes adicionadas com sucesso");
        listTransactions.add(dto);
    }

    public void deleteAllTransactions(){
        log.info("Iniciado processamento para deletar transações");
        listTransactions.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransactionDTO> getTransactionsStatistics(Integer timeInterval) {
        log.info("Inicadas buscas de transações por tempo " + timeInterval);
        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(timeInterval);

        log.info("Retorno de transações com sucesso");
        return listTransactions.stream().filter(transaction -> transaction.dateTime().isAfter(interval)).toList();
    }
}
