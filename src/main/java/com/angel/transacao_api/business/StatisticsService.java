package com.angel.transacao_api.business;

import com.angel.transacao_api.business.exceptions.UnprocessableEntity;
import com.angel.transacao_api.controller.dtos.StatisticsDTO;
import com.angel.transacao_api.controller.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    @Autowired
    private final TransactionService transactionService;


    public StatisticsDTO statisticsCalculate (Integer timeInterval) {
        log.info("Iniciada busca de estatísticas de transações pelo período de tempo " + timeInterval);

        List<TransactionDTO> transactions = transactionService.getTransactionsStatistics(timeInterval);

        if (transactions.isEmpty()){
            new StatisticsDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics transactionStatistics = transactions.stream().mapToDouble(TransactionDTO::value).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");
        return new StatisticsDTO(transactionStatistics.getCount(), transactionStatistics.getSum(), transactionStatistics.getAverage(), transactionStatistics.getMin(), transactionStatistics.getMax());

    }
}
