package cz.vse.hosek.logika;

import java.util.Scanner;

/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře "prozkoumávat" věci
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "prozkoumej". Zkouší prozkoumat věc nebo mrtvolu v aktuálním prostoru.
     *  Pokud aspoň jedno exituje, prozkoumá ji. Pokud zadaná věc/mrtvola v prostoru
     *  není, vypíše se chybové hlášení.
     *
     *@param parametry - jako parametr obsahuje jméno věci/mrtvoli, kterou chce prozkoumat.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat?";
        }

        String nazev = parametry[0];
        Vec vec = null;
        if (plan.getAktualniProstor().obsahujeVec(nazev)) {
            vec = plan.getAktualniProstor().vratVec(nazev);
        }else if (plan.getBatoh().getVeci().containsKey(nazev)) {
            vec = plan.getBatoh().getVeci().get(nazev);
        }

        if (vec != null) {
            if (vec.isProhledana()) {
                return "Tuto věc jsi již prozkoumal.";
            }
            switch (vec.getNazev()) {
                case "arduino":
                    if (vec.isProhledana()) {
                        return "Tuto věc jsi již prozkoumal";
                    }

                    if (plan.getAktualniProstor().vratPostavu("Security").isPromluveno()
                           || (plan.getAktualniProstor().vratPostavu("Security").isMrtvy()
                            && plan.getAktualniProstor().vratVec("lísteček").isProhledana())) {
                        System.out.println("Na arduinu jsou rozsvíceny diody na pozicích 0 1 0 0 0 1 0 1");

                        while (!hadej()) {
                            System.out.println("Zkus to znova");
                        }

                        Vec memeS = new Vec("memeS","Securityho meme", true, false);
                        memeS.setCesta("/obrazky/jarvan_dragons.jpg");
                        plan.getBatoh().pridejDoBatohu(memeS, plan);
                        vec.setProhledana(true);
                        return "Uhádl jsi a získal jsi " + memeS.getPopis();
                    } else {
                        return "Na arduinu jsou rozsvíceny diody na pozicích 0 1 0 0 0 1 0 1";
                    }
                case "truhla":
                    if (plan.getBatoh().getVeci().containsKey("klíč")) {
                        plan.getBatoh().odeberZBatohu("klíč");
                        Vec memeK = new Vec("memeK", "Kvájetův meme", true, false);
                        memeK.setCesta("/obrazky/draven_f.jpg");
                        plan.getBatoh().pridejDoBatohu(memeK, plan);
                        vec.setProhledana(true);
                        return "Otevřel jsi truhlu a našel jsi " + memeK.getPopis() +".";
                    }
                    return "Uzamčená truhla.";

                case "postel":
                    if (plan.getAktualniProstor().getNazev().equals("můj pokoj") && !plan.getAktualniProstor().vratVec(nazev).isProhledana()) {
                        plan.getAktualniProstor().vratVec(nazev).setProhledana(true);
                        Vec memeM = new Vec("memeM", "tvůj meme", true, false);
                        memeM.setCesta("/obrazky/tOkski.png");
                        plan.getBatoh().pridejDoBatohu(memeM, plan);
                        return "Prohledal jsi svoji postel a pod ní jsi našel krabici, která obsahovala " + memeM.getPopis() + ".";
                    } else {
                        return "Prozkoumal jsi " + vec.getNazev() + " a zjistil jsi, že je to " + vec.getPopis() + ".";
                    }
                case "lísteček":
                    if (plan.getAktualniProstor().vratPostavu("Security").isMrtvy()) {
                        vec.setProhledana(true);
                        return "Rozlušti binární kód do desítkové soustavy a budeš odměněn. Nalezneš ho na arduinu.";
                    }
                default:
                    return "Prozkoumal jsi " + vec.getNazev() + " a zjistil jsi, že je to " + vec.getPopis() + ".";
            }
        } else if ( plan.getAktualniProstor().obsahujePostavu(nazev)
                && plan.getAktualniProstor().vratPostavu(nazev).isMrtvy()) {
            Postava postava = plan.getAktualniProstor().vratPostavu(nazev);

            if (postava.isProzkoumano()) {
                return "Tuto postavu jsi již prohledal.";
            }
            switch (postava.getJmeno()) {
                case "Tireneali":
                    Vec memeT = new Vec("memeT", "meme Tireneali", true, false);
                    memeT.setCesta("/obrazky/witcher_common_item.jpg");
                    if (!postava.isProzkoumano()) {
                        postava.setProzkoumano(true);
                        plan.getBatoh().pridejDoBatohu(memeT, plan);
                        return "Prohledal jsi mrtvolu Tireneali a našel jsi " + memeT.getPopis() + ".";
                    } else {
                        return "Tuto postavu jsi již prohledal.";
                    }

                case "Urdont":
                    if (!postava.isProzkoumano()) {
                        postava.setProzkoumano(true);
                        Vec klic = new Vec("klíč", "klíč k truhle", true, false);
                        plan.getBatoh().pridejDoBatohu(klic, plan);
                        return "Našel jsi " + klic.getNazev() + ".";
                    } else {
                        return "Tuto postavu jsi již prohledal.";
                    }
                case "Security":
                case "Kvájet":
                    if (!postava.isProzkoumano()) {
                        postava.setProzkoumano(true);
                        return "Prozkoumal jsi mrtvolu " + postava.getJmeno() + ", ale nic jsi nenašel.";
                    } else {
                        return "Tuto postavu jsi již prohledal.";
                    }
            }
        }

        return "Nic takového tu neexistuje.";
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

    private boolean hadej() {
        int vysledek = 0;

        Scanner radek = new Scanner(System.in);
        String vysledekString = radek.nextLine();
        vysledek = Integer.parseInt(vysledekString);

        return vysledek == 69;
    }
}
