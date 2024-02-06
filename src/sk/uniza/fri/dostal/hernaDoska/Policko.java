package sk.uniza.fri.dostal.hernaDoska;

import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;
import sk.uniza.fri.dostal.figurky.Figurka;
import sk.uniza.fri.dostal.figurky.Kamen;

/**
 * Trieda Policko reprezentuje policko na sachovej doske
 *
 * @author Juraj Dostál
 */
public class Policko {

    private Stvorec policko;
    private Obrazok obrazok;
    private Figurka figurka;
    private int pozX;
    private int pozY;

    /**
     * Konstruktor na vytvorenie instancie Policka
     *
     * @param pozX
     * @param pozY
     * @param farba
     */
    public Policko(int pozX, int pozY, String farba) {
        this.pozX = pozX * 100;
        this.pozY = pozY * 100;
        this.policko = new Stvorec(this.pozX , this.pozY);
        this.policko.zmenStranu(100);
        this.policko.zmenFarbu(farba);
        this.policko.zobraz();
    }

    /**
     * Metoda na polozenie figurky na policko
     *
     * @param figurka
     */
    public void polozFigurku(Figurka figurka) {
        this.figurka = figurka;
        this.zobrazObrazok(figurka.getObrazok());
    }

    /**
     * Metoda na zobranie figurky z policka
     *
     * @return figurku
     */
    public Figurka zoberFigurku() {
        var pomFig = this.figurka;
        this.skryObrazok();
        this.figurka = null;
        return pomFig;
    }

    /**
     * Getter metoda
     *
     * @return figurku
     */
    public Figurka getFigurka() {
        return this.figurka;
    }

    /**
     * Motoda na zistenie ci policko obsahuje figurku
     *
     * @return boolean
     */

    public boolean obsahujeFigurku() {
        return this.figurka != null;
    }

    /**
     * Zistenie akej farby je figurka
     *
     * @return
     * @throws Exception
     */
    public Kamen farbaFigurky() throws NullPointerException {
        if (this.obsahujeFigurku()) {
            return this.figurka.getKamen();
        } else {
            throw new NullPointerException("Neobsahuje figurku!");
        }
    }

    private void zobrazObrazok(String cesta) {
        if (this.obrazok != null) {
            this.obrazok.skry();
        }
        this.obrazok = new Obrazok(cesta, this.pozX, this.pozY);
        this.obrazok.zobraz();
    }

    private void skryObrazok() {
        if (this.obrazok != null) {
            this.obrazok.skry();
            this.obrazok = null;
        }
    }

}
