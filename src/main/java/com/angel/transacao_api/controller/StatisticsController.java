package com.angel.transacao_api.controller;


import com.angel.transacao_api.business.StatisticsService;
import com.angel.transacao_api.controller.dtos.StatisticsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsável por calcular estatisticas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StatisticsDTO> statisticsCalculate ( @RequestParam(value = "timeInterval", defaultValue = "60", required = false ) Integer timeInterval) {


        return ResponseEntity.ok(statisticsService.statisticsCalculate(timeInterval));

    }

}
