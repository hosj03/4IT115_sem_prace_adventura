package cz.vse.hosek.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("můj pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("prozkoumej postel");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertEquals("batoh: memeM", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("jdi chodba");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi pokoj Tireneali");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("pokoj Tireneali", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujePostavu("Tireneali"));
        assertEquals("batoh: memeM", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("prozkoumej Tireneali");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("batoh: memeM, memeT", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("jdi pokoj Urdonta");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("pokoj Urdonta", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujePostavu("Urdont"));

        hra1.zpracujPrikaz("prozkoumej Urdont");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("batoh: memeM, klíč, memeT", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("otevři skříň");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());

        hra1.zpracujPrikaz("seber memeU");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("batoh: memeM, klíč, memeU, memeT", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("jdi chodba");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());

        hra1.zpracujPrikaz("jdi pokoj Kvájeta");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujePostavu("Kvájet"));

        hra1.zpracujPrikaz("prozkoumej truhla");
        assertFalse(hra1.getHerniPlan().isVyhrano());
        assertFalse(hra1.konecHry());
        assertEquals("batoh: memeM, memeK, memeU, memeT", hra1.getHerniPlan().getBatoh().vypisBatoh());

        hra1.zpracujPrikaz("promluv s Kvájet");
        assertFalse(hra1.konecHry());
        assertTrue(hra1.getHerniPlan().isVyhrano());


        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());
    }
}
