package cz.vse.hosek.logika;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Jakub Hošek
 */
public class PrikazSeberTest {
    private Hra hra1;

    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    @Test
    public void testNeprenositelneVeci() {
        assertEquals("Tuto věc není možné přenést.", hra1.zpracujPrikaz("seber postel"));
        assertEquals("Tuto věc není možné přenést.", hra1.zpracujPrikaz("seber skříň"));
    }
}
