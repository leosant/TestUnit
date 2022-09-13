package site.softleo.services;

import java.util.Arrays;

public class Divisivel {
    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FIVE = 5;
    public static final int NUMBER_SEVEN = 7;

    //Se o numero for divisivel por 3
        //buzz
    //Se o numero for divisivel por 5
        //fizz
    //Se o numero for divisivel por 7
        //fizzbuzz
    //Se o numero for divisivel por 3 e por 5
        //fizzbuzz
    public String validar(int num, int numDiv) {
        return validar(num, numDiv, null);
    }
    public String validar(Integer num, Integer numDiv1, Integer numDiv2) {

        if (numDiv2 != null) {

            if (isDivisivelPorDoisNumeros(num, numDiv1, numDiv2)
                    || (isNumPermitido(NUMBER_SEVEN, num) && isDivisivel(num, numDiv1))) {
                return "fizzBuzz";
            }
        }
        if (isNumPermitido(NUMBER_THREE, num) && isDivisivel(num, numDiv1)) {
            return "buzz";
        }
        if (isNumPermitido(NUMBER_FIVE, num) && isDivisivel(num, numDiv1)) {
            return "fizz";
        }
        return "N";
    }
    public static boolean isNumPermitido(int numberPermitido, int number) {
        return numberPermitido == number;

    }
    public boolean isDivisivelPorDoisNumeros(int num, Integer numDiv1, Integer numDiv2) {

        return isNumPermitido(numDiv1, num) && isNumPermitido(numDiv2, num)
                && num % numDiv1 == 0 && num % numDiv2 == 0;
    }

    public boolean isDivisivel(int num, int numDiv1) {
        return num % numDiv1 == 0;
    }
}
