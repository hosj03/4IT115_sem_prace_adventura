package cz.vse.hosek.logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Hošek
 *@version    25. 5. 2020
 */
public class HerniPlan {
    private Hra hra;
    private Prostor aktualniProstor;
    private Batoh batoh;
    private int ziskaneMemes;
    private Postava[]  seznamPostav;
    private boolean vyhrano;
    private Prostor mujPokoj;
    private Prostor pokojSecurityho;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        this.hra = hra;

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor chodba = new Prostor("chodba","chodba, která spojuje jednotlivé pokoje");
        mujPokoj = new Prostor("můj pokoj", "tvého pokoje, kde najdeš spoustu anime věcí");
        Prostor pokojKvajeta = new Prostor("pokoj Kvájeta","pokoj Kvájeta, ve kterém se nachází plakáty a figurky ze seriálů či her, jako například World of Warcraft");
        pokojSecurityho = new Prostor("pokoj Securityho","Securityho pokoj, kde budeš ohromen technickými vymoženostmi");
        Prostor pokojUrdonta = new Prostor("pokoj Urdonta","Urdontův pokoj, který je plný výkresů budov.");
        Prostor pokojTireneali = new Prostor("pokoj Tireneali","pokoj Tireneali, ve kterém nalezneš sbírku věcí s tématikou Harryho Pottera");

        // vytvoření a přidání postav do prostorů
        seznamPostav = new Postava[4];
        Postava Kvajet = new Postava("Kvájet", false);
        Postava Security = new Postava("Security", false);
        Postava Urdont = new Postava("Urdont", true);
        Postava Tireneali = new Postava("Tireneali", true);
        seznamPostav[0] = Kvajet;
        seznamPostav[1] = Security;
        seznamPostav[2] = Urdont;
        seznamPostav[3] = Tireneali;

        pokojKvajeta.pridejPostavu(Kvajet);
        pokojSecurityho.pridejPostavu(Security);
        pokojUrdonta.pridejPostavu(Urdont);
        pokojTireneali.pridejPostavu(Tireneali);


        // přiřazují se průchody mezi prostory (sousedící prostory)
        mujPokoj.setVychod(chodba);
        chodba.setVychod(mujPokoj);
        chodba.setVychod(pokojKvajeta);
        chodba.setVychod(pokojSecurityho);
        chodba.setVychod(pokojUrdonta);
        chodba.setVychod(pokojTireneali);
        pokojKvajeta.setVychod(chodba);
        pokojSecurityho.setVychod(chodba);
        pokojUrdonta.setVychod(chodba);
        pokojUrdonta.setVychod(pokojTireneali);
        pokojTireneali.setVychod(chodba);
        pokojTireneali.setVychod(pokojUrdonta);

        //vytvoření věcí do prostoru
        Vec postelM = new Vec("postel", "velká a prostorná postel", false, false);
        Vec postelK = new Vec("postel", "velká a prostorná postel", false, false);
        Vec postelS = new Vec("postel", "velká a prostorná postel", false, false);
        Vec postelU = new Vec("postel", "velká a prostorná postel", false, false);
        Vec postelT = new Vec("postel", "velká a prostorná postel", false, false);
        Vec skrinM = new Vec("skříň", "skříň stojící v Okskiho pokoji", false, true);
        Vec skrinK = new Vec("skříň", "skříň v pokoji Kvájeta", false, true);
        Vec skrinU = new Vec("skříň", "skříň nacházející se v pokoji Urdonta", false, true);
        Vec arduino = new Vec("arduino", "malý \"počítač\"", false, false);
        Vec raspberryPie = new Vec("raspberryPie", "malý \"počítač\"", false, false);
        Vec zrcadlo = new Vec("zrcadlo", "zrcadlo, které předpovídá konec budoucí hry", false, false);
        Vec stulT = new Vec("stůl", "stůl v pokoji Tireneali", false,false);
        Vec stulS = new Vec("stůl", "stůl v Securityho pokoji, na kterém se nachází arduino a raspberryPie", false,false);
        Vec botnik = new Vec("botník", "botník ve kterém se nachází 5 párů bot", false, false);
        Vec pocitacM = new Vec("počítač", "tvůj stolní počítač", false, false);
        Vec notebookT = new Vec("notebook", "notebook Tireneali", true, false);

        Vec dvere = new Vec("dveře", "dvěře vedoucí ven, které jsou zamčené a nejdou odemknout", false, false);
        Vec truhla = new Vec("truhla", "zamčená truhla", false, true);
        dvere.setUzamcena(true);
        truhla.setUzamcena(true);

        Vec memeU = new Vec("memeU", "Urdontův meme", true, false);
        memeU.setCesta("/obrazky/pawel_hacky.jpg"); //  "\\obrazky\\memeU.jpg"

        chodba.pridejVec(zrcadlo);
        chodba.pridejVec(dvere);
        chodba.pridejVec(botnik);

        mujPokoj.pridejVec(postelM);
        mujPokoj.pridejVec(skrinM);
        mujPokoj.pridejVec(pocitacM);

        pokojKvajeta.pridejVec(truhla);
        pokojKvajeta.pridejVec(postelK);
        pokojKvajeta.pridejVec(skrinK);

        pokojSecurityho.pridejVec(stulS);
        pokojSecurityho.pridejVec(postelS);
        pokojSecurityho.pridejVec(arduino);
        pokojSecurityho.pridejVec(raspberryPie);

        pokojUrdonta.pridejVec(skrinU);
        pokojUrdonta.pridejVec(postelU);
        skrinU.pridejVec(memeU);

        pokojTireneali.pridejVec(postelT);
        pokojTireneali.pridejVec(stulT);
        pokojTireneali.pridejVec(notebookT);


        // Vytvoří se batoh
        batoh = new Batoh();

        // Nastevení ziskaneMemes na 0
        ziskaneMemes = 0;

        // Nastavení vyhrano na false
        vyhrano = false;

                
        aktualniProstor = mujPokoj;  // hra začíná v pokoji Okskiho (postavy za kterou hrajete)
        //setVyhrano(true);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     * Getter batohu
     *
     * @return vrátí uloženou instanci batohu
     */
    public Batoh getBatoh() {
        return batoh;
    }

    /**
     * Metoda novyDen se volá při určitém počtu použití příkazu jdi.
     * Kdy simuluje únavu hráče, který šel spát a probudil se další den.
     *
     * @return vrací String probuzení a dlouhehyPopis prostoru
     */
    public String novyDen() {
        aktualniProstor = mujPokoj;
        try {
            System.out.println("Jsi unavený a jdeš spát.");
            Thread.sleep(5000);
            String text = "Postava ";
            if (!seznamPostav[1].isMrtvy()) {
                seznamPostav[1].setMrtvy(true);
                text += seznamPostav[1].getJmeno() + " zemřela.";
                Vec papir = new Vec("lísteček", "Lísteček na kterém je něco napsáno.", false, false);
                pokojSecurityho.pridejVec(papir);
            } else if (!seznamPostav[0].isMrtvy()) {
                seznamPostav[0].setMrtvy(true);
                text += seznamPostav[0].getJmeno() + " zemřela.";
            } else {
                hra.setKonecHry(true);
                return "Zemřel jsi a prohrál.";
            }
            System.out.println(text);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Chyba při uspání vlákna.");
            e.printStackTrace();
        }

        return "Probudil jsi se.\n\n" + aktualniProstor.dlouhyPopis();
    }

    /**
     * Getters
     *
     * @return hodnotu požadovaných proměnných
     */
    public int getZiskaneMemes() {
        return ziskaneMemes;
    }

    public boolean isVyhrano() {
        return vyhrano;
    }

    /**
     * Setter proměnné ziskaneMemes
     *
     * @param ziskaneMemes - proměnná počtu zíkaných memes
     */
    public void setZiskaneMemes(int ziskaneMemes) {
        this.ziskaneMemes = ziskaneMemes;
    }



    /**
     * Metoda setVyhrano se zavolá při výhře.
     *
     * @param vyhrano proměná udávající, zda hráč vyhrál
     */
    public void setVyhrano(boolean vyhrano) {
        this.vyhrano = vyhrano;
        if (vyhrano) {
            aktualniProstor = mujPokoj;
            aktualniProstor.odeberVychody();
            aktualniProstor.odeberVeci();

            for (Postava postava:seznamPostav) {
                postava.setMrtvy(false);
                aktualniProstor.pridejPostavu(postava);
            }
            Prostor sinSlavy = new Prostor("síň slávy", "Zde je sbírka nejlepších memes");
            sinSlavy.setVychod(aktualniProstor);

            //vytvoření a přidání dalších věcí do síně slávy
            Vec memeF = new Vec("hoppo", "meme králíka", false, false);
            Vec memeL = new Vec("LFR", "meme looking for raid z wowka", false, false);
            Vec memeP = new Vec("shadowlands", "meme cesty do shadowlands", false, false);
            Vec memeR = new Vec("raid", "meme raidu ve wowku", false, false);
            Vec memeD = new Vec("skada", "meme otázky na skadu", false, false);
            Vec memeH = new Vec("healer", "meme healera hrajícího wowko", false, false);
            Vec memeU = new Vec("memeU", "Urdontův meme", true, false);
            Vec memeS = new Vec("memeS","Securityho meme", true, false);
            Vec memeT = new Vec("memeT", "meme Tireneali", true, false);
            Vec memeK = new Vec("memeK", "Kvájetův meme", true, false);
            Vec memeM = new Vec("memeM", "tvůj meme", true, false);

            memeF.setCesta("/obrazky/final.jpg");
            memeL.setCesta("/obrazky/lfr_wow.jpg");
            memeP.setCesta("/obrazky/path_to_shadowlands.jpg");
            memeR.setCesta("/obrazky/raid_yelling.jpg");
            memeD.setCesta("/obrazky/skada.jpg");
            memeH.setCesta("/obrazky/wow_healer.jpg");
            memeU.setCesta("/obrazky/pawel_hacky.jpg");
            memeS.setCesta("/obrazky/jarvan_dragons.jpg");
            memeT.setCesta("/obrazky/witcher_common_item.jpg");
            memeK.setCesta("/obrazky/draven_f.jpg");
            memeM.setCesta("/obrazky/tOkski.png");


            sinSlavy.pridejVec(memeF);
            sinSlavy.pridejVec(memeL);
            sinSlavy.pridejVec(memeP);
            sinSlavy.pridejVec(memeR);
            sinSlavy.pridejVec(memeD);
            sinSlavy.pridejVec(memeH);
            sinSlavy.pridejVec(memeU);
            sinSlavy.pridejVec(memeS);
            sinSlavy.pridejVec(memeT);
            sinSlavy.pridejVec(memeK);
            sinSlavy.pridejVec(memeM);


            aktualniProstor.setVychod(sinSlavy);
        }
    }
}