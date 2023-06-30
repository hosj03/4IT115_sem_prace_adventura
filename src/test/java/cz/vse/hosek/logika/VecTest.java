package cz.vse.hosek.logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování
 * třídy Vec
 *
 * @author Jakub Hošek
 * @version  25. 5. 2020
 */
public class VecTest {
    private Vec postelM, postelS, skrinK, zrcadlo, truhla, notebookT, memeU, memeS;

    @Before
    public void setUp() {
        postelM = new Vec("postel", "velká a prostorná postel", false, false);
        postelS = new Vec("postel", "velká a prostorná postel", false, false);
        skrinK = new Vec("skříň", "skříň v pokoji Kvájeta", false, true);
        zrcadlo = new Vec("zrcadlo", "zrcadlo, které předpovídá konec budoucí hry", false, false);
        truhla = new Vec("truhla", "zamčená truhla", false, true);
        notebookT = new Vec("notebook", "notebook Tireneali", true, false);
        memeU = new Vec("memeU", "Urdontův meme", true, false);
        memeS = new Vec("memeS","Securityho meme", true, false);
    }

    @Test
    public void testSetOtevrena() {
        truhla.setOtevrena(true);
        assertTrue(truhla.isOtevrena());
        skrinK.setOtevrena(true);
        assertTrue(skrinK.isOtevrena());
    }

    @Test
    public void testSetProhledana() {
        postelM.setProhledana(true);
        assertTrue(postelM.isProhledana());
        postelS.setProhledana(true);
        assertTrue(postelS.isProhledana());
    }

    @Test
    public void testSetCesta() {
        String cestaU = "/obrazky/pawel_hacky.jpg";
        String cestaS = "/obrazky/jarvan_dragons.jpg";
        memeU.setCesta(cestaU);
        memeS.setCesta(cestaS);
        assertEquals("/obrazky/pawel_hacky.jpg",  memeU.getCesta());
        assertEquals("/obrazky/pawel_hacky.jpg",  memeU.getCesta());
    }

    @Test
    public void testOdstranVsechnyVeci() {
        assertEquals("", skrinK.vypisStringVeci());
        skrinK.pridejVec(zrcadlo);
        assertEquals("zrcadlo", skrinK.vypisStringVeci());
        skrinK.pridejVec(notebookT);
        assertEquals("zrcadlo, notebook", skrinK.vypisStringVeci());
        skrinK.odstranVsechnyVeci();
        assertEquals("", skrinK.vypisStringVeci());
    }
}
