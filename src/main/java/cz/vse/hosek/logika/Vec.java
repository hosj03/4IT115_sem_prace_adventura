package cz.vse.hosek.logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída Věc - popisuje věc ve hře.
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Věc" reprezentuje jednu věc ve hře.
 * Věc v sobě může obsahovat další věci.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class Vec implements cz.vse.hosek.logika.INazev {

    private final String nazev;
    private final String popis;
    private final boolean prenositelna;
    private final boolean oteviratelna;
    private boolean otevrena;
    private boolean uzamcena;
    private boolean prohledana;
    private Map<String, Vec> veci;
    private String cesta;

    /**
     * Konstruktor instance věci s příslušnými parametry.
     *  @param nazev název věci
     * @param popis popis věci
     * @param prenositelna boolean zda je věc přenositelná
     * @param otevritelna boolean zda je věc otevíratelná
     */
    public Vec(String nazev, String popis, boolean prenositelna, boolean otevritelna) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.oteviratelna = otevritelna;
        uzamcena = false;
        otevrena = false;
        veci = new HashMap<String, Vec>();
        prohledana = false;
        cesta = null;
    }

    /**
     * Vrací název věci (byl zadán při vytváření věci jako parametr
     * konstruktoru)
     *
     * @return název věci
     */
    @Override
    public String getNazev() {
        return nazev;
    }

    /**
     * Getters
     *
     * @return vrátí hodnotu požadované proměmné
     */
    public String getPopis() {
        return popis;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public boolean isOtevrena() {
        return otevrena;
    }

    public boolean isOteviratelna() {
        return oteviratelna;
    }

    public boolean isUzamcena() {
        return uzamcena;
    }

    public Map<String, Vec> getVeci() {
        return veci;
    }

    public boolean isProhledana() {
        return prohledana;
    }

    public String getCesta() {
        return cesta;
    }

    /**
     * Setter
     *
     * @param otevrena požadované nastavení proměnné otevrena
     */
    public void setOtevrena(boolean otevrena) {
        this.otevrena = otevrena;
    }

    /**
     * Setter
     *
     * @param prohledana požadované nastavení proměnné prohledana
     */
    public void setProhledana(boolean prohledana) {
        this.prohledana = prohledana;
    }

    /**
     * Setter
     *
     * @param cesta požadované nastavení proměnné cesta
     */
    public void setCesta(String cesta) {
        this.cesta = cesta;
    }

    /**
     * Setter
     *
     * @param uzamcena požadované nastavení proměnné uzamcena
     */
    public void setUzamcena(boolean uzamcena) {
        this.uzamcena = uzamcena;
    }

    /**
     * Metoda přidávající věc do věci
     *
     * @param vec instance přidávané věci
     */
    public void pridejVec (Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    public void odstranVsechnyVeci () {
        veci.clear();
    }

    public String vypisStringVeci () {
        return cz.vse.hosek.logika.Util.vypisStringNazvu(veci.values());
    }

}
