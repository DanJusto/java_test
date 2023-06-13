package com.example.teste.domain.container;

public record DadosDetalhesContainer(Long id, String cliente, String n_container, Tipo tipo, Status status, Categoria categoria) {
    public DadosDetalhesContainer(Container container) {
        this(container.getId(), container.getCliente(), container.getNumero_container(), container.getTipo(), container.getStatus(), container.getCategoria());
    }
}
