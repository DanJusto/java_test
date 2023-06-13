package com.example.teste.domain.movimentacao;

import java.time.LocalDateTime;

public record DadosDetalhesMovimentacao(Long id, Long container_id, TipoMovimentacao tipo_movimentacao, LocalDateTime data_inicio, LocalDateTime data_fim) {
    public DadosDetalhesMovimentacao(Movimentacao movimentacao) {
        this(movimentacao.getId(),
                movimentacao.getContainer().getId(),
                movimentacao.getTipo_movimentacao(),
                movimentacao.getData_inicio(),
                movimentacao.getData_fim());
    }
}
