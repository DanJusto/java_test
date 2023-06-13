package com.example.teste.domain.container;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroContainer(

        @NotBlank
        String cliente,
        @NotBlank
        @Pattern(regexp = "^[A-Z]{4}[0-9]{7}$")
        String numero_container,
        @NotNull
        Tipo tipo,
        @NotNull
        Status status,
        @NotNull
        Categoria categoria
) {
}
