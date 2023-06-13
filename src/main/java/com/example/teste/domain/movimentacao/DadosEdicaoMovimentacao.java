package com.example.teste.domain.movimentacao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosEdicaoMovimentacao(
        @NotNull
        Long id,
        TipoMovimentacao tipo_movimentacao,
        LocalDateTime data_inicio,
        LocalDateTime data_fim
) {
}
