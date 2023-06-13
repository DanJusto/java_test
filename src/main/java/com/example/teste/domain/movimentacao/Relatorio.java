package com.example.teste.domain.movimentacao;

import com.example.teste.domain.container.Categoria;
import com.example.teste.domain.container.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Relatorio {

    private Integer exportacoes;
    private Integer importacoes;
    @Autowired
    ContainerRepository containerRepository;
    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public ArrayList<Object> criarRelatório() {
        var lista = movimentacaoRepository.findAll();

        ArrayList<DadosRelatorio> relatorio = new ArrayList<>();

        for (Movimentacao movimentacao : lista) {
            relatorio.add(new DadosRelatorio(movimentacao));
        }

        Map<String, Map<TipoMovimentacao, List<DadosRelatorio>>> movimentacoes = relatorio.stream().collect(
                Collectors.groupingBy(DadosRelatorio::cliente,
                        Collectors.groupingBy(DadosRelatorio::tipo_movimentacao)));


        contagem();
        ArrayList<Object> relatorioMovimentacoes = new ArrayList<>();
        relatorioMovimentacoes.add(movimentacoes);
        relatorioMovimentacoes.add(toString());

        return relatorioMovimentacoes;
    }

    private void contagem() {
        var impo = containerRepository.findAllByCategoria(Categoria.IMPORTACAO);
        var expo = containerRepository.findAllByCategoria(Categoria.EXPORTACAO);

        this.exportacoes = expo.size();
        this.importacoes = impo.size();
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "exportacoes=" + exportacoes +
                ", importacoes=" + importacoes +
                '}';
    }
}
