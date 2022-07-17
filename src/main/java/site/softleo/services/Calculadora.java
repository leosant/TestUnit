package site.softleo.services;

import site.softleo.exceptions.DivideErrorException;

public class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public double dividir(int a, int b) throws DivideErrorException {
        if (b == 0) {
            throw new DivideErrorException("Número não pode ser dividido por zero");
        }
        return a / b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }
}
