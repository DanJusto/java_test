package com.example.teste.controller;

import com.example.teste.domain.ValidacaoException;
import com.example.teste.domain.container.*;
import com.example.teste.domain.movimentacao.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private Relatorio relatorio;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMovimentacao dados, UriComponentsBuilder uriBuilder) {
        if(!containerRepository.existsById(dados.container_id())) {
            throw new ValidacaoException("Id do container não existe.");
        }

        var container = containerRepository.getReferenceById(dados.container_id());
        var movimentacao = new Movimentacao(null, container, dados.tipo_movimentacao(), dados.data_inicio(), dados.data_fim());
        movimentacaoRepository.save(movimentacao);

        var uri = uriBuilder.path("/movimentacao/{id}").buildAndExpand(movimentacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesMovimentacao(movimentacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar(@RequestBody @Valid DadosEdicaoMovimentacao dados) {
        if (!movimentacaoRepository.existsById(dados.id())) {
            throw new ValidacaoException("Id da movimentacao não existe.");
        }
        var movimentacao = movimentacaoRepository.getReferenceById(dados.id());
        movimentacao.editarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhesMovimentacao(movimentacao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        if (!movimentacaoRepository.existsById(id)) {
            throw new ValidacaoException("Id do container não existe.");
        }
        movimentacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        if (!movimentacaoRepository.existsById(id)) {
            throw new ValidacaoException("Id do container não existe.");
        }
        var movimentacao = movimentacaoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesMovimentacao(movimentacao));
    }

    @GetMapping
    public ResponseEntity listagem() {
        var lista = relatorio.criarRelatório();
        return ResponseEntity.ok(lista);
    }
}
