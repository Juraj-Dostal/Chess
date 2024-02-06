package sk.uniza.fri.dostal.figurky;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;

/**
 * Abstraktna trieda Figurka, ktora sluzi ako predok pre sachove figurky.
 *
 * @author Juraj Dostál
 */
public abstract class Figurka {
    private Kamen kamen;

    /**
     * Konstruktor na vytvorenie instancie potomkou
     *
     * @param kamen
     */
    public Figurka(Kamen kamen) {
        this.kamen = kamen;
    }

    /**
     * Metoda na zistenie farby figurky
     *
     * @return Kamen
     */
    public Kamen getKamen() {
        return this.kamen;
    }

    /**
     * Abstraktna metoda vracia nazov figurky
     *
     * @return String nazov figurky
     */
    public abstract String getNazov();

    /**
     * Abstraktna metoda, ktora vracia cestu k obrazku danej figurke
     *
     * @return cestu k obrazku
     */
    public abstract String getObrazok();

    /**
     * Abstraktna metoda, ci dana Figurka moze vykonat dany pohyb
     *
     * @param sachovnica
     * @param mojaPozicia
     * @param cielenaPozicia
     * @return
     * @throws Exception
     */
    public abstract boolean mozemUrobitPohyb(Sachovnica sachovnica, int[] mojaPozicia, int[] cielenaPozicia) throws FigurkaNemozeUrobitTahException;

    /**
     * Abstraktna metoda, zistenie ci je kral ohrozeny
     *
     * @param sachovnica
     * @param pozicia
     * @return boolean
     */
    public abstract boolean ohrozujeKrala(Sachovnica sachovnica, int[] pozicia);
}
