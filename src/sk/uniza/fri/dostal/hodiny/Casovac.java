package sk.uniza.fri.dostal.hodiny;

import fri.shapesge.Manazer;
import sk.uniza.fri.dostal.figurky.Kamen;
import javax.swing.JOptionPane;

/**
 * Trieda Casovac pripravuje pre hracov casovace s nastavenim casom a s odpocitavanim
 *
 * @author Juraj Dostál
 */

public class Casovac {
    private Displej bieleHodiny;
    private Displej cierneHodiny;
    private int casBielehoMinuty;
    private int casBielehoSekundy;
    private int casCiernehoMinuty;
    private int casCiernehoSekundy;
    private Manazer manazer;
    private Kamen kamen;

    /**
     * Konstruktor na vytvorenie casovacov pre hracov
     *
     * @param nastavMinuty
     */
    public Casovac(int nastavMinuty) {
        this.bieleHodiny = new Displej(0, 810);
        this.cierneHodiny = new Displej(630, 810);

        this.kamen = Kamen.BIELY;
        this.manazer = new Manazer();

        this.casBielehoSekundy = 0;
        this.casCiernehoSekundy = 0;

        if (-1 < nastavMinuty && nastavMinuty < 31) {
            this.casBielehoMinuty = nastavMinuty;
            this.casCiernehoMinuty = nastavMinuty;
        } else {
            this.casBielehoMinuty = 10;
            this.casCiernehoMinuty = 10;
        }

        this.bieleHodiny.nastavCas(this.casBielehoMinuty, this.casBielehoSekundy);
        this.cierneHodiny.nastavCas(this.casCiernehoMinuty, this.casCiernehoSekundy);
    }

    /**
     * Metoda, ktoru vyuziva trieda manazer.
     * Sluzi na odpocitavanie.
     */
    public void tik() {
        if (this.kamen == Kamen.BIELY) {
            if (this.casBielehoSekundy > 0 ) {
                this.casBielehoSekundy--;

                this.bieleHodiny.nastavCas(this.casBielehoMinuty, this.casBielehoSekundy);
            } else {
                this.casBielehoSekundy = 59;
                this.casBielehoMinuty--;

                this.bieleHodiny.nastavCas(this.casBielehoMinuty, this.casBielehoSekundy);
            }
            if (this.casBielehoMinuty == 0 && this.casBielehoSekundy == 0) {
                this.skoncilaHra();
            }
        } else {
            if (this.casCiernehoSekundy > 0 ) {
                this.casCiernehoSekundy--;

                this.cierneHodiny.nastavCas(this.casCiernehoMinuty, this.casCiernehoSekundy);
            } else {
                this.casCiernehoSekundy = 59;
                this.casCiernehoMinuty--;

                this.cierneHodiny.nastavCas(this.casCiernehoMinuty, this.casCiernehoSekundy);
            }
            if (this.casCiernehoMinuty == 0 && this.casCiernehoSekundy == 0) {
                this.skoncilaHra();
            }
        }
    }

    /**
     * Metoda na zmenenie odpocitavania casu pre daneho hraca
     *
     * @param kamen
     */
    public void zmenHraca(Kamen kamen) {
        this.kamen = kamen;
    }

    /**
     * Metoda na zacatie odpocitavania.
     */
    public void zacalaHra() {
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metoda na vyprsanie casu.
     */
    public void skoncilaHra() {
        this.manazer.prestanSpravovatObjekt(this);
        JOptionPane.showMessageDialog(null, "Hrac s " + this.kamen.getText() + "mi figurkami prehral lebo presiahol cas!");
    }
}
