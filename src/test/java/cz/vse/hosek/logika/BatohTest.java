package cz.vse.hosek.logika;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování
 * třídy Batoh
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class BatohTest {
    private Hra hra;
    Vec memeU;
    Vec memeS;
    Vec memeT;
    Vec memeK;
    Vec memeM;

    @Before
    public void setUp() {
        hra = new Hra();
        memeU = new Vec("memeU", "Urdontův meme", true, false);
        memeS = new Vec("memeS","Securityho meme", true, false);
        memeT = new Vec("memeT", "meme Tireneali", true, false);
        memeK = new Vec("memeK", "Kvájetův meme", true, false);
        memeM = new Vec("memeM", "tvůj meme", true, false);
    }

    @Test
    public void testBatoh() {
        assertEquals("batoh: ", hra.getHerniPlan().getBatoh().vypisBatoh());

        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeS, hra.getHerniPlan());
        assertEquals("batoh: memeS", hra.getHerniPlan().getBatoh().vypisBatoh());

        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeU, hra.getHerniPlan());
        assertEquals("batoh: memeU, memeS", hra.getHerniPlan().getBatoh().vypisBatoh());

        hra.getHerniPlan().getBatoh().odeberZBatohu(memeU.getNazev());
        assertEquals("batoh: memeS", hra.getHerniPlan().getBatoh().vypisBatoh());

        hra.getHerniPlan().getBatoh().odeberZBatohu(memeS.getNazev());
        assertEquals("batoh: ", hra.getHerniPlan().getBatoh().vypisBatoh());

    }

    @Test
    public void testPridejDoBatohu() {
        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeS, hra.getHerniPlan());
        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeU, hra.getHerniPlan());
        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeT, hra.getHerniPlan());
        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeK, hra.getHerniPlan());
        assertEquals("batoh: memeK, memeU, memeT, memeS", hra.getHerniPlan().getBatoh().vypisBatoh());
        hra.getHerniPlan().getBatoh().pridejDoBatohu(memeU, hra.getHerniPlan());
        assertEquals("batoh: memeK, memeU, memeT, memeS", hra.getHerniPlan().getBatoh().vypisBatoh());
    }
}
