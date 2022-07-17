package site.softleo.domains;

import java.util.Date;
import java.util.List;

public class Locacao {

	private Usuario usuario;
	private List<Filme> filmes;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;

	private String errors;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValorTotal(Double valor) {
		this.valor = valor;
	}
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {

		return String.format(
				"RECIBO:%n" +
						"Usuario: %1$s%n"
						+"Filme: %2$s%n"
						+"Data de locacao: %3$s%n"
						+"Data de retorno: %4$s%n"
						+"valor da locacao: %5$s%n",
				this.usuario, this.filmes, this.dataLocacao, this.dataRetorno, this.valor
		);
	}
}