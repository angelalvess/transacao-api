package com.angel.transacao_api.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionDTO(Double value, OffsetDateTime dateTime) {
}
