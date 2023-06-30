package cz.vse.hosek.logika;


/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 24. 5. 2020
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře "sbírat" věci
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". Zkouší sebrat věc z aktuálního prostoru. Pokud věc
     *  existuje v prostoru a je přenositelná, vloží se do batohu. Pokud zadaná věc v prostoru
     *  není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno věci, kterou chce sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "nevím co mám sebrat";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (!aktualniProstor.obsahujeVec(nazevVeci)) {
            return "Věc s názvem " + nazevVeci + " v prostoru neexistuje.";
        } else {
            Vec vec = aktualniProstor.vratVec(nazevVeci);
            if (vec.isPrenositelna()) {
                plan.getBatoh().pridejDoBatohu(vec, plan);
                aktualniProstor.odstranVec(nazevVeci);
                return "Sebral jsi věc " + nazevVeci + ".\n" + aktualniProstor.dlouhyPopis();
            } else {
                return "Tuto věc není možné přenést.";
            }
        }
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
