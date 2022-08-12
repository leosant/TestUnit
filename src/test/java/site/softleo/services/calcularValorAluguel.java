package site.softleo.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;
import site.softleo.domains.Usuario;
import site.softleo.exceptions.LocacaoException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class calcularValorAluguel {

    private LocacaoService locacaoService;

    @Parameterized.Parameter
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;

    @Parameterized.Parameter(value = 2)
    public String describe;

    @Before
    public void antesDe() {
        locacaoService = new LocacaoService();
    }

    public static final Filme filme1 = new Filme(" Filme 1", 2, 4.0);
    public static final Filme filme2 = new Filme(" Filme 2", 3, 4.0);
    public static final Filme filme3 = new Filme(" Filme 3", 4, 4.0);
    public static final Filme filme4 = new Filme(" Filme 4", 1, 4.0);
    public static final Filme filme5 = new Filme(" Filme 5", 8, 4.0);
    public static final Filme filme6 = new Filme(" Filme 6", 5, 4.0);
    public static final Filme filme7 = new Filme(" Filme 7", 5, 4.0);

    @Parameterized.Parameters(name = "Teste {index} = {2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList(filme1, filme2), 8.0, "Sem desconto"},
                {Arrays.asList(filme1, filme2, filme3), 11.0, "Desconto de 25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "Desconto de 50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "Desconto de 75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "Desconto de 100%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "Sem Desconto no Setimo filme"}
        });
    }


    @Test
    public void shouldCalcDescontoFilmes() throws LocacaoException {
        //Cenario
        Usuario user = new Usuario("Usuario 1");

        //Acao
        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Verificacao
        Assert.assertEquals(valorLocacao, locacao.getValor(), 0.001);
    }
}
