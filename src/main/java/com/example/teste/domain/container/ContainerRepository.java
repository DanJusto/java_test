package com.example.teste.domain.container;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContainerRepository extends JpaRepository<Container, Long> {

    @Query("""
            select c from Container c
            where c.numero_container = :numero
            """)
    Container encontrarPeloNumeroContainer(String numero);

    List<Container> findAllByCategoria(Categoria categoria);
}
