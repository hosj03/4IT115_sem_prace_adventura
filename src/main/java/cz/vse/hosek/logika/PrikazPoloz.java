package cz.vse.hosek.logika;


/**
 *  Třída PrikazPoloz implementuje pro hru příkaz polož.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře "pokládat" věci
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "polož". Zkouší položit věc do aktuálního prostoru. Pokud věc
     *  existuje v batohu, položí se do aktuálního prostoru. Pokud zadaná věc v batohu
     *  není, vypíše se chybové hlášení.
     *
     *@param parametry - jako parametr obsahuje jméno věci, kterou chce položit.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "nevím co mám položit";
        }

        String nazevVeci = parametry[0];

        if (plan.getAktualniProstor().getNazev().equals("pokoj Kvájeta")
                && plan.getAktualniProstor().vratPostavu("Kvájet").isMrtvy() && nazevVeci.startsWith("meme")) {
            plan.getBatoh().odeberZBatohu(nazevVeci);
            plan.setZiskaneMemes(plan.getZiskaneMemes() + 1);

            if (plan.getZiskaneMemes() == 4) {
                plan.setVyhrano(true);
                return "Našel jsi všechny memes a výhral jsi.";
            } else {
                return "Položil jsi " + nazevVeci + " na mrtvolu Kvájeta.";
            }
        }

        if (plan.getBatoh().getVeci().containsKey(nazevVeci)) {
            plan.getAktualniProstor().pridejVec(plan.getBatoh().odeberZBatohu(nazevVeci));
            return "Položil jsi věc " + nazevVeci + " na zem do prostoru." + plan.getAktualniProstor().dlouhyPopis();
        } else {
            return "Nic takového v batohu nemáš.";
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
