package cz.vse.hosek.logika;

import java.util.ArrayList;

/**
 *  Třída PrikazPromluv implementuje pro hru příkaz "promluv s".
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class PrikazPromluv implements IPrikaz {
    private static final String NAZEV = "promluv s";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "mluvit s" postavama
     */
    public PrikazPromluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "promluv s". Zkouší promluvit s osobou z aktuálního prostoru. Pokud osoba
     *  existuje v prostoru a je naživu, promluví s ní. Pokud zadaná osoba v prostoru
     *  není, vypíše se chybové hlášení.
     *
     *@param parametry - jako parametr obsahuje jméno osoby, se kterou chce mluvit.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám promluvit?";
        }

        String nazev = parametry[0];
        Postava postava = plan.getAktualniProstor().vratPostavu(nazev);

        switch (parametry[0]) {
            case "Kvájet":
                if (plan.isVyhrano()) {
                    return "Jsi fakt špatnej, že jsi to nezvládl rychleji, ale stejnak díky. Ó velký meme Lorde.";
                }

                if (postava.isMrtvy()) {
                    return "Již neni mezi námi. Umřel na záchavat smíchu.";
                }

                ArrayList<Vec> nazevVeci = new ArrayList<Vec>();
                for (Vec vec:  plan.getBatoh().getVeci().values()) {
                    if (vec.getNazev().startsWith("meme")) {
                        nazevVeci.add(vec);
                    }
                }
                for (Vec vec:  nazevVeci) {
                        plan.getBatoh().odeberZBatohu(vec.getNazev());
                }

                if (!nazevVeci.isEmpty()) {
                    plan.setZiskaneMemes(plan.getZiskaneMemes() + nazevVeci.size());
                    if (plan.getZiskaneMemes() == 4) {
                        plan.setVyhrano(true);
                        return "Našel jsi všechny memes a výhral jsi.\n" + plan.getAktualniProstor().dlouhyPopis();
                    }
                    return "Díky, že jsi přinesl " + Util.vypisStringNazvu(nazevVeci) + ".";
                }

                if (postava.getPromluvenoCount() == 3) {
                    return "Přestaň mě otravovat a jdi hledat.";
                }
                postava.setPromluvenoCount(postava.getPromluvenoCount() + 1);
                postava.setPromluveno(true);
                switch (plan.getZiskaneMemes()) {
                    case 0:
                        return "Najdi 4 memes a přines mi je.";
                    case 1:
                        return "Najdi jestě 3 memes a přines mi je.";
                    case 2:
                        return "Najdi ještě 2 memes a přines mi je.";
                    case 3:
                        return "Už jenom 1 meme.";
                }
            case "Security":
                if (plan.isVyhrano()) {
                    return "Dík, že jsi mě zachránil. Ó velký meme Lorde.";
                }

                if (postava.isMrtvy()) {
                    return "Již neni mezi námi. Umřel na zásah elektickým proudem.";
                }

                if (postava.getPromluvenoCount() == 3) {
                    return "Tak už to jdi rozluštit.";
                }
                postava.setPromluveno(true);
                return "Pomoz mi rozluštit binární kód do desítkové soustavy a odmněním tě. Nalezneš ho na arduinu.";
            case "Urdont":
                if (plan.isVyhrano()) {
                    return "Díky, že jsi mě oživil. Ó velký meme Lorde.";
                }
            case "Tireneali":
                if (plan.isVyhrano()) {
                    return "Děkuji, že jsi mě zachránil. Ó velký meme Lorde.";
                }
                return "Tento člověk již zemřel.";
            default:
                return "Nikdo takový zde není.";
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
