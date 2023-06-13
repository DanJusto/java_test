package com.example.teste.controller;

import com.example.teste.domain.ValidacaoException;
import com.example.teste.domain.container.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("container")
public class ContainerController {

    @Autowired
    private ContainerRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroContainer dados, UriComponentsBuilder uriBuilder) {
        if(repository.encontrarPeloNumeroContainer(dados.numero_container()) != null) {
            throw new ValidacaoException("Número do container já existe.");
        }
        var container = new Container(dados);
        repository.save(container);
        var uri = uriBuilder.path("/container/{id}").buildAndExpand(container.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesContainer(container));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar(@RequestBody @Valid DadosEdicaoContainer dados) {
        if (!repository.existsById(dados.id())) {
            throw new ValidacaoException("Id do container não existe.");
        }
        var container = repository.getReferenceById(dados.id());
        container.editarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhesContainer(container));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id do container não existe.");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id do container não existe.");
        }
        var container = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesContainer(container));
    }

}
