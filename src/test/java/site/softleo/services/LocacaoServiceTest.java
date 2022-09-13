package site.softleo.services;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;
import site.softleo.domains.Usuario;
import site.softleo.exceptions.LocacaoException;
import site.softleo.matchers.DiaSemanaMatcher;
import site.softleo.matchers.MatchersProprios;
import site.softleo.utils.DataUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

//Caso utilize, tenho que colocar em ordem alfabética
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    public ExpectedException expectedException = ExpectedException.none();

    private LocacaoService locacaoService;
    private List<Filme> filmes;


    @Before
    public void antesDe() {
        locacaoService = new LocacaoService();
        filmes = new ArrayList<>();
    }

    @Test
    public void shouldNotDevolverFilmeDomingo() throws LocacaoException {
        //Cenario
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 4.0));

        //Acao
        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Verificacao
        boolean isSunday = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.SUNDAY);
        assertFalse(isSunday);
        assertThat(locacao.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), MatchersProprios.caiNumaTerca());

    }

    @Test
    public void shouldAlugarFilme() throws LocacaoException {

        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 5.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //como ser ler verifique que o valor da locacao e 5.0
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        assertTrue(DataUtils.isDifferenceDate(locacao.getDataLocacao(), new Date()));
        assertTrue(DataUtils.isDifferenceDate(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
//        assertThat(is(CoreMatchers.equalTo(locacao.getValor())), is(5.0));

    }

    @Test(expected = Exception.class)
    public void shouldExpectedExceptionFilmeSemEstoque() throws LocacaoException {

        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 0, 5.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

    }

    @Test
    public void shouldBlockCodeFilmeSemEstoque() {

        //A melhor solucao é essa
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 0, 5.0));

        try {
            Locacao locacao = locacaoService.alugarFilme(user, filmes);
            fail("Deveria ter lancado excecao");
        } catch (Exception e) {
            assertThat(e.getMessage(), is(containsString("está sem estoque")));
        }

    }

}
