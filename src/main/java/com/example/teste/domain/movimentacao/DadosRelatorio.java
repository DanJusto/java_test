package com.example.teste.domain.movimentacao;

import com.example.teste.domain.container.Categoria;
import com.example.teste.domain.container.Status;
import com.example.teste.domain.container.Tipo;

import java.time.LocalDateTime;

public record DadosRelatorio(String cliente, TipoMovimentacao tipo_movimentacao, Long id, String numero_container, Tipo tipo, Status status, Categoria categoria, LocalDateTime data_inicio, LocalDateTime data_fim) {
    public DadosRelatorio(Movimentacao movimentacao) {
        this(movimentacao.getContainer().getCliente(),
                movimentacao.getTipo_movimentacao(),
                movimentacao.getId(),
                movimentacao.getContainer().getNumero_container(),
                movimentacao.getContainer().getTipo(),
                movimentacao.getContainer().getStatus(),
                movimentacao.getContainer().getCategoria(),
                movimentacao.getData_inicio(),
                movimentacao.getData_fim());
    }
}
