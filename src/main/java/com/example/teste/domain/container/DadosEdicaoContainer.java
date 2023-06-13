package com.example.teste.domain.container;

import jakarta.validation.constraints.NotNull;

public record DadosEdicaoContainer(
        @NotNull
        Long id,
        String cliente,
        Tipo tipo,
        Status status,
        Categoria categoria
) {
}
