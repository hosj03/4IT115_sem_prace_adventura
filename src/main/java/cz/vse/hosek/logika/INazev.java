package cz.vse.hosek.logika;

/**
 * Rozhraní, které musí implementovat věc a prostor, pro využití metody vypisStringNazvu.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public interface INazev {
    /**
     *
     * @return vrací se řetězec názvu věci
     */
    public String getNazev();

}