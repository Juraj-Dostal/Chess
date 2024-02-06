package sk.uniza.fri.dostal.zaklad;

import fri.shapesge.Manazer;
import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;
import sk.uniza.fri.dostal.vynimky.OhrozenyKralException;
import sk.uniza.fri.dostal.vynimky.ProtihracJeNaTahuException;

import javax.swing.JOptionPane;
/**
 * Trieda Ovladanie sluzi na prijimanie sprav od manazera na ovladanie figuriek.
 *
 * @author Juraj Dostál
 */
public class Ovladanie {
    private Sachovnica sachovnica;
    private int[] suradnicePrvehoPolicka;
    private Manazer manazer;
    private boolean oznacenePolicko;

    /**
     * Konstruktor inicializuje atributy a nastavuje Manazera.
     *
     * @param sachovnica
     */
    public Ovladanie(Sachovnica sachovnica) {
        this.sachovnica = sachovnica;
        this.suradnicePrvehoPolicka = new int[2];
        this.oznacenePolicko = false;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metoda, ktoru vyuziva manazer na vybratie figurky
     *
     * @param pozX
     * @param pozY
     */
    public void oznacFigurku(int pozX, int pozY) {
        if (pozY > 800) {
            return;
        }
        this.suradnicePrvehoPolicka[0] = pozX / 100;
        this.suradnicePrvehoPolicka[1] = pozY / 100;
        this.oznacenePolicko = true;
    }

    /**
     * Metoda, ktoru vyuziva manazer na presunutie vybratej figurky
     *
     * @param pozX
     * @param pozY
     */
    public void presunFigurku(int pozX, int pozY) {
        if (pozY > 800) {
            return;
        }
        try {
            if (this.oznacenePolicko) {
                this.oznacenePolicko = false;
                this.sachovnica.presunFigurku(this.suradnicePrvehoPolicka, new int[]{(pozX / 100), (pozY / 100)});
            }
        } catch (OhrozenyKralException oke) {
            JOptionPane.showMessageDialog(null, oke.getMessage());
        } catch (FigurkaNemozeUrobitTahException fex) {
            JOptionPane.showMessageDialog(null, fex.getMessage());
        } catch (ProtihracJeNaTahuException pnte) {
            JOptionPane.showMessageDialog(null, pnte.getMessage());
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(null, iae.toString());
        }
    }
}
