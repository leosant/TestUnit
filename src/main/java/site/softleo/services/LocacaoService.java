package site.softleo.services;

import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;
import site.softleo.domains.Usuario;
import site.softleo.enums.PercentualDesconto;
import site.softleo.exceptions.LocacaoException;
import site.softleo.utils.DataUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws LocacaoException {

		List<String> erros = new ArrayList<>();

		for (Filme filme : filmes) {

			if (filme.getEstoque() == 0)
			{
				erros.add(format("Filme %1$s está sem estoque", filme.getNome()));
			}

		}

		if (!erros.isEmpty()) {
			throw new LocacaoException(erros.toString());
		}

		double precoTotal = descontosFilmes(filmes);

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValorTotal(precoTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 0);
		if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		}

		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...
		
		return locacao;
	}

	public Double descontosFilmes(List<Filme> filmes) {

		double valorFilmeComDesconto = 0.0;

		for (int i = 0; filmes.size() > i; i++) {

			BigDecimal precoLoc = BigDecimal.valueOf(filmes.get(i).getPrecoLocacao());
			BigDecimal percentualDesconto = BigDecimal.valueOf(PercentualDesconto.calculaPercentualDesconto(i+1));

			valorFilmeComDesconto += precoLoc.subtract(precoLoc.multiply(percentualDesconto)).doubleValue();
		}

		return valorFilmeComDesconto;
	}
}