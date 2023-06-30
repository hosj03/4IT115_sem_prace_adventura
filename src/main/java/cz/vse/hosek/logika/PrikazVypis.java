package cz.vse.hosek.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 24. 5. 2020
 */
public class PrikazVypis implements IPrikaz{
    private static final String NAZEV = "vypiš";
    private HerniPlan plan;


    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, který bude "vypisovat" batoh nebo popis aktuálního prostoru
     */
    public PrikazVypis(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vypiš". Vypíše obsah batohu nebo dlouhy popis aktuálního prostoru.
     *  Jinak se vypíše chybová hláška.
     *
     *@param parametry - jako parametr obsahuje batoh nebo prostor, kterou má vypsat.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 ) {
            return "Co mám vypsat?";
        } else if (parametry[0].equals("batoh") && parametry.length == 1) {
            return plan.getBatoh().vypisBatoh();
        } else if (parametry[0].equals("prostor") && parametry.length == 1) {
            return plan.getAktualniProstor().dlouhyPopis();
        }
        return "Tento příkaz s takovým parametrem neexistuje.";
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
