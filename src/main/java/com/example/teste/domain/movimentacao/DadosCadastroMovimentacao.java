package com.example.teste.domain.movimentacao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroMovimentacao(
        @NotNull
        Long container_id,

        @NotNull
        TipoMovimentacao tipo_movimentacao,

        @NotNull
        LocalDateTime data_inicio,

        @NotNull
        LocalDateTime data_fim

) {
}
