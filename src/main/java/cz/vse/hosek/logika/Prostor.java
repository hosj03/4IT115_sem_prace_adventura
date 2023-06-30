package cz.vse.hosek.logika;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Hošek
 * @version 25. 5. 2020
 */
public class Prostor  implements INazev {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;  // obsahuje veci v instanci veci
    private ArrayList<Postava> seznamPostav;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        seznamPostav = new ArrayList<Postava>();
        vychody = new HashSet<Prostor>();
        veci = new HashMap<String, Vec>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    @Override
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu()+ "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {

        return "Východy: " + Util.vypisStringNazvu(vychody);
    }

    /**
     * Vrací textový řetězec, který popisuje věci v prostoru, například:
     * "věci: postel ".
     *
     * @return Popis věcí - názvů věcí
     */
    private String popisVeci() {
        return "věci: " + Util.vypisStringNazvu(veci.values());
    }

    /**
     * Vrací textový řetězec, který popisuje postavy, například:
     * "postavy: Jakub ".
     *
     * @return Popis postav - názvů postav
     */
    private String popisPostav() {
        String vracenyText = "postavy: ";
        boolean prvni = true;
        for (Postava postava: seznamPostav) {
            if (prvni) {
                prvni = false;
            } else {
                vracenyText += ", ";
            }

            if (postava != null) {
                vracenyText += postava.getJmeno();
                if (postava.isMrtvy()) {
                    vracenyText += " (mrtvý)";
                } else {
                    vracenyText += " (živý)";
                }
            }
        }


        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Metoda vracející věc.
     *
     * @param nazevVeci název požadované věci
     */
    public Vec vratVec (String nazevVeci) {
        return veci.get(nazevVeci);
    }

    /**
     * Metoda vracející postavu pokud existuje, jinak null.
     *
     * @param jmeno jméno požadované postavy
     * @return vrátí intanci postavy nebo null
     */
    public Postava vratPostavu(String jmeno) {
        for (Postava postava : seznamPostav) {
            if (postava.getJmeno().equals(jmeno)) {
                return postava;
            }
        }
        return null;
    }

    /**
     * Metoda zjišťuje zda věci obsahují věc s názvem věci.
     *
     * @param nazevVeci název požadované věci
     * @return vrací true pokud věc obsahuje, jinak false
     */
    public boolean obsahujeVec (String nazevVeci) {
        return veci.containsKey(nazevVeci);
    }

    /**
     * Metoda zjišťuje zda prostor obsahuje postavu s jménem.
     *
     * @param jmeno jméno požadované postavy
     * @return vrací true pokud postavu obsahuje, jinak false
     */
    public boolean obsahujePostavu(String jmeno) {
        for (Postava postava:seznamPostav) {
            if (jmeno.equals(postava.getJmeno())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda přidává věc do věcí.
     *
     * @param vec instance přidávané věci
     */
    public void pridejVec (Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     * Metoda odstraní věc s požadovaným názvem, pokud existuje.
     *
     * @param nazevVeci název odstraňované věci
     * @return vrátí instanci věci nebo null
     */
    public Vec odstranVec (String nazevVeci) {
        return veci.remove(nazevVeci);
    }

    /**
     * Metoda přidá postavu do seznamu postav.
     *
     * @param postava instance přidávané postavy
     */
    public void pridejPostavu(Postava postava) {
        seznamPostav.add(postava);
    }

    /**
     * Metoda obnoví počet promluvení postavám.
     */
    public void obnovaPromluveno() {
        for (Postava postava:seznamPostav) {
            postava.setPromluvenoCount(0);
        }
    }

    /**
     * Metoda odebere všechny východy.
     */
    public void odeberVychody() {
        vychody.clear();
    }

    /**
     * Metoda odebere všechny věci.
     */
    public void odeberVeci() {
        veci.clear();
    }
}
