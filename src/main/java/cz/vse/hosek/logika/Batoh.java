package cz.vse.hosek.logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída Batoh - třída představující batoh(inventář) hráče.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Batoh reprezentuje místo, kde se ukládají věci, které hráč sebral či získal.
 * Hráč může mít současně maximálně 4 věci v batohu.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class Batoh {
    private Map<String, Vec> veci;
    private final int velikostBatohu;

    /**
     * Konstruktor třídy
     *
     * Vytváří instaci batohu.
     */
    public Batoh() {
        veci = new HashMap<>();
        velikostBatohu = 4;
    }

    /**
     * Metoda pridejDoBatohu přidává sebranou či získanou věc do batohu, pokud má hráč místo.
     *
     * @param vec - věc, kterou chce hráč vzít s sebou
     * @param plan - herní plán, do kterého se do batohu vkládá věc
     */
    public void pridejDoBatohu (Vec vec, HerniPlan plan) {
        if (veci.size() < velikostBatohu) {
            veci.put(vec.getNazev(), vec);

            if (vec.getCesta() != null) {
                Util.zobrazObrazek(vec.getCesta());
            }
        } else {
            plan.getAktualniProstor().pridejVec(vec);
            System.out.println("Máš plný batoh. Zkus něco nejdříve položit.");
        }
    }

    /**
     * Metoda odeberZBatohu odebírá věc z batohu
     *
     * @param nazevVeci - název věci, kterou chce hráč položit nebo odevzdal
     * @return vrátí odebranou věc
     */
    public Vec odeberZBatohu (String nazevVeci) {
        return veci.remove(nazevVeci);
    }

    /**
     * Metoda getVeci vrací mapu Stringu, Věc.
     *
     * @return vrátí Map<String, Vec> věcí
     */
    public Map<String, Vec> getVeci() {
        return veci;
    }

    /**
     * Metoda vrací řetězec, který popisuje obsah batohu
     *
     * @return Popis batohu - názvů věcí v batohu
     */
    public String vypisBatoh () {
        return "batoh: " + Util.vypisStringNazvu(veci.values());
    }


}
