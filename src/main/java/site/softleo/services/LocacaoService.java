package site.softleo.services;

import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;
import site.softleo.domains.Usuario;
import site.softleo.enums.PercentualDesconto;
import site.softleo.utils.DataUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {

		List<String> erros = new ArrayList<>();

		for (Filme filme : filmes) {

			if (filme.getEstoque() == 0)
			{
				erros.add(format("Filme %1$s está sem estoque", filme.getNome()));
			}

		}

		if (!erros.isEmpty()) {
			throw new Exception(erros.toString());
		}

		Double totalPreco = filmes.stream()
				.mapToDouble(Filme::getPrecoLocacao)
				.sum();

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValorTotal(totalPreco);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...
		
		return locacao;
	}

	//TODO: refatorar pensando no SOLID
	public void descontoConjuntosFilmes(Locacao locacao) {

		BigDecimal valorUltimoFilmeDesconto;

		for (int i = 0; locacao.getFilmes().size() > i; i++) {

			double precoLoc = locacao.getFilmes().get(i).getPrecoLocacao();
			valorUltimoFilmeDesconto = BigDecimal.valueOf(precoLoc - (precoLoc * PercentualDesconto.calculaPercentualDesconto(i+1)));
			locacao.setValorTotal((locacao.getValor() - precoLoc)+valorUltimoFilmeDesconto.doubleValue());

		}
	}
}