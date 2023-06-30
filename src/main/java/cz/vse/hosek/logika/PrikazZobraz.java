package cz.vse.hosek.logika;

/**
 *  Třída PrikazZobraz implementuje pro hru příkaz zobraz.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 24. 5. 2020
 */
public class PrikazZobraz implements IPrikaz{
    private static final String NAZEV = "zobraz";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, z kterého se bude "zobrazovat" požadovaná věc (pouze v síňi slávy)
     */
    public PrikazZobraz(HerniPlan plan) {
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
        if (parametry.length == 0) {
            return "nevím co mám zobrazit";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if(aktualniProstor.getNazev().equals("síň slávy")) {
            if (!aktualniProstor.obsahujeVec(nazevVeci)) {
                return "Věc s názvem " + nazevVeci + " v prostoru neexistuje.";
            }
            Util.zobrazObrazek(aktualniProstor.vratVec(nazevVeci).getCesta());
            return "Zobrazil jsi si obrázek " + nazevVeci + ".";
        } else {
            return "Zde nemůžeš nic zobrazovat.";
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
