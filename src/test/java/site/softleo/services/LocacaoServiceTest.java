package site.softleo.services;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;
import site.softleo.domains.Usuario;
import site.softleo.utils.DataUtils;

import java.util.ArrayList;
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

    //Caso o usuário queira compra até tres filmes deve ocorrer um desconto de 25%
    @Test
    public void shouldDescontoTresFilmes() throws Exception {
        //Cenario
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 4.0));
        filmes.add(new Filme(" Filme 2", 3, 4.0));
        filmes.add(new Filme(" Filme 3", 4, 4.0));
        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Acao
        locacaoService.descontoConjuntosFilmes(locacao);

        //Verificacao
        Assert.assertEquals(11.0, locacao.getValor(), 0.001);
    }

    //Caso o usuário queira compra até quatro filmes deve ocorrer um desconto de 50%
    @Test
    public void shouldDescontoQuatroFilmes() throws Exception {
        //Cenario
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 4.0));
        filmes.add(new Filme(" Filme 2", 3, 4.0));
        filmes.add(new Filme(" Filme 3", 4, 4.0));
        filmes.add(new Filme(" Filme 4", 1, 4.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Acao
        locacaoService.descontoConjuntosFilmes(locacao);

        //Verificacao
        Assert.assertEquals(13.0, locacao.getValor(), 0.001);
    }

    //Caso o usuário queira compra até cinco filmes deve ocorrer um desconto de 75%
    @Test
    public void shouldDescontoCincoFilmes() throws Exception {
        //Cenario
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 4.0));
        filmes.add(new Filme(" Filme 2", 3, 4.0));
        filmes.add(new Filme(" Filme 3", 4, 4.0));
        filmes.add(new Filme(" Filme 4", 1, 4.0));
        filmes.add(new Filme(" Filme 5", 8, 4.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Acao
        locacaoService.descontoConjuntosFilmes(locacao);

        //Verificacao
        Assert.assertEquals(14, locacao.getValor(), 0.001);
    }
    //Caso o usuário queira compra até seis filmes deve ocorrer um desconto de 100%
    @Test
    public void shouldDescontoSeisFilmes() throws Exception {
        //Cenario
        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 2, 4.0));
        filmes.add(new Filme(" Filme 2", 3, 4.0));
        filmes.add(new Filme(" Filme 3", 4, 4.0));
        filmes.add(new Filme(" Filme 4", 1, 4.0));
        filmes.add(new Filme(" Filme 5", 8, 4.0));
        filmes.add(new Filme(" Filme 6", 5, 4.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

        //Acao
        locacaoService.descontoConjuntosFilmes(locacao);

        //Verificacao
        Assert.assertEquals(14.0, locacao.getValor(), 0.001);
    }


    @Test
    public void shouldAlugarFilme() throws Exception {

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
    public void shouldExpectedExceptionFilmeSemEstoque() throws Exception {

        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 0, 5.0));

        Locacao locacao = locacaoService.alugarFilme(user, filmes);

    }

    @Test
    public void shouldBlockCodeFilmeSemEstoque() {

        //A melhor é essa

        Usuario user = new Usuario("Usuario 1");
        filmes.add(new Filme(" Filme 1", 0, 5.0));

        try {
            Locacao locacao = locacaoService.alugarFilme(user, filmes);
            fail("Deveria ter lancado excecao");
        } catch (Exception e) {
            assertThat(e.getMessage(), is(containsString("está sem estoque")));
        }

    }

//    @Test
//    public void testeLocacao_filmeEstoque3() throws Exception {
//
//        LocacaoService ls = new LocacaoService();
//
//        Usuario user = new Usuario("Usuario 1");
//        Filme filme = new Filme(" Filme 1", 0, 5.0);
//
//        expectedException.expect(Exception.class);
//        expectedException.expectMessage("Filme sem estoque");
////        expectedException.expectMessage("Filme sem estoque");
//
//        if (expectedException.isAnyExceptionExpected()) {
//            Locacao locacao = ls.alugarFilme(user, filme);
//            assertTrue(true);
//        }
//
//    }
}
