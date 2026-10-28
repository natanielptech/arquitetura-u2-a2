package br.com.natanielpaiva.padroes.catalogo;

public record PatternInfo(
        String slug,
        String nome,
        String categoria,
        String resumo,
        String problema,
        String solucao,
        String consequencias,
        String smell,
        String solid,
        String exemplo,
        String teste
) {
}
