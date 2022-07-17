package site.softleo.utils;

import site.softleo.domains.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssertTest {

    @Test
    public void test() {
        assertTrue(true);
        assertFalse(false);
        assertEquals( 3.14, Math.PI, 0.01);

        int i = 5;
        Integer i2 = 5;
        assertEquals(i, i2.intValue());
        assertEquals(Integer.valueOf(i), i2);

        assertTrue("bola".equalsIgnoreCase("Bola"));
        assertTrue("bola".startsWith("bo"));

        Usuario user1 = new Usuario("Usuario 1");
        Usuario user2 = new Usuario("Usuario 1");
        Usuario user3 = user2;
        Usuario user4 = null;

        assertEquals(user1, user2);

        //Verificar se os objetos são da mesma instância
        assertSame(user3, user2);

        assertNull(user4);
        assertNotNull(user3);

        assertNotEquals("Strings são iguais", "Casa2", "Casa");
    }
}
