package com.example.teste.domain.container;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Container")
@Table(name = "container")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Container {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    private String numero_container;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Container(DadosCadastroContainer dados) {
        this.cliente = dados.cliente();
        this.numero_container = dados.numero_container();
        this.tipo = dados.tipo();
        this.status = dados.status();
        this.categoria = dados.categoria();
    }

    public void editarInformacoes(DadosEdicaoContainer dados) {
        if(dados.cliente() != null) {
            this.cliente = dados.cliente();
        }
        if(dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if(dados.status() != null) {
            this.status = dados.status();
        }
        if(dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
    }
}
