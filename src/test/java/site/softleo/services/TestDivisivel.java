package site.softleo.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class TestDivisivel {

    @Parameterized.Parameter
    public int numMod1;

    @Parameterized.Parameter(value = 1)
    public int number;
    @Parameterized.Parameter(value = 2)
    public int numMod2;

    @Parameterized.Parameter(value = 3)
    public String result;

    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FIVE = 5;
    public static final int NUMBER_SEVEN = 7;

    @Parameterized.Parameters
    public static Collection<Object[]> getParamentros() {
        return Arrays.asList(new Object[][] {
                {3, NUMBER_THREE, 0, "buzz"},
                {5, NUMBER_FIVE, 0, "fizz"},
                {7, NUMBER_SEVEN, 0, "fizzBuzz"},
                {3, NUMBER_THREE, NUMBER_FIVE, "fizzBuzz"}
        });
    }

    @Test
    public void checkDivisivel() {
        Divisivel div = new Divisivel();
        Assert.assertEquals(result, div.validar(number, numMod1, numMod2));
    }
}
