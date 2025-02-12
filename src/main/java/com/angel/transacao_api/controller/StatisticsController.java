package com.angel.transacao_api.controller;


import com.angel.transacao_api.business.StatisticsService;
import com.angel.transacao_api.controller.dtos.StatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class StatisticsController {


    private final StatisticsService statisticsService;


    @GetMapping
    public ResponseEntity<StatisticsDTO> statisticsCalculate (@RequestParam(defaultValue = 60, required = false, value = "timeInterval") Integer timeInterval) {

//        statisticsService.statisticsCalculate(timeInterval);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsCalculate(timeInterval));


    }


}
