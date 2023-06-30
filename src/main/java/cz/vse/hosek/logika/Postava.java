package cz.vse.hosek.logika;

/**
 * Třída Postava - třída popisující postavu.
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Postava" reprezentuje jednu postavu ve hře.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class Postava {
    private final String jmeno;
    private boolean mrtvy;
    private boolean promluveno;
    private int promluvenoCount;
    private boolean prozkoumano;

    /**
     * Vytvoření postavy se zadaným jménem a zda žije.
     *
     * @param jmeno jméno postavy
     * @param mrtvy boolean, zda je postava mrtvá
     */
    public Postava(String jmeno, boolean mrtvy) {
        this.jmeno = jmeno;
        this.mrtvy = mrtvy;
        promluveno = false;
        prozkoumano = false;
        promluvenoCount = 0;
    }

    /**
     * Getters
     *
     * @return vrátí hodnotu požadované proměmné
     */
    public String getJmeno() {
        return jmeno;
    }

    public boolean isMrtvy() {
        return mrtvy;
    }

    public boolean isPromluveno() {
        return promluveno;
    }

    public int getPromluvenoCount() {
        return promluvenoCount;
    }

    public boolean isProzkoumano() {
        return prozkoumano;
    }

    /**
     * Setter
     *
     * @param mrtvy hodnota na, kterou chceme hodnotu nastavit
     */
    public void setMrtvy(boolean mrtvy) {
        this.mrtvy = mrtvy;
    }

    /**
     * Setter
     *
     * @param promluveno hodnota na, kterou chceme hodnotu nastavit
     */
    public void setPromluveno(boolean promluveno) {
        this.promluveno = promluveno;
    }

    /**
     * Setter
     *
     * @param prozkoumano hodnota na, kterou chceme hodnotu nastavit
     */
    public void setProzkoumano(boolean prozkoumano) {
        this.prozkoumano = prozkoumano;
    }

    /**
     * Setter
     *
     * @param promluvenoCount hodnota na, kterou chceme hodnotu nastavit
     */
    public void setPromluvenoCount(int promluvenoCount) {
        this.promluvenoCount = promluvenoCount;
    }
}
