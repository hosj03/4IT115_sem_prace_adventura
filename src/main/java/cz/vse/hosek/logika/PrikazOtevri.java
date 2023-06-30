package cz.vse.hosek.logika;

import java.util.Map;

/**
 *  Třída PrikazOtevri implementuje pro hru příkaz otevři.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 24. 5. 2020
 */
public class PrikazOtevri implements IPrikaz {
    private static final String NAZEV = "otevři";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře "otevírat" věci
     */
    public PrikazOtevri(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "otevři". Zkouší se otevřít zadanou věc. Pokud věc
     *  existuje a je otevíratelná a nezamčená (nebo má klíč), otevře ji.
     *  Pokud zadaná věc není, vypíše se chybové hlášení.
     *
     *@param parametry - jako parametr obsahuje název věci, kterou chce otevřít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám otevřít?";
        }

        String nazevVeci = parametry[0];
        Vec vec = plan.getAktualniProstor().vratVec(nazevVeci);

        if (vec != null && vec.isOtevrena()) {
            return "Věc je již otevřená.";
        }

        if (vec != null && vec.isUzamcena() && !vec.isOteviratelna()) {
            return vec.getPopis() + ".";
        }

        if (vec != null && !vec.isOteviratelna()) {
            return "Věc nejde otevřít.";
        }

        if (vec != null && vec.isUzamcena()) {
            return "Věc je uzamčená";
        }

        if (vec != null && !vec.isUzamcena() && vec.isOteviratelna()) {
            String stringVeci = plan.getAktualniProstor().vratVec(nazevVeci).vypisStringVeci();

            for (Map.Entry<String, Vec> veci: plan.getAktualniProstor().vratVec(nazevVeci).getVeci().entrySet()) {
                plan.getAktualniProstor().pridejVec(veci.getValue());
            }

            plan.getAktualniProstor().vratVec(nazevVeci).odstranVsechnyVeci();
            plan.getAktualniProstor().vratVec(nazevVeci).setOtevrena(true);

            if (stringVeci.isEmpty()) {
                return "Otevřel jsi " + nazevVeci + " a nenašel jsi nic.";
            }

            return "Otevřel jsi " + nazevVeci + " a našel jsi " + stringVeci + "\n\n" + plan.getAktualniProstor().dlouhyPopis();
        }
        return "Věc s názvem " + nazevVeci + " v prostoru neexistuje.";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
