package site.softleo.services;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.softleo.exceptions.DivideErrorException;

public class CalculadoraTest {

    private Calculadora calc;

    @Before
    public void beforeFrom() {
        calc = new Calculadora();
    }

    @Test
    public void somaDoisValores() {
        //Cenario
        int a = 10, b = 5;
        //Acao
        int resultado = calc.somar(a, b);
        //Verificacao
        Assert.assertEquals(15, resultado);
    }

    @Test
    public void subtraiDoisValores() {
        int a = 10, b = 5;
        int resultado = calc.subtrair(a, b);

        Assert.assertEquals(5, resultado);
    }

    @Test
    public void divideDoisValores() throws DivideErrorException {
        int a = 10, b = 5;
        double resultado = calc.dividir(a, b);

        Assert.assertEquals(2, resultado,0.01);
    }

    @Test(expected = DivideErrorException.class)
    public void divideComDivisorZero() throws DivideErrorException {
        int a = 10, b = 0;
        double resultado = calc.dividir(a, b);

    }

    @Test
    public void divideComDivisorZeroTrantando() {
        int a = 10, b = 0;
        try {
            double resultado = calc.dividir(a, b);
            Assert.fail("Deveria ter estourado exception");
        } catch (DivideErrorException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Número não pode ser dividido por zero"));
        }

    }

    @Test
    public void mutiplicaDoisValores() {
        int a = 10, b = 5;
        int resultado = calc.multiplicar(a, b);

        Assert.assertEquals(50, resultado);
    }
}
