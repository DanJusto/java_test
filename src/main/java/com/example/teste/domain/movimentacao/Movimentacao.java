package com.example.teste.domain.movimentacao;

import com.example.teste.domain.container.Container;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Movimentacao")
@Table(name = "movimentacao")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id")
    private Container container;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo_movimentacao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;

    public void editarInformacoes(DadosEdicaoMovimentacao dados) {
        if(dados.tipo_movimentacao() != null) {
            this.tipo_movimentacao = dados.tipo_movimentacao();
        }
        if(dados.data_inicio() != null) {
            this.data_inicio = dados.data_inicio();
        }
        if(dados.data_fim() != null) {
            this.data_fim = dados.data_fim();
        }
    }
}
